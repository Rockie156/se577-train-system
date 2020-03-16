package edu.drexel.TrainDemo.cart;

import edu.drexel.TrainDemo.ControllerUtil;
import edu.drexel.TrainDemo.cart.controllers.CartController;
import org.junit.jupiter.api.Test;

public class CartControllerTest {

    @Test
    public void test_cart_controller_endpoints_start_with_cart() {
        ControllerUtil.verifyControllerHasPrefix(CartController.class, "/cart");
    }
}
