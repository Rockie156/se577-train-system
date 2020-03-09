package edu.drexel.TrainDemo.cart.services;

import edu.drexel.TrainDemo.cart.models.CartItem;
import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.services.TripService;

import java.sql.Time;

public class CartService {
    private TripService tripService;

    public CartService(TripService tripService) {
        this.tripService = tripService;
    }

    public Itinerary convertCartItemToItinerary(CartItem cartItem) {
        Long tripId = cartItem.getTripId();
        String fromId = cartItem.getFromId();
        String toId = cartItem.getToId();
        Time departure = cartItem.getDeparture();
        Time arrival = cartItem.getArrival();
        return tripService.findItinerary(tripId, fromId, toId, departure, arrival);
    }
}
