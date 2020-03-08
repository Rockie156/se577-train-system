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

    public int getStopSequence() {
        return getId().getStopSequence();
    }

    public StopTimeEntityIdentity getId() {
        return id;
    }

    public StationEntity getStation() {
        return station;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }
}