package edu.drexel.TrainDemo.cart.controllers;

import edu.drexel.TrainDemo.cart.models.Cart;
import edu.drexel.TrainDemo.cart.services.CartService;
import edu.drexel.TrainDemo.trips.models.Itinerary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart/add")
    public String addToCart(HttpSession session, @ModelAttribute Itinerary selectedItinerary) {
        Cart shoppingCart = cartService.addToCart(session, selectedItinerary);
        session.setAttribute("ShoppingCart", shoppingCart);

        return "redirect:/cart/view";
    }

    @GetMapping("/cart/view")
    public String viewCart(HttpSession session, Model model) {
        Cart shoppingCart = cartService.getOrCreateCart(session);
        model.addAttribute("ShoppingCart", shoppingCart);

        return "cart/view_cart";
    }

}
