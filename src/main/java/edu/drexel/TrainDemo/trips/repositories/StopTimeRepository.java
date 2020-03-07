package edu.drexel.TrainDemo.trips.repositories;

import edu.drexel.TrainDemo.trips.models.Route;
import edu.drexel.TrainDemo.trips.models.StopTime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StopTimeRepository extends CrudRepository<StopTime, Long> {
    List<StopTime> findByStation_Id(String stationId);
}