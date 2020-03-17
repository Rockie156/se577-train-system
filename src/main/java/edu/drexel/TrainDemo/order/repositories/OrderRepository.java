package edu.drexel.TrainDemo.order.repositories;

import edu.drexel.TrainDemo.order.models.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
