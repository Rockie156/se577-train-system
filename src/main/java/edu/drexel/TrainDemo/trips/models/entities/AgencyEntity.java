package edu.drexel.TrainDemo.trips.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "agency")
public class AgencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String externalUrl;

    protected AgencyEntity() {
    }

    public AgencyEntity(String name, String externalUrl) {
        this.name = name;
        this.externalUrl = externalUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", externalUrl='" + externalUrl + '\'' +
                '}';
    }
}