package org.example.model;

public class Train {
    // Denne klassen inneholder informasjon om tog ankomster og avganger

    public Train() {
    }

    public Train(String id, String origin, Rute route, String destination) {
        this.id = id;
        this.origin = origin;
        this.route = route;
        this.destination = destination;
    }

    private String id;// Variabel for ankomsttid
    private String origin;
    private Rute route; // Ruten toget har
    private String destination;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Rute getRoute() {
        return route;
    }

    public void setRoute(Rute route) {
        this.route = route;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
