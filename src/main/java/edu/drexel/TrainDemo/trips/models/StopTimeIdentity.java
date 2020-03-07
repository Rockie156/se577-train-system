package edu.drexel.TrainDemo.trips.models;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StopTimeIdentity implements Serializable {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;


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
        StopTimeIdentity that = (StopTimeIdentity) o;
        return stopSequence == that.stopSequence &&
                trip.equals(that.trip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trip, stopSequence);
    }
}