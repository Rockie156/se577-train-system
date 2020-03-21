package edu.drexel.TrainDemo.order.repositories;

import edu.drexel.TrainDemo.trips.models.entities.SegmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface SegmentRepository extends CrudRepository<SegmentEntity, Long> {
}
