package org.example.model;

import java.time.LocalDateTime;

public class StationTimetable {
    // Denne skal holde på alle avgangstider og ankomsttider til tog x på stasjon y
    private Station station;
    private ScheduledTime ScheduledDeparturesAndArrivals;
    private Train tog; // inneholder id'en til hvert tog
    private String origin;
    private String destination;
    private int platform;




    public StationTimetable() {

    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public ScheduledTime getScheduledDeparturesAndArrivals() {
        return ScheduledDeparturesAndArrivals;
    }

    public void setScheduledDeparturesAndArrivals(ScheduledTime scheduledDeparturesAndArrivals) {
        ScheduledDeparturesAndArrivals = scheduledDeparturesAndArrivals;
    }

    public Train getTog() {
        return tog;
    }

    public void setTog(Train tog) {
        this.tog = tog;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }
}


