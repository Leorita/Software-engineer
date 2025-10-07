package org.example.model;

import java.time.LocalDateTime;

public class StationTimetable {
    // Denne skal holde på alle avgangstider og ankomsttider til tog x på stasjon y
    private Station station;
    private ScheduledTime scheduledDeparturesAndArrivals;
    private Train tog; // inneholder id'en til hvert tog
    private String origin;
    private String destination;
    private int platform;




    public StationTimetable() {

    }

    public StationTimetable(Station station, ScheduledTime scheduledDeparturesAndArrivals, Train tog, String origin, String destination, int platform) {
        this.station = station;
        this.scheduledDeparturesAndArrivals = scheduledDeparturesAndArrivals;
        this.tog = tog;
        this.origin = origin;
        this.destination = destination;
        this.platform = platform;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public ScheduledTime getScheduledDeparturesAndArrivals() {
        return scheduledDeparturesAndArrivals;
    }

    public void setScheduledDeparturesAndArrivals(ScheduledTime scheduledDeparturesAndArrivals) {
        this.scheduledDeparturesAndArrivals = scheduledDeparturesAndArrivals;
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

    @Override
    public String toString() {
        return "StationTimetable{\n" +
                "  station=" + station.getName() + ",\n" +
                "  ScheduledDeparture=" + scheduledDeparturesAndArrivals.getScheduledDeparture() + ",\n" +
                "  ScheduledArrival=" + scheduledDeparturesAndArrivals.getScheduledArrival() + ",\n" +
                "  tog=" + tog.getId() + ",\n" +
                "  origin='" + origin + ",\n" +
                "  destination='" + destination + ",\n" +
                "  platform=" + platform + ",\n" +
                '}';
    }
}


