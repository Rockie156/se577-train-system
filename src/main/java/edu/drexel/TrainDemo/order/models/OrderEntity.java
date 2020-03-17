package edu.drexel.TrainDemo.order.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "order_tbl")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private String email;

    @OneToMany(mappedBy = "order_id", cascade = {CascadeType.ALL})
    private List<ItineraryEntity> itineraries;

    public OrderEntity() {

    }

    public OrderEntity(String email, List<ItineraryEntity> itineraries) {
        this.email = email;
        this.itineraries = itineraries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ItineraryEntity> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<ItineraryEntity> itineraries) {
        this.itineraries = itineraries;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", itineraries=" + itineraries +
                '}';
    }
}
