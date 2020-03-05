package edu.drexel.TrainDemo.trips.controllers;

import edu.drexel.TrainDemo.trips.repositories.RouteRepository;
import edu.drexel.TrainDemo.trips.models.Route;
import edu.drexel.TrainDemo.trips.repositories.RouteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TripController {
    private final RouteRepository repo;

    public TripController(RouteRepository repo) {
        this.repo = repo;
    }


    @GetMapping("/trip/routes")
    public List<Route> getAllRoutes() {
        return (List<Route>) repo.findAll();
    }

    @RequestMapping("/trip/routes/{routeID}")
    public Optional<Route> getRouteByID(@PathVariable long routeID) {
        return repo.findById(routeID);
    }
}
