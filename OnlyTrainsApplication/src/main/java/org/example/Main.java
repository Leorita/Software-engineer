package org.example;

import org.example.model.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station("1", "Oslo S"));
        stations.add(new Station("2", "Fredrikstad S"));
        stations.add(new Station("5", "Halden S"));
        stations.add(new Station("7", "Goteborg S"));

        Rute rute1 = new Rute("001", stations);

        Train re20 = new Train("re20", stations.getFirst().getName(), rute1, stations.getLast().getName());


        ScheduledTime scheduledTime = new ScheduledTime(LocalTime.of(13, 15), LocalTime.of(9, 20));

        System.out.println(scheduledTime.getScheduledDeparture());

        Station osloS = new Station("1", "Oslo S");
        StationTimetable osloSTimeTable = new StationTimetable(
                osloS,
                scheduledTime,
                re20,
                re20.getOrigin(),
                re20.getDestination(),
                2);

        System.out.println(osloSTimeTable);






    }
}