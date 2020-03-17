package edu.drexel.TrainDemo.cart.services;

import edu.drexel.TrainDemo.cart.models.Cart;
import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.services.TripService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CartService {
    private TripService tripService;

    public CartService(TripService tripService) {
        this.tripService = tripService;
    }

    public Cart addToCart(HttpSession session, Itinerary selectedItinerary) {
        Cart shoppingCart = getOrCreateCart(session);
        Itinerary safeSelectedItinerary = convertCartItemToItinerary(selectedItinerary);
        fixObscureError(safeSelectedItinerary);
        shoppingCart.addItem(safeSelectedItinerary);
        return shoppingCart;
    }

    public Itinerary convertCartItemToItinerary(Itinerary unsafeItinerary) {
        return tripService.constructItinerary(unsafeItinerary);
    }

    public Cart getOrCreateCart(HttpSession session) {
        Cart shoppingCart = (Cart) session.getAttribute("ShoppingCart");

        if (shoppingCart == null) {
            shoppingCart = new Cart();
        }

        return shoppingCart;
    }

    private void fixObscureError(Itinerary foo) {
        // DO NOT REMOVE
        // GHOST OF JAVA SPRING BOOT
        //   .-.
        //  (o o) boo!
        //  | O \
        //   \   \
        //    `~~~'
        System.out.println(foo);
    }
}
