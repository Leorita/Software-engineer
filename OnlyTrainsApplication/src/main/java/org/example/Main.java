package org.example;

import org.example.model.Rute;
import org.example.model.Station;
import org.example.model.StationTimetable;
import org.example.model.Train;

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

        Train re20 = new Train("re20", "Oslo S", rute1, "Goteborg");



    }
}