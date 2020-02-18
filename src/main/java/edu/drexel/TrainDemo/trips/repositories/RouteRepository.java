package edu.drexel.TrainDemo.trips.repositories;

import edu.drexel.TrainDemo.trips.models.Route;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RouteRepository extends CrudRepository<Route, Long> {
    List<Route> findByAgency_Id(long agencyId);
}