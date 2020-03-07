package edu.drexel.TrainDemo.trips.repositories;

import edu.drexel.TrainDemo.trips.models.RouteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RouteRepository extends CrudRepository<RouteEntity, Long> {
    List<RouteEntity> findByAgency_Id(long agencyId);
}