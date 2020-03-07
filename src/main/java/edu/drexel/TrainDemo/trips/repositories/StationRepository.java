package edu.drexel.TrainDemo.trips.repositories;

import edu.drexel.TrainDemo.trips.models.Station;
import org.springframework.data.repository.CrudRepository;

public interface StationRepository extends CrudRepository<Station, Long> {
}