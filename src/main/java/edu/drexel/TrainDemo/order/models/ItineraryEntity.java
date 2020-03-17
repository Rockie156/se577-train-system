package edu.drexel.TrainDemo.order.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "order_itinerary_tbl")
public class ItineraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private Long order_id;

    @OneToMany
    @JoinColumn(name = "itinerary_id")
    private List<SegmentEntity> segments;

    public ItineraryEntity() {

    }

    public ItineraryEntity(List<SegmentEntity> segments) {
        this.segments = segments;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SegmentEntity> getSegments() {
        return segments;
    }

    public void setSegments(List<SegmentEntity> segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        return "ItineraryEntity{" +
                "id=" + id +
                ", segments=" + segments +
                '}';
    }
}
