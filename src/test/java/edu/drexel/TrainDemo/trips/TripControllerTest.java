package edu.drexel.TrainDemo.trips;

import edu.drexel.TrainDemo.ControllerUtil;
import edu.drexel.TrainDemo.trips.controllers.TripController;
import org.junit.jupiter.api.Test;

public class TripControllerTest {

    @Test
    public void test_trip_controller_endpoints_start_with_trip() {
        ControllerUtil.verifyControllerHasPrefix(TripController.class, "/trips");
    }
}
