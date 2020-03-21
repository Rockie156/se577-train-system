package edu.drexel.TrainDemo.order.repositories;

import edu.drexel.TrainDemo.trips.models.entities.ItineraryEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItineraryRepository extends CrudRepository<ItineraryEntity, Long> {
}
