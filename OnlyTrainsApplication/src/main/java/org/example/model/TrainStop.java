package org.example.model;

import java.time.LocalTime;

public class TrainStop{
    private Station stop;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private int track;
    private boolean isDelayed;

    public TrainStop() {
    }

    public TrainStop(Station station, LocalTime arrivalTime, LocalTime departureTime) {
        this.stop = station;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public TrainStop(Station stop, LocalTime arrivalTime, LocalTime departureTime, int track, boolean isDelayed) {
        this.stop = stop;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.track = track;
        this.isDelayed = isDelayed;
    }

    public Station getStation() { return stop; }
    public LocalTime getArrivalTime() { return arrivalTime; }
    public LocalTime getDepartureTime() { return departureTime; }

    public Station getStop() {
        return stop;
    }

    public void setStop(Station stop) {
        this.stop = stop;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public boolean isDelayed() {
        return isDelayed;
    }

    public void setDelayed(boolean delayed) {
        isDelayed = delayed;
    }

    @Override
    public String toString() {
        return "TrainStop{" +
                "stop=" + stop +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", track=" + track +
                ", isDelayed=" + isDelayed +
                '}';
    }
}
