package org.example.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ScheduledTime {
    private LocalTime scheduledArrival;
    private LocalTime scheduledDeparture;

    public ScheduledTime(LocalTime scheduledArrival, LocalTime scheduledDeparture) {
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
