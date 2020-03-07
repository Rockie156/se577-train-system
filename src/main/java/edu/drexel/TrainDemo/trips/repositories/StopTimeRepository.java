package edu.drexel.TrainDemo.trips.repositories;

import edu.drexel.TrainDemo.trips.models.entities.StopTimeEntity;
import edu.drexel.TrainDemo.trips.models.entities.StopTimeEntityIdentity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StopTimeRepository extends CrudRepository<StopTimeEntity, StopTimeEntityIdentity> {
//    List<StationEntity> findByStation_Id

    List<StopTimeEntity> findByStation_Id(String stationId);
}