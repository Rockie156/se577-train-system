package edu.drexel.TrainDemo.cart.controllers;

import edu.drexel.TrainDemo.cart.models.Cart;
import edu.drexel.TrainDemo.cart.services.CartService;
import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.repositories.StationRepository;
import edu.drexel.TrainDemo.trips.repositories.TripRepository;
import edu.drexel.TrainDemo.trips.services.TripService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {
    private CartService cartService;

    public CartController(StationRepository stationRepository, TripRepository tripRepository) {
        this.cartService = new CartService(new TripService(stationRepository, tripRepository));
    }

    @PostMapping("/cart/add")
    public String addToCart(HttpSession session, @ModelAttribute Itinerary selectedItinerary) {
        Cart shoppingCart = getOrCreateCart(session);
        Itinerary safeSelectedItinerary = cartService.convertCartItemToItinerary(selectedItinerary);
        fixObscureError(safeSelectedItinerary);
        shoppingCart.addItem(safeSelectedItinerary);
        session.setAttribute("ShoppingCart", shoppingCart);

        return "redirect:/cart/view";
    }

    @GetMapping("/cart/view")
    public String viewCart(HttpSession session, Model model) {
        Cart shoppingCart = getOrCreateCart(session);
        model.addAttribute("ShoppingCart", shoppingCart);

        return "cart/view_cart";
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

    public Cart getOrCreateCart(HttpSession session) {
        Cart shoppingCart = (Cart) session.getAttribute("ShoppingCart");

        if (shoppingCart == null) {
            shoppingCart = new Cart();
        }

        return shoppingCart;
    }

}
