package edu.drexel.TrainDemo.trips.controllers;

import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.models.TripSearchRequest;
import edu.drexel.TrainDemo.trips.models.entities.StationEntity;
import edu.drexel.TrainDemo.trips.services.TripService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TripController {
    private TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/trips/search")
    public String searchTrips(Model model) {
        Iterable<StationEntity> allStations = tripService.getAllStations();
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
