package edu.drexel.TrainDemo.trips.models.entities;

import javax.persistence.*;
import java.sql.Time;

@Entity(name = "stop_time")
public class StopTimeEntity {

    @EmbeddedId
    private StopTimeEntityIdentity id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_id")
    private StationEntity station;

    private Time arrivalTime;
    private Time departureTime;

    protected StopTimeEntity() {

    }

    @Override
    public String toString() {
        return "Fake";
//        return "StopTime{" +
//                "id={" + id +
//                ", station=" + station +
//                ", id=" + id +
//                ", arrivalTime=" + arrivalTime +
//                ", departureTime=" + departureTime +
//                '}';
    }
}