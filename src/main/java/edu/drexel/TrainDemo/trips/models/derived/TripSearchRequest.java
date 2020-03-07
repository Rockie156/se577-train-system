package edu.drexel.TrainDemo.trips.models.derived;

public class TripSearchRequest {
    private String from;

    public TripSearchRequest() {

    }

    public TripSearchRequest(String from) {
        this.from = from;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}

