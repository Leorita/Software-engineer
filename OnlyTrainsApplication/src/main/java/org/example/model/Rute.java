package org.example.model;

import java.util.ArrayList;

public class Rute {
    // Klassen skal inneholde alle stopp for hvert tog
    private String id;
    private String name;
    private boolean outbound; // True hvis ruten går mot sentrum, false hvis den går ut fra sentrum
    ArrayList<Station> stops = new ArrayList<>();
    //Legge til tider her??
    
    public Rute(String id) {
        this.id = id;
    }

    public Rute(String id, ArrayList<Station> stops) {
        this.id = id;
        this.stops = stops;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}

    public ArrayList<Station> getStops() {
        return stops;
    }

    public void addStop(Station stop) {

        this.stops.add(stop);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOutbound() {
        return outbound;
    }

    public void setOutbound(boolean outbound) {
        this.outbound = outbound;
    }

    @Override
    public String toString() {
        return "Rute{" +
                "id='" + id + '\'' +
                ", stops=" + stops +
                '}';
    }
}
