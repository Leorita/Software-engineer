package org.example;

import org.example.model.*;
import org.example.repository.RuteRepository;
import org.example.repository.StationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, World!");
        // TODO vi tester rute repository
        StationRepository stationRepository = new StationRepository("json");

        //System.out.println(stationRepository.getStations());
        //System.out.println(stationRepository.getStations().size());

        RuteRepository ruteRepository = new RuteRepository();

        for (Rute rute : ruteRepository.getRuter()){
            System.out.println("Rute ID: " + rute.getId() + " Origin-Destinasjon: " + rute.getStops().getFirst().getName()
                    + " -> " +
                    rute.getStops().getLast().getName());
        }



    }
}