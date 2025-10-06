package org.example.model;

import java.time.LocalDateTime;

public class StationTimetable {
    // Denne skal holde på alle avgangstider og ankomsttider til tog x på stasjon y
    private Station station;
    private LocalDateTime expectedArrival;
    private LocalDateTime expectedDeparture;
    private String togID; // inneholder id'en til hvert tog
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

    public LocalDateTime getExpectedArrival() {
        return expectedArrival;
    }

    public void setExpectedArrival(LocalDateTime expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public LocalDateTime getExpectedDeparture() {
        return expectedDeparture;
    }

    public void setExpectedDeparture(LocalDateTime expectedDeparture) {
        this.expectedDeparture = expectedDeparture;
    }

    public String getTogID() {
        return togID;
    }

    public void setTogID(String togID) {
        this.togID = togID;
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


