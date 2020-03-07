package edu.drexel.TrainDemo.trips.repositories;

import edu.drexel.TrainDemo.trips.models.StationEntity;
import org.springframework.data.repository.CrudRepository;

public interface StationRepository extends CrudRepository<StationEntity, String> {
}