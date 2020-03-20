package edu.drexel.TrainDemo.admin.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "payment_tbl")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String name;
		private boolean isEnabled;

    public PaymentEntity() { }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name; 
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean getIsEnabled() {
        return isEnabled; 
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "name=" + name +
						 '}';
    }
}
