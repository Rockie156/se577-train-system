package edu.drexel.TrainDemo.cart.controllers;

import edu.drexel.TrainDemo.cart.models.Cart;
import edu.drexel.TrainDemo.cart.models.CartItem;
import edu.drexel.TrainDemo.cart.services.CartService;
import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.repositories.StationRepository;
import edu.drexel.TrainDemo.trips.repositories.TripRepository;
import edu.drexel.TrainDemo.trips.services.TripService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {
    private CartService cartService;

    public CartController(StationRepository stationRepository, TripRepository tripRepository) {
        this.cartService = new CartService(new TripService(stationRepository, tripRepository));
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public Itinerary addToCart(@ModelAttribute CartItem selectedCartItem) {
        Itinerary selectedItinerary = cartService.convertCartItemToItinerary(selectedCartItem);
        return selectedItinerary;
    }

    @GetMapping("/cart/view")
    @ResponseBody
    public Cart viewCart(HttpSession session) {
        return (Cart) session.getAttribute(Cart.getShoppingCartConstant());
    }

}
