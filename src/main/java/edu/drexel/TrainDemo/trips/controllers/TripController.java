package edu.drexel.TrainDemo.trips.controllers;

import edu.drexel.TrainDemo.trips.models.Route;
import edu.drexel.TrainDemo.trips.repositories.RouteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TripController {
    private final RouteRepository repo;

    public TripController(RouteRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/getAllTrips")
    public List<Route> routes() {
        return (List<Route>) repo.findAll();
    }
}
