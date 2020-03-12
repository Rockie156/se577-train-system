package edu.drexel.TrainDemo.order.models;

import javax.persistence.*;

@Entity(name = "order_tbl")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "email")
    private String customerEmail;

    public OrderEntity() {

    }

    public OrderEntity(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
