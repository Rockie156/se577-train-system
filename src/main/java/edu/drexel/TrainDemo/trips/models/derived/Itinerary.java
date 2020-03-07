package edu.drexel.TrainDemo.trips.models.derived;

import edu.drexel.TrainDemo.trips.models.StationEntity;

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
