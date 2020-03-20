package edu.drexel.TrainDemo.admin;

import edu.drexel.TrainDemo.ControllerUtil;
import edu.drexel.TrainDemo.admin.controllers.AdminController;
import org.junit.jupiter.api.Test;

public class AdminControllerTest {

    @Test
    public void test_admin_controller_endpoints_start_with_admin() {
        ControllerUtil.verifyControllerHasPrefix(AdminController.class, "/admin");
    }
}
