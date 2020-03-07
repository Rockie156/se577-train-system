package edu.drexel.TrainDemo.trips.services;

import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.models.TripSearchRequest;
import edu.drexel.TrainDemo.trips.models.entities.StationEntity;
import edu.drexel.TrainDemo.trips.models.entities.StopTimeEntity;
import edu.drexel.TrainDemo.trips.models.entities.TripEntity;
import edu.drexel.TrainDemo.trips.repositories.StationRepository;
import edu.drexel.TrainDemo.trips.repositories.StopTimeRepository;
import edu.drexel.TrainDemo.trips.repositories.TripRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripService {
    private StationRepository stationRepository;
    private TripRepository tripRepository;
    private StopTimeRepository stopTimeRepository;

    public TripService(StationRepository stationRepository, TripRepository tripRepository) {
        this.stationRepository = stationRepository;
        this.tripRepository = tripRepository;
    }

    public List<Itinerary> getMatchingTrips(TripSearchRequest searchRequest) {
        String toId = searchRequest.getTo();
        String fromId = searchRequest.getFrom();

        StationEntity toStation = safeGetStationFromId(toId);
        StationEntity fromStation = safeGetStationFromId(fromId);

//        Itinerary result = new Itinerary(fromStation, toStation);
        ArrayList<Itinerary> resultList = new ArrayList<>();
//        resultList.add(result);

        Iterable<StopTimeEntity> stops = stopTimeRepository.findAll();

        List<TripEntity> tripsThatContainOurFromStation = new ArrayList<>();
        for (StopTimeEntity stop : stops) {
            if (stop.getStation().getId() == fromId) {
                TripEntity currentTrip = stop.getId().getTrip();
                tripsThatContainOurFromStation.add(currentTrip);
            }
        }

        List<TripEntity> tripsThatContainBoth = new ArrayList<>();

        for (TripEntity trip : tripsThatContainOurFromStation) {
            Long tripId = trip.getId();
            for (StopTimeEntity stop : stopTimeRepository.findByTrip_Id(tripId)) {
                if (stop.getStation().getId() == toId) {
                    TripEntity currentTrip = stop.getId().getTrip();
                    tripsThatContainBoth.add(currentTrip);
                }
            }
        }
        // TODO populate itinerary with StopTimeEntity
        return resultList;

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
