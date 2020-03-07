package edu.drexel.TrainDemo.trips.repositories;

import edu.drexel.TrainDemo.trips.models.StopTimeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StopTimeRepository extends CrudRepository<StopTimeEntity, Long> {
    List<StopTimeEntity> findByStation_Id(String stationId);
}