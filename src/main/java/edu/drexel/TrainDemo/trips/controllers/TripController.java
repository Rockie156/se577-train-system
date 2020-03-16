package edu.drexel.TrainDemo.trips.controllers;

import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.models.TripSearchRequest;
import edu.drexel.TrainDemo.trips.models.entities.RouteEntity;
import edu.drexel.TrainDemo.trips.models.entities.StationEntity;
import edu.drexel.TrainDemo.trips.repositories.RouteRepository;
import edu.drexel.TrainDemo.trips.repositories.StationRepository;
import edu.drexel.TrainDemo.trips.services.TripService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TripController {
    private StationRepository stationRepository;
    private TripService tripService;

    public TripController(StationRepository stationRepository, TripService tripService) {
        this.stationRepository = stationRepository;
        this.tripService = tripService;
    }

    @GetMapping("/trips/search")
    public String searchTrips(Model model) {
        Iterable<StationEntity> allStations = stationRepository.findAll();
        TripSearchRequest searchRequest = new TripSearchRequest();

        model.addAttribute("allStations", allStations);
        model.addAttribute("TripSearchRequest", searchRequest);

        return "trips/search_trip";
    }

    @PostMapping("/trips/search/submit")
    public String submitSearchTrips(@ModelAttribute TripSearchRequest searchRequest, Model model) {
        List<Itinerary> resultTrips = tripService.getMatchingTrips(searchRequest);
        model.addAttribute("SearchResult", resultTrips);
        return "trips/search_trip_result";
    }
}
