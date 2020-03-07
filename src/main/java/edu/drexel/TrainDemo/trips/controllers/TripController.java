package edu.drexel.TrainDemo.trips.controllers;

import edu.drexel.TrainDemo.trips.models.RouteEntity;
import edu.drexel.TrainDemo.trips.models.StationEntity;
import edu.drexel.TrainDemo.trips.models.derived.Itinerary;
import edu.drexel.TrainDemo.trips.models.derived.TripSearchRequest;
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
    private RouteRepository routeRepository;
    private StationRepository stationRepository;
    private TripService tripService;

    public TripController(RouteRepository routeRepository, StationRepository stationRepository) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
        this.tripService = new TripService(stationRepository);
    }

    @GetMapping("/trips/routes")
    @ResponseBody
    public List<RouteEntity> getAllRoutes() {
        return (List<RouteEntity>) routeRepository.findAll();
    }

    @RequestMapping("/trips/routes/{routeID}")
    @ResponseBody
    public Optional<RouteEntity> getRouteByID(@PathVariable long routeID) {
        return routeRepository.findById(routeID);
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
