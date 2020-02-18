package edu.drexel.TrainDemo.trips.repositories;

import edu.drexel.TrainDemo.trips.models.Agency;
import org.springframework.data.repository.CrudRepository;

public interface AgencyRepository extends CrudRepository<Agency, Long> {
}
