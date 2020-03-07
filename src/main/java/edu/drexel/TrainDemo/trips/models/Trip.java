package edu.drexel.TrainDemo.trips.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "trip")
public class Trip {

    @Id
    private long id;
    private long routeId;
    private long calendarId;
    private String headsign;
    private boolean direction;

    protected Trip() {
    }
}