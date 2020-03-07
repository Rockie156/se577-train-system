package edu.drexel.TrainDemo.trips.repositories;

import edu.drexel.TrainDemo.trips.models.entities.AgencyEntity;
import org.springframework.data.repository.CrudRepository;

public interface AgencyRepository extends CrudRepository<AgencyEntity, Long> {
}
