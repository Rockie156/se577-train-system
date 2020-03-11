package edu.drexel.TrainDemo.order.services;

import edu.drexel.TrainDemo.order.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
