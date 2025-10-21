package org.example.model;

import java.time.LocalTime;

public class TrainStop{
    private Station stop;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private int track;

    public TrainStop(Station station, LocalTime arrivalTime, LocalTime departureTime) {
        this.stop = station;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public Station getStation() { return stop; }
    public LocalTime getArrivalTime() { return arrivalTime; }
    public LocalTime getDepartureTime() { return departureTime; }


}
