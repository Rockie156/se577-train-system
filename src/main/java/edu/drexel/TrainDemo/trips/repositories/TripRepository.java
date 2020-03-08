package edu.drexel.TrainDemo.trips.repositories;

import edu.drexel.TrainDemo.trips.models.entities.TripEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends CrudRepository<TripEntity, Long> {

//    @Query("SELECT t FROM trip t WHERE :stationOneId in (t.stops) AND :stationTwoId in (t.stops)")
//    List<TripEntity> findByStations(String stationOneId, String stationTwoId);

    List<TripEntity> findByStops_Station_Id(String stationId);
}