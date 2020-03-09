package edu.drexel.TrainDemo.cart.controllers;

import com.sun.tools.jdi.ThreadReferenceImpl;
import edu.drexel.TrainDemo.cart.models.Cart;
import edu.drexel.TrainDemo.cart.models.CartItem;
import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.models.entities.StationEntity;
import edu.drexel.TrainDemo.trips.models.entities.TripEntity;
import edu.drexel.TrainDemo.user.models.User;
import edu.drexel.TrainDemo.user.repositories.UserRepository;
import edu.drexel.TrainDemo.user.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @PostMapping("/cart/add")
    @ResponseBody
    public CartItem addToCart(@ModelAttribute CartItem cartItem) {
        return cartItem;
    }

    @GetMapping("/cart/view")
    @ResponseBody
    public Cart viewCart(HttpSession session) {
        return (Cart) session.getAttribute(Cart.getShoppingCartConstant());
    }

}
