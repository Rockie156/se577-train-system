package edu.drexel.TrainDemo.trips.models;

import javax.persistence.*;

@Entity(name = "route")
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private RouteEntityType routeType;
    private String externalUrl;

    @ManyToOne
    private AgencyEntity agency;

    protected RouteEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RouteEntityType getRouteType() {
        return routeType;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public AgencyEntity getAgency() {
        return agency;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", routeType=" + routeType +
                ", externalUrl='" + externalUrl + '\'' +
                ", agency=" + agency +
                '}';
    }
}