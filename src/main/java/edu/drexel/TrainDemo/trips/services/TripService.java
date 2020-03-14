package edu.drexel.TrainDemo.trips.services;

import edu.drexel.TrainDemo.trips.models.Itinerary;
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
        StationEntity fromStation = safeGetStationFromId(fromId);

        List<TripEntity> tripsThatContainOurFromStation =
                tripRepository.findByStops_Station_Id(fromId);

        List<TripEntity> tripsThatContainOurToStation =
                tripRepository.findByStops_Station_Id(toId);

        List<TripEntity> tripsThatContainBoth = tripsThatContainOurFromStation;
        tripsThatContainBoth.retainAll(tripsThatContainOurToStation);


        TripEntity[] validTrips = tripsThatContainBoth.stream().filter(
                tripEntity ->
                        tripEntity.getStopByStationId(fromId).getStopSequence() < tripEntity.getStopByStationId(toId).getStopSequence()
        ).toArray(TripEntity[]::new);

        ArrayList<Itinerary> resultList = new ArrayList<>();
        for (TripEntity trip : validTrips) {
            StopTimeEntity fromStopTimeEntity = trip.getStopByStationId(fromId);
            StopTimeEntity toStopTimeEntity = trip.getStopByStationId(toId);

            resultList.add(
                    new Itinerary(
                            trip,
                            fromStation,
                            toStation,
                            fromStopTimeEntity.getDepartureTime(),
                            toStopTimeEntity.getArrivalTime()
                    )
            );

        }

        return resultList;
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
}
