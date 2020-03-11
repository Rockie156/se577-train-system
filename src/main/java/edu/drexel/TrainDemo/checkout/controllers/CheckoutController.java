package edu.drexel.TrainDemo.checkout.controllers;

import edu.drexel.TrainDemo.cart.controllers.CartController;
import edu.drexel.TrainDemo.cart.models.Cart;
import edu.drexel.TrainDemo.checkout.models.Billing;
import edu.drexel.TrainDemo.checkout.models.CheckoutError;
import edu.drexel.TrainDemo.order.controllers.OrderController;
import edu.drexel.TrainDemo.order.models.Order;
import edu.drexel.TrainDemo.order.services.OrderRepository;
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
    private OrderController orderController;

    public CheckoutController(StationRepository stationRepository, TripRepository tripRepository, OrderRepository orderRepository) {
        this.cartController = new CartController(stationRepository, tripRepository);
        this.orderController = new OrderController(orderRepository);
    }

    @GetMapping("/checkout")
    public String viewCheckoutForm(Model model) {
        CheckoutError error = (CheckoutError) model.getAttribute("error");
        model.addAttribute("error", error);
        model.addAttribute("CustomerBilling", new Billing("1234 5678 8888 8888"));
        return "checkout/checkout_form";
    }

    @PostMapping("/checkout/submit")
    public String submitOrder(@ModelAttribute Billing billing, RedirectAttributes redirectAttributes, HttpSession session, Model model) {
        if (billing.getCreditCard() == null) {
            redirectAttributes.addFlashAttribute("error", new CheckoutError("Did not receive billing information"));
            return "redirect:/checkout";
        }
        Cart cart = cartController.getOrCreateCart(session);
        edu.drexel.TrainDemo.checkout.models.Order newOrder = new edu.drexel.TrainDemo.checkout.models.Order(cart, billing);
        Order newOrderEntity = orderController.createOrder(cart.getItems());
        // resetCart(session);
        // model.addAttribute("orderEntity", newOrderEntity);
        model.addAttribute("lastSuccessfulOrder", newOrder);
        return "checkout/checkout_success";
    }
}
