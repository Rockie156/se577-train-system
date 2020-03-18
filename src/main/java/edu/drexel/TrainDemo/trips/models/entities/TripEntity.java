package edu.drexel.TrainDemo.trips.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "trip")
public class TripEntity {

    @Id
    private long id;
    private long routeId;
    private long calendarId;
    private String headsign;
    private boolean direction;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private List<StopTimeEntity> stops;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<StopTimeEntity> getStops() {
        return stops;
    }

    public StopTimeEntity getStopByStationId(String stopId) {
        for (StopTimeEntity stop : stops) {
            if (stopId.equals(stop.getStation().getId())) {
                return stop;
            }
        }
        return null;
    }

    public StopTimeEntity getStopByStopSequence(int i) {
        return stops.get(i);
    }

    @Override
    public String toString() {
        return "TripEntity{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", calendarId=" + calendarId +
                ", headsign='" + headsign + '\'' +
                ", direction=" + direction +
                '}';
    }
}