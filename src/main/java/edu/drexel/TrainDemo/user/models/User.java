package edu.drexel.TrainDemo.user.models;

import javax.persistence.*;

@Entity(name = "user_tbl")
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

    public User(Long id, String name, String email, long externalId) {
        this(name, email, externalId);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
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