package edu.drexel.TrainDemo.trips.models;

import edu.drexel.TrainDemo.trips.models.entities.StopTimeEntity;

public class Itinerary {
    private StopTimeEntity from;
    private StopTimeEntity to;

    public Itinerary(StopTimeEntity from, StopTimeEntity to) {
        this.from = from;
        this.to = to;
    }

    public StopTimeEntity getFrom() {
        return from;
    }

    public StopTimeEntity getTo() {
        return to;
    }
}
