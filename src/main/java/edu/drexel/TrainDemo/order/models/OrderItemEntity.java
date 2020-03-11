package edu.drexel.TrainDemo.order.models;

import javax.persistence.*;

@Entity(name = "order_item_tbl")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private long tripId;

//    @ManyToOne
//    @JoinColumn(name = "trip_id")
//    private TripEntity trip;

    public OrderItemEntity() {

    }

//    public OrderItemEntity(TripEntity trip) {
//        this.trip = trip;
//    }

    public OrderItemEntity(long tripId) {
        this.tripId = tripId;
    }

    public Long getId() {
        return id;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long trip_id) {
        this.tripId = trip_id;
    }

//    public TripEntity getTrip() {
//        return trip;
//    }
//
//    public void setTrip(TripEntity trip) {
//        this.trip = trip;
//    }
//    @ManyToOne
//    @JoinColumn(name = "start_id")
//    private StopTimeEntity startStopTime;
//
//    @ManyToOne
//    @JoinColumn(name = "stop_id")
//    private StopTimeEntity endStopTime;

}
