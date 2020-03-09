package edu.drexel.TrainDemo.cart.models;

import java.sql.Time;

public class CartItem {
    private Long tripId;
    private String fromId;
    private String toId;
    private Time departure;
    private Time arrival;


    public CartItem(Long tripId, String fromId, String toId, Time departure, Time arrival) {
        this.tripId = tripId;
        this.fromId = fromId;
        this.toId = toId;
        this.departure = departure;
        this.arrival = arrival;
    }

    public Long getTripId() {
        return tripId;
    }

    public String getFromId() {
        return fromId;
    }

    public String getToId() {
        return toId;
    }

    public Time getDeparture() {
        return departure;
    }

    public Time getArrival() {
        return arrival;
    }
}
