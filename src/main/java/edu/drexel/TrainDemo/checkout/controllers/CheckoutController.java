package edu.drexel.TrainDemo.checkout.controllers;

import edu.drexel.TrainDemo.cart.controllers.CartController;
import edu.drexel.TrainDemo.cart.models.Cart;
import edu.drexel.TrainDemo.checkout.models.Billing;
import edu.drexel.TrainDemo.checkout.models.CheckoutError;
import edu.drexel.TrainDemo.checkout.models.Order;
import edu.drexel.TrainDemo.trips.repositories.StationRepository;
import edu.drexel.TrainDemo.trips.repositories.TripRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class CheckoutController {
    private CartController cartController;

    public CheckoutController(StationRepository stationRepository, TripRepository tripRepository) {
        this.cartController = new CartController(stationRepository, tripRepository);
    }

    @GetMapping("/checkout")
    public String viewCheckoutForm(Model model) {
        CheckoutError error = (CheckoutError) model.getAttribute("error");
        model.addAttribute("CustomerBilling", new Billing());
        model.addAttribute("error", error);
        return "checkout/checkout_form";
    }

    @PostMapping("/checkout/submit")
    public String submitOrder(HttpSession session, @ModelAttribute Billing billingData, Model model, RedirectAttributes redirectAttributes) {
        if (billingData.getCreditCard() == null) {
            redirectAttributes.addFlashAttribute("error", new CheckoutError("Did not receive billing information"));
            return "redirect:/checkout";
        }
        Cart cart = cartController.getOrCreateCart(session);
        cart.getItems();
        Order newOrder = new Order(cart, billingData);
        // saveOrder(newOrder);
        // resetCart(session);
        System.out.println("Cart Data: " + cart);
        System.out.println("Billing Data: " + billingData);
        System.out.println("Order Data: " + newOrder);
        model.addAttribute("lastSuccessfulOrder", newOrder);
        return "checkout/checkout_success";
    }
}
