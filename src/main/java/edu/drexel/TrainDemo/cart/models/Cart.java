package edu.drexel.TrainDemo.cart.models;

import edu.drexel.TrainDemo.trips.models.Itinerary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class Cart {
    public List<Itinerary> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<Itinerary> getItems() {
        return items;
    }

    public void addItem(Itinerary newItinerary) {
        items.add(newItinerary);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
