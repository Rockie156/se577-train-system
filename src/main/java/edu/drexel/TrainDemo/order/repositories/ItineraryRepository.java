package edu.drexel.TrainDemo.order.repositories;

import edu.drexel.TrainDemo.order.models.ItineraryEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItineraryRepository extends CrudRepository<ItineraryEntity, Long> {
}
