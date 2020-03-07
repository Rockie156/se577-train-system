package edu.drexel.TrainDemo.trips.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="stop")
public class Station {

    @Id
    private String id;
    private String name;
    private BigDecimal lat;
    private BigDecimal lon;

    protected Station() {
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}