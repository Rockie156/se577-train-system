package edu.drexel.TrainDemo.trips.services;

import edu.drexel.TrainDemo.trips.models.StationEntity;
import edu.drexel.TrainDemo.trips.models.derived.Itinerary;
import edu.drexel.TrainDemo.trips.models.derived.TripSearchRequest;
import edu.drexel.TrainDemo.trips.repositories.StationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripService {
    private StationRepository stationRepository;

    public TripService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Itinerary> getMatchingTrips(TripSearchRequest searchRequest) {
        String toId = searchRequest.getTo();
        String fromId = searchRequest.getFrom();

        StationEntity toStation = safeGetStationFromId(toId);
        StationEntity fromStation = safeGetStationFromId(fromId);

        Itinerary result = new Itinerary(fromStation, toStation);
        ArrayList<Itinerary> resultList = new ArrayList<>();
        resultList.add(result);
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
