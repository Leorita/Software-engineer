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

    public Train(String id, Rute route, ArrayList<TrainStop> trainStops) {
        this.id = id;
        this.route = route;
        this.trainStops = trainStops;
    }

    private String id;// Variabel for ankomsttid
    private Rute route; // Ruten toget har
    //Denne listen inneholder alle stopp i ruten, og inkluderer avgangstider og ankomsttider
    private ArrayList<TrainStop> trainStops = new ArrayList<>();


    public TrainStop getStopByName(String stationName) {
        // Vi ønsker å hente et stopp via navn.
        try {
            for (TrainStop stop : trainStops) {
                String stopName = stop.getStop().getName().toLowerCase();
                if (stopName.equals(stationName.toLowerCase())) {
                    return stop;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalArgumentException("Stopp med navn " + stationName + " ikke funnet i togets stopp.");
    }

    public boolean trainStopsAtStation(String stationName){
        // Metode for å sjekke om toget stopper på en gitt stasjon
        for (TrainStop stop : trainStops){
            if (stop.getStop().getName().toLowerCase().equals(stationName.toLowerCase())){
                return true;
            }
        }
        return false;
    }
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

    public ArrayList<TrainStop> getTrainStops() {
        return trainStops;
    }
    public void addTrainStop(TrainStop trainStop) {
        trainStops.add(trainStop);
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
