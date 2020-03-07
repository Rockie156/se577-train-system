package edu.drexel.TrainDemo.trips.controllers;

import edu.drexel.TrainDemo.trips.models.StopTime;
import edu.drexel.TrainDemo.trips.repositories.RouteRepository;
import edu.drexel.TrainDemo.trips.models.Route;
import edu.drexel.TrainDemo.trips.repositories.StopTimeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TripController {
    private final RouteRepository routeRepository;
    private final StopTimeRepository stopTimeRepository;

    public TripController(RouteRepository routeRepository, StopTimeRepository stopTimeRepository) {
        this.routeRepository = routeRepository;
        this.stopTimeRepository = stopTimeRepository;
    }

    @GetMapping("/trip/routes")
    public List<Route> getAllRoutes() {
        return (List<Route>) routeRepository.findAll();
    }

    @RequestMapping("/trip/routes/{routeID}")
    public Optional<Route> getRouteByID(@PathVariable long routeID) {
        return routeRepository.findById(routeID);
    }

    @GetMapping("/trip/test")
    public List<StopTime> testEndpoint() {
        List<StopTime> matching_stops = stopTimeRepository.findByStation_Id("PHL");

        for (StopTime stop : matching_stops) {
            System.out.println("===");
            System.out.println(stop.toString());
        }
        return matching_stops;
    }
}
