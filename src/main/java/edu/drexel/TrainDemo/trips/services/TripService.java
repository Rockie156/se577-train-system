package edu.drexel.TrainDemo.trips.services;

import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.models.Segment;
import edu.drexel.TrainDemo.trips.models.TripSearchRequest;
import edu.drexel.TrainDemo.trips.models.entities.StationEntity;
import edu.drexel.TrainDemo.trips.models.entities.StopTimeEntity;
import edu.drexel.TrainDemo.trips.models.entities.TripEntity;
import edu.drexel.TrainDemo.trips.repositories.StationRepository;
import edu.drexel.TrainDemo.trips.repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private StationRepository stationRepository;
    private TripRepository tripRepository;

    public TripService(StationRepository stationRepository, TripRepository tripRepository) {
        this.stationRepository = stationRepository;
        this.tripRepository = tripRepository;
    }

    public List<Itinerary> getMatchingTrips(TripSearchRequest searchRequest) {
        String toId = searchRequest.getTo();
        String fromId = searchRequest.getFrom();

        StationEntity toStation = safeGetStationFromId(toId);

        List<TripEntity> tripsThatContainOurFromStation =
                tripRepository.findByStops_Station_Id(fromId);

        List<Itinerary> itineraryList = new ArrayList<>();

        for (TripEntity trip : tripsThatContainOurFromStation) {
            StopTimeEntity fromStop = trip.getStopByStationId(fromId);

            int startIndex = fromStop.getStopSequence() + 1;

            for (int i = startIndex; i < trip.getStops().size(); i++) {
                StopTimeEntity toStop = trip.getStopByStopSequence(i);
                if (!toStop.getStation().equals(toStation)) {
                    continue;
                }

                Segment segment = new Segment(trip, fromStop, toStop);

                List<Segment> segmentList = new ArrayList<>();
                segmentList.add(segment);

                Itinerary itinerary = new Itinerary(segmentList);
                itineraryList.add(itinerary);
            }
        }
        return itineraryList;

    }

    public Itinerary constructItinerary(Itinerary unsafeItinerary) {
        List<Segment> segments = new ArrayList<>();
        for (Segment unsafeSegment : unsafeItinerary.getSegments()) {
            Segment safeSegment = findSegment(unsafeSegment);
            segments.add(safeSegment);
        }
        return new Itinerary(segments);
    }

    public Segment findSegment(Segment segment) {
        return findSegment(segment.getTrip().getId(), segment.getFrom().getId(), segment.getTo().getId(), segment.getDeparture(), segment.getArrival());
    }

    public Segment findSegment(Long tripId, String fromId, String toId, Time departure, Time arrival) {
        Itinerary itinerary = findItinerary(tripId, fromId, toId, departure, arrival);
        Segment segment = itinerary.segments.get(0);
        return segment;
    }

    public Itinerary findItinerary(Long tripId, String fromId, String toId, Time departure, Time arrival) {
        Optional<TripEntity> tripResult = tripRepository.findById(tripId);
        if (!tripResult.isPresent()) {
            throw new IllegalArgumentException();
        }

        TripEntity trip = tripResult.get();

        Optional<StopTimeEntity> fromStopResult = trip.getStops().stream().filter(stop -> stop.getStation().getId().equals(fromId)).findFirst();
        Optional<StopTimeEntity> toStopResult = trip.getStops().stream().filter(stop -> stop.getStation().getId().equals(toId)).findFirst();

        if (!fromStopResult.isPresent() || !toStopResult.isPresent()) {
            throw new IllegalArgumentException();
        }

        StopTimeEntity fromStop = fromStopResult.get();
        StopTimeEntity toStop = toStopResult.get();

        if (!fromStop.getDepartureTime().equals(departure) || !toStop.getArrivalTime().equals(arrival)) {
            throw new IllegalArgumentException();
        }

        return new Itinerary(trip, fromStop, toStop);
    }

    private StationEntity safeGetStationFromId(String id) {
        //TODO Add to document that we are making sure data is clean/valid
        Optional<StationEntity> result = stationRepository.findById(id);

        if (!result.isPresent()) {
            throw new IllegalArgumentException();
        }

        return result.get();
    }

    public Iterable<StationEntity> getAllStations() {
        return stationRepository.findAll();
    }
}
