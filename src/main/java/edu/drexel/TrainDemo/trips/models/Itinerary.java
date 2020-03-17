package edu.drexel.TrainDemo.trips.models;

import edu.drexel.TrainDemo.trips.models.entities.StationEntity;
import edu.drexel.TrainDemo.trips.models.entities.StopTimeEntity;
import edu.drexel.TrainDemo.trips.models.entities.TripEntity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    public List<Segment> segments;

    public Itinerary() {
        this.segments = new ArrayList<>();
    }

    public Itinerary(List<Segment> segments) {
        this.segments = segments;
    }

    public Itinerary(TripEntity trip, StationEntity from, StationEntity to, Time departure, Time arrival) {
        this();
        Segment segment = new Segment(trip, from, to, departure, arrival);
        segments.add(segment);
    }

    public Itinerary(TripEntity trip, StopTimeEntity from, StopTimeEntity to) {
        this(trip, from.getStation(), to.getStation(), from.getDepartureTime(), to.getArrivalTime());
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public StationEntity getFrom() {
        return getFirstSegment().getFrom();
    }

    public StationEntity getTo() {
        return getLastSegment().getTo();
    }

    public Time getDeparture() {
        return getFirstSegment().getDeparture();
    }

    public Time getArrival() {
        return getLastSegment().getArrival();
    }

    public Segment getFirstSegment() {
        return segments.get(0);
    }

    public Segment getLastSegment() {
        return segments.get(segments.size() - 1);
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "segments=" + segments +
                '}';
    }
}
