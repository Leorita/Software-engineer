package org.example.model;

import java.util.ArrayList;

public class Rute {
    // Klassen skal inneholde alle stopp for hvert tog
    private String id;
    ArrayList<Station> stops = new ArrayList<>();

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

    public void setStops(ArrayList<Station> stops) {
        this.stops = stops;
    }
}
