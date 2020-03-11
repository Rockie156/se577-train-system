package edu.drexel.TrainDemo.order.services;

import edu.drexel.TrainDemo.order.models.Order;
import edu.drexel.TrainDemo.order.models.OrderEntity;
import edu.drexel.TrainDemo.order.models.OrderItemEntity;
import edu.drexel.TrainDemo.trips.models.Itinerary;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(List<Itinerary> purchasedItinerary) {
        if (purchasedItinerary.size() == 0) {
            throw new IllegalArgumentException("Cannot create order with 0 purchased items");
        }
        List<OrderItemEntity> items = new ArrayList<>();

        for (Itinerary itinerary : purchasedItinerary) {
            OrderItemEntity item = new OrderItemEntity(itinerary.getTrip().getId());
            items.add(item);
        }

        OrderEntity orderEntity = this.createOrderEntity();

        Order newOrder = new Order(orderEntity, items);

        return saveOrder(newOrder);
    }

    public OrderEntity createOrderEntity() {
        return new OrderEntity();
    }

    public Order saveOrder(Order orderEntity) {
        return orderRepository.save(orderEntity);
    }
}
