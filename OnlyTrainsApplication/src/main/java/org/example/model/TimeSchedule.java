package org.example.model;

import java.time.LocalTime;

public class TimeSchedule {
    private LocalTime scheduledArrival;
    private LocalTime scheduledDeparture;

    public TimeSchedule(LocalTime scheduledArrival, LocalTime scheduledDeparture) {
        this.scheduledArrival = scheduledArrival;
        this.scheduledDeparture = scheduledDeparture;
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
}
