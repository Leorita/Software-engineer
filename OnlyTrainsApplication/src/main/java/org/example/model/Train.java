package org.example.model;

import java.util.ArrayList;

public class Train {
    // Denne klassen inneholder informasjon om tog ankomster og avganger

    public Train() {
    }

    public Train(String id, Rute route) {
        this.id = id;
        this.route = route;
    }

    private String id;// Variabel for ankomsttid
    private Rute route; // Ruten toget har

    //Denne listen inneholder alle stopp i ruten, og inkluderer avgangstider og ankomsttider
    private ArrayList<TrainStop> trainStops = new ArrayList<>();



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Rute getRoute() {
        return route;
    }

    public void setRoute(Rute route) {
        this.route = route;
    }


    @Override
    public String toString() {
        return "Train{" +
                "id='" + id + '\'' +
                ", route=" + route +
                ", trainStops=" + trainStops +
                '}';
    }
}
