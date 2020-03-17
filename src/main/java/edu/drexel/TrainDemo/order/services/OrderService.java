package edu.drexel.TrainDemo.order.services;

import edu.drexel.TrainDemo.checkout.models.Billing;
import edu.drexel.TrainDemo.order.models.ItineraryEntity;
import edu.drexel.TrainDemo.order.models.OrderEntity;
import edu.drexel.TrainDemo.order.models.SegmentEntity;
import edu.drexel.TrainDemo.order.repositories.ItineraryRepository;
import edu.drexel.TrainDemo.order.repositories.OrderRepository;
import edu.drexel.TrainDemo.order.repositories.SegmentRepository;
import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.models.Segment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ItineraryRepository itineraryRepository;
    private SegmentRepository segmentRepository;

    public OrderService(OrderRepository orderRepository, ItineraryRepository itineraryRepository, SegmentRepository segmentRepository) {
        this.orderRepository = orderRepository;
        this.itineraryRepository = itineraryRepository;
        this.segmentRepository = segmentRepository;
    }

    public OrderEntity createOrder(List<Itinerary> purchasedItinerary, Billing billing) {
        if (purchasedItinerary.size() == 0) {
            throw new IllegalArgumentException("Cannot create order with 0 purchased items");
        }

        // Save the order to database
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setEmail(billing.getCustomerEmail());
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);

        // Save each itinerary to the database
        for (Itinerary itinerary : purchasedItinerary) {
            ItineraryEntity itineraryEntity = new ItineraryEntity();
            itineraryEntity.setOrder_id(savedOrderEntity.getId());
            ItineraryEntity savedItinerary = itineraryRepository.save(itineraryEntity);

            // Save each segment to the database
            for (int sequenceId = 0; sequenceId < itinerary.getSegments().size(); sequenceId++) {
                Segment currentSegment = itinerary.getSegments().get(sequenceId);
                SegmentEntity segment = new SegmentEntity(currentSegment.getTrip(), currentSegment.getFrom(), currentSegment.getTo(), sequenceId);
                segment.setItinerary(savedItinerary);
                segmentRepository.save(segment);
            }
        }

        return orderRepository.findById(savedOrderEntity.getId()).get();
    }

    public OrderEntity saveOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }
}
