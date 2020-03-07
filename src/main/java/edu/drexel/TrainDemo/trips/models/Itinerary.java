package edu.drexel.TrainDemo.trips.models;

import edu.drexel.TrainDemo.trips.models.entities.StationEntity;

public class Itinerary {
    private StationEntity from;
    private StationEntity to;

    public Itinerary(StationEntity from, StationEntity to) {
        this.from = from;
        this.to = to;
    }

    public StationEntity getFrom() {
        return from;
    }

    public StationEntity getTo() {
        return to;
    }
}
