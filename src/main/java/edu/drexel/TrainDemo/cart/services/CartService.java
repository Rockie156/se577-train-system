package edu.drexel.TrainDemo.cart.services;

import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.services.TripService;

import java.sql.Time;

public class CartService {
    private TripService tripService;

    public CartService(TripService tripService) {
        this.tripService = tripService;
    }

    public Itinerary convertCartItemToItinerary(Itinerary itinerary) {
        Long tripId = itinerary.getTrip().getId();
        String fromId = itinerary.getFrom().getId();
        String toId = itinerary.getTo().getId();
        Time departure = itinerary.getDeparture();
        Time arrival = itinerary.getArrival();
        return tripService.findItinerary(tripId, fromId, toId, departure, arrival);
    }
}
