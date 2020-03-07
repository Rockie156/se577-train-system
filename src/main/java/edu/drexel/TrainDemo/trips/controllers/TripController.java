package edu.drexel.TrainDemo.trips.controllers;

import edu.drexel.TrainDemo.trips.models.Route;
import edu.drexel.TrainDemo.trips.models.Station;
import edu.drexel.TrainDemo.trips.models.derived.TripSearchRequest;
import edu.drexel.TrainDemo.trips.repositories.RouteRepository;
import edu.drexel.TrainDemo.trips.repositories.StationRepository;
import edu.drexel.TrainDemo.trips.repositories.StopTimeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TripController {
    private final RouteRepository routeRepository;
    private final StopTimeRepository stopTimeRepository;
    private final StationRepository stationRepository;

    public TripController(RouteRepository routeRepository, StopTimeRepository stopTimeRepository, StationRepository stationRepository) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
        this.stopTimeRepository = stopTimeRepository;
    }

    @GetMapping("/trips/routes")
    @ResponseBody
    public List<Route> getAllRoutes() {
        return (List<Route>) routeRepository.findAll();
    }

    @RequestMapping("/trips/routes/{routeID}")
    @ResponseBody
    public Optional<Route> getRouteByID(@PathVariable long routeID) {
        return routeRepository.findById(routeID);
    }

    @GetMapping("/trips/search")
    public String searchTrips(Model model) {
        Iterable<Station> allStations = stationRepository.findAll();
        TripSearchRequest searchRequest = new TripSearchRequest();

        model.addAttribute("allStations", allStations);
        model.addAttribute("TripSearchRequest", searchRequest);

        return "trips/search_trip";
    }

    @PostMapping("/trips/search/submit")
    @ResponseBody
    public String submitSearchTrips(@ModelAttribute TripSearchRequest searchRequest) {
        String result = searchRequest.getFrom();
        System.out.println(result);
        return searchRequest.getFrom() + ", " + searchRequest.getTo();
    }
}
