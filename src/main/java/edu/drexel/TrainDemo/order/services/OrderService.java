package edu.drexel.TrainDemo.order.services;

import edu.drexel.TrainDemo.checkout.models.Billing;
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

    public Order createOrder(List<Itinerary> purchasedItinerary, Billing billing) {
        if (purchasedItinerary.size() == 0) {
            throw new IllegalArgumentException("Cannot create order with 0 purchased items");
        }

        List<OrderItemEntity> items = new ArrayList<>();

        for (int sequenceId = 0; sequenceId < purchasedItinerary.size(); sequenceId++) {
            Itinerary itinerary = purchasedItinerary.get(sequenceId);
            OrderItemEntity item = new OrderItemEntity(itinerary.getTrip(), itinerary.getFrom(), itinerary.getTo(), sequenceId);
            items.add(item);
        }

        OrderEntity orderEntity = this.createOrderEntity(billing.getCustomerEmail());

        Order newOrder = new Order(orderEntity, items);

        return saveOrder(newOrder);
    }

    public OrderEntity createOrderEntity(String customerEmail) {
        return new OrderEntity(customerEmail);
    }

    public Order saveOrder(Order orderEntity) {
        return orderRepository.save(orderEntity);
    }
}
