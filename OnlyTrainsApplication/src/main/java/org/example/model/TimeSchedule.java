package org.example.model;

import java.time.LocalTime;

public class TimeSchedule {
    private LocalTime scheduledArrival;
    private LocalTime scheduledDeparture;

    public TimeSchedule(LocalTime scheduledArrival, LocalTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
        this.scheduledArrival = scheduledArrival;
    }

    public LocalTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(LocalTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public LocalTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    @Override
    public String toString(){
        return "Avgang: " + scheduledDeparture + ", Ankomst" + (scheduledArrival != null ? scheduledArrival : "N/A");

    }
}
