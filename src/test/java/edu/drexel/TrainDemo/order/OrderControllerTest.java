package edu.drexel.TrainDemo.order;

import edu.drexel.TrainDemo.ControllerUtil;
import edu.drexel.TrainDemo.order.controllers.OrderController;
import org.junit.jupiter.api.Test;

public class OrderControllerTest {

    @Test
    public void test_order_controller_endpoints_start_with_order() {
        ControllerUtil.verifyControllerHasPrefix(OrderController.class, "/order");
    }
}
