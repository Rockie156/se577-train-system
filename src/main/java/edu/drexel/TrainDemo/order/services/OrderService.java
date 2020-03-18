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
import java.util.Optional;

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
        OrderEntity savedOrderEntity = createOrderEntity(billing);

        // Save each itinerary to the database
        for (Itinerary unsafeItinerary : purchasedItinerary) {
            saveItinerary(unsafeItinerary, savedOrderEntity);
        }

        return fetchOrderEntity(savedOrderEntity);
    }

    public OrderEntity createOrderEntity(Billing billing) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setEmail(billing.getCustomerEmail());
        return orderRepository.save(orderEntity);
    }

    public void saveItinerary(Itinerary itinerary, OrderEntity parentOrder) {
        ItineraryEntity savedItinerary = createItineraryEntity(parentOrder);

        // Save each segment to the database
        for (int sequenceId = 0; sequenceId < itinerary.getSegments().size(); sequenceId++) {
            createSegmentEntity(itinerary, savedItinerary, sequenceId);
        }
    }

    public ItineraryEntity createItineraryEntity(OrderEntity parentOrder) {
        ItineraryEntity itineraryEntity = new ItineraryEntity();
        itineraryEntity.setOrder_id(parentOrder.getId());
        return itineraryRepository.save(itineraryEntity);
    }

    public SegmentEntity createSegmentEntity(Itinerary unsafeItinerary, ItineraryEntity parent, Integer sequenceId) {
        Segment unsafeSegment = unsafeItinerary.getSegments().get(sequenceId);
        SegmentEntity segment = new SegmentEntity(unsafeSegment.getTrip(), unsafeSegment.getFrom(), unsafeSegment.getTo(), sequenceId);
        segment.setItinerary(parent);
        return segmentRepository.save(segment);
    }

    public OrderEntity fetchOrderEntity(OrderEntity unsafeOrderEntity) {
        Long id = unsafeOrderEntity.getId();
        return findById(id);
    }

    public OrderEntity findById(Long id) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isEmpty()) {
            return null;
        }
        return orderEntityOptional.get();
    }

    public OrderEntity saveOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }
}
