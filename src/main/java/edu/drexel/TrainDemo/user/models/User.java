package edu.drexel.TrainDemo.user.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name="user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String name;
    private String email;
    @Column(unique = true)
    private long externalId;

    public User() {
    }

    public User(String name, String email, long externalId) {
        this.name = name;
        this.email = email;
        this.externalId = externalId;
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
                ", external_id=" + externalId +
                '}';
    }
}