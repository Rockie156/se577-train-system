package edu.drexel.TrainDemo.checkout;

import edu.drexel.TrainDemo.ControllerUtil;
import edu.drexel.TrainDemo.checkout.controllers.CheckoutController;
import org.junit.jupiter.api.Test;

public class CheckoutControllerTest {

    @Test
    public void test_checkout_controller_endpoints_start_with_checkout() {
        ControllerUtil.verifyControllerHasPrefix(CheckoutController.class, "/checkout");
    }
}
