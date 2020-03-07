package edu.drexel.TrainDemo.trips.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "stop")
public class StationEntity {

    @Id
    private String id;
    private String name;
    private BigDecimal lat;
    private BigDecimal lon;

    protected StationEntity() {
    }

    public StationEntity(String id, String name, BigDecimal lat, BigDecimal lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public BigDecimal getLon() {
        return lon;
    }
}