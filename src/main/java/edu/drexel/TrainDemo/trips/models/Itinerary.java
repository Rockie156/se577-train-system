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

    public int getNumStops() {
        return segments.size();
    }

    public String getPath() {
        String result = segments.get(0).getFrom().getId();
        for (Segment stop : segments) {
            result += "->" + stop.getTo().getId();
        }
        return result;
    }

    public boolean isValid() {
        for (int i = 0; i < segments.size() - 1; i++) {
            Segment start = segments.get(i);
            Segment end = segments.get(i + 1);

            // avoid transferring for no reason
            // e.g. when on the same route, don't transfer to a later trip
            if (start.getTrip().getRouteId() == end.getTrip().getRouteId()) {
                return false;
            }

            Time departure = start.getDeparture();
            Time arrival = end.getArrival();

            if (departure.after(arrival)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "segments=" + segments +
                '}';
    }
}
