package edu.drexel.TrainDemo.order.models;

import edu.drexel.TrainDemo.trips.models.entities.StationEntity;
import edu.drexel.TrainDemo.trips.models.entities.TripEntity;

import javax.persistence.*;

@Entity(name = "order_item_tbl")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private TripEntity trip;

    @ManyToOne
    @JoinColumn(name = "start_station_id")
    private StationEntity startStation;

    @ManyToOne
    @JoinColumn(name = "end_station_id")
    private StationEntity endStation;

    @Column(name = "sequence")
    private Integer stopSequence;

    public OrderItemEntity() {

    }


    public OrderItemEntity(TripEntity trip, StationEntity start, StationEntity end, int stopSequence) {
        this.trip = trip;
        this.startStation = start;
        this.endStation = end;
        this.stopSequence = stopSequence;
    }

    public Long getId() {
        return id;
    }

    public TripEntity getTrip() {
        return trip;
    }

    public void setTrip(TripEntity trip) {
        this.trip = trip;
    }

    public StationEntity getStartStation() {
        return startStation;
    }

    public void setStartStation(StationEntity startStation) {
        this.startStation = startStation;
    }

    public StationEntity getEndStation() {
        return endStation;
    }

    public void setEndStation(StationEntity endStation) {
        this.endStation = endStation;
    }

    public Integer getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(Integer stopSequence) {
        this.stopSequence = stopSequence;
    }
}
