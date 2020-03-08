package edu.drexel.TrainDemo.trips.models;

import edu.drexel.TrainDemo.trips.models.entities.StationEntity;
import edu.drexel.TrainDemo.trips.models.entities.TripEntity;

import java.sql.Time;

public class Itinerary {
    private TripEntity trip;
    private StationEntity from;
    private StationEntity to;
    private Time departure;
    private Time arrival;

    public Itinerary(TripEntity trip, StationEntity from, StationEntity to, Time departure, Time arrival) {
        this.trip = trip;
        this.from = from;
        this.to = to;
        this.departure = departure;
        this.arrival = arrival;
    }

    public TripEntity getTrip() {
        return trip;
    }

    public StationEntity getFrom() {
        return from;
    }

    public StationEntity getTo() {
        return to;
    }

    public Time getDeparture() {
        return departure;
    }

    public Time getArrival() {
        return arrival;
    }
}
