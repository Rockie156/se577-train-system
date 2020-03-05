package edu.drexel.TrainDemo.user.models;

import javax.persistence.*;

@Entity(name="user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private long externalId;

    protected User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getExternalId() {
        return externalId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + "\'" +
                ", email='" + email + "\'" +
                ", external_id='" + externalId +
                '}';
    }
}