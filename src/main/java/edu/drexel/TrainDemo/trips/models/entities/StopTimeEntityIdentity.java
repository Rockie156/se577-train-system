package edu.drexel.TrainDemo.trips.models.entities;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StopTimeEntityIdentity implements Serializable {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private TripEntity trip;


    @NotNull
    private int stopSequence;

    @Override
    public String toString() {
        return "StopTimeIdentity{" +
                trip +
                ", stopSequence=" + stopSequence +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopTimeEntityIdentity that = (StopTimeEntityIdentity) o;
        return stopSequence == that.stopSequence &&
                trip.equals(that.trip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trip, stopSequence);
    }
}