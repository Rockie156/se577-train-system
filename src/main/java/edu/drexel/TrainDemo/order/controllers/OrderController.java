package edu.drexel.TrainDemo.order.controllers;

import edu.drexel.TrainDemo.order.models.Order;
import edu.drexel.TrainDemo.order.services.OrderRepository;
import edu.drexel.TrainDemo.order.services.OrderService;
import edu.drexel.TrainDemo.trips.models.Itinerary;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {
    private OrderService orderService;
    private List<Itinerary> newItinerary;

    public OrderController(OrderRepository orderRepository) {
        this.orderService = new OrderService(orderRepository);
    }


    public Order createOrder(List<Itinerary> newItinerary) {
        this.newItinerary = newItinerary;
        return this.orderService.createOrder(newItinerary);
    }
}
