package edu.drexel.TrainDemo.user;

import edu.drexel.TrainDemo.ControllerUtil;
import edu.drexel.TrainDemo.user.controllers.UserController;
import org.junit.jupiter.api.Test;

public class UserControllerTest {

    @Test
    public void test_user_controller_endpoints_start_with_user() {
        ControllerUtil.verifyControllerHasPrefix(UserController.class, "/user");
    }
}
