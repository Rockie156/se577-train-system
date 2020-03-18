package edu.drexel.TrainDemo.trips.models;

import edu.drexel.TrainDemo.trips.models.entities.StationEntity;
import edu.drexel.TrainDemo.trips.models.entities.StopTimeEntity;
import edu.drexel.TrainDemo.trips.models.entities.TripEntity;

import java.sql.Time;

public class Itinerary {
    private TripEntity trip;
    private StationEntity from;
    private StationEntity to;
    private Time departure;
    private Time arrival;
    private String departureDate;
  
    public Itinerary() {

    }

    public Itinerary(TripEntity trip,
                     StationEntity from,
                     StationEntity to,
                     Time departure,
                     Time arrival,
                     String departureDate) {
        this.trip = trip;
        this.from = from;
        this.to = to;
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
    }

    public Itinerary(TripEntity trip, StopTimeEntity from, StopTimeEntity to) {
        this.trip = trip;
        this.from = from.getStation();
        this.to = to.getStation();
        this.departure = from.getDepartureTime();
        this.arrival = to.getArrivalTime();
    }

    public TripEntity getTrip() {
        return trip;
    }

    public void setTrip(TripEntity trip) {
        this.trip = trip;
    }

    public StationEntity getFrom() {
        return from;
    }

    public void setFrom(StationEntity from) {
        this.from = from;
    }

    public StationEntity getTo() {
        return to;
    }

    public void setTo(StationEntity to) {
        this.to = to;
    }

    public Time getDeparture() {
        return departure;
    }

    public void setDeparture(Time departure) {
        this.departure = departure;
    }

    public Time getArrival() {
        return arrival;
    }

    public void setArrival(Time arrival) {
        this.arrival = arrival;
    }
  
    public String getDepartureDate() { 
      return departureDate;
    }
  
    public String setDepartureDate(String departureDate) { 
      this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "trip=" + trip +
                ", from=" + from +
                ", to=" + to +
                ", departure=" + departure +
                ", arrival=" + arrival +
                '}';
    }
}
