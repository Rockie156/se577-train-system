package edu.drexel.TrainDemo.order.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "order_x_item_tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    private List<OrderItemEntity> orderItemEntityList;

    public Order() {

    }

    public Order(OrderEntity orderEntity, List<OrderItemEntity> items) {
        this.orderEntity = orderEntity;
        this.orderItemEntityList = items;
    }
}
