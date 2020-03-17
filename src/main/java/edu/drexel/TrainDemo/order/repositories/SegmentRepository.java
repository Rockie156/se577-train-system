package edu.drexel.TrainDemo.order.repositories;

import edu.drexel.TrainDemo.order.models.SegmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface SegmentRepository extends CrudRepository<SegmentEntity, Long> {
}
