package edu.drexel.TrainDemo.trips.repositories;

import edu.drexel.TrainDemo.trips.models.entities.TripEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends CrudRepository<TripEntity, Long> {

    @Query("SELECT t FROM trip t left join stop_time on stop_time.id.trip.id = t.id where stop_time.station.id =:stationId")
    List<TripEntity> findByStation(@Param("stationId") String stationId);
}