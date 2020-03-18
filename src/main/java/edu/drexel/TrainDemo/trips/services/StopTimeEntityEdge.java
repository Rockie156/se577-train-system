package edu.drexel.TrainDemo.trips.services;

import edu.drexel.TrainDemo.trips.models.entities.StopTimeEntity;
import org.jgrapht.graph.DefaultEdge;


class StopTimeEntityEdge extends DefaultEdge {
    private StopTimeEntity from;
    private StopTimeEntity to;

    public StopTimeEntityEdge(StopTimeEntity from, StopTimeEntity to) {
        this.from = from;
        this.to = to;
    }

    public StopTimeEntity getFrom() {
        return from;
    }

    public void setFrom(StopTimeEntity from) {
        this.from = from;
    }

    public StopTimeEntity getTo() {
        return to;
    }

    public void setTo(StopTimeEntity to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "StopTimeEntityEdge{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}

