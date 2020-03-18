package edu.drexel.TrainDemo.trips.models.entities;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity(name = "trip")
public class TripEntity {

    @Id
    private long id;
    private long routeId;
    private String headsign;
    private boolean direction;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private CalendarEntity calendarEntity;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private List<StopTimeEntity> stops;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<StopTimeEntity> getStops() {
        return stops;
    }

    public StopTimeEntity getStopByStationId(String stopId) {
        for (StopTimeEntity stop : stops) {
            if (stopId == stop.getStation().getId()) {
                return stop;
            }
        }
        return null;
    }

    public boolean isTripOnDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        DayOfWeek dayOfWeek  = localDate.getDayOfWeek();

        switch (dayOfWeek)
        {
            case SUNDAY:
                return calendarEntity.isSunday();
            case MONDAY:
                return calendarEntity.isMonday();
            case TUESDAY:
                return calendarEntity.isTuesday();
            case WEDNESDAY:
                return calendarEntity.isWednesday();
            case THURSDAY:
                return calendarEntity.isThursday();
            case FRIDAY:
                return calendarEntity.isFriday();
            case SATURDAY:
                return calendarEntity.isSaturday();
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return "TripEntity{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", calendarId=" + calendarId +
                ", headsign='" + headsign + '\'' +
                ", direction=" + direction +
                '}';
    }
}
