package edu.drexel.TrainDemo.trips.models;

import javax.persistence.*;
import java.sql.Time;

@Entity(name = "stop_time")
public class StopTime {

    @EmbeddedId
    private StopTimeIdentity id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_id")
    private Station station;

    private Time arrivalTime;
    private Time departureTime;

    protected StopTime() {

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