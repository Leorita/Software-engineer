package org.example;

import org.example.model.*;
import org.example.repository.RuteRepository;
import org.example.repository.StationRepository;
import org.example.repository.TrainRepository;

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

        System.out.println(stationRepository.getStations() + "\n\n");
        //System.out.println(stationRepository.getStations().size());

        RuteRepository ruteRepository = new RuteRepository();

        for (Rute rute : ruteRepository.getRuter()){
            System.out.println("Rute ID: " + rute.getId() + " Origin-Destinasjon: " + rute.getStops().getFirst().getName()
                    + " -> " +
                    rute.getStops().getLast().getName());
        }


        TrainRepository trainRepository = new TrainRepository();
        for (Train train : trainRepository.getTrains()){
            System.out.println("Tog ID: " + train.getId() + ", Rute: " + train.getRoute().getId() + " ," + train.getRoute().getName()
                    + "\nTrain Stops: ");

            for (TrainStop trainstop : train.getTrainStops()){
                System.out.println("   ---- Train Stop ----");
                System.out.println("   Stopp: " + trainstop.getStop().getName());
                System.out.println("   ArrivalTime: " + trainstop.getArrivalTime());
                System.out.println("   DepartureTime: " + trainstop.getDepartureTime());
                System.out.println("   Track: " + trainstop.getTrack());
                System.out.println("   isDelayed: " + trainstop.isDelayed());
                System.out.println("   -----------------" );
            }
            System.out.println("-----------------------------------");
        }



    }
}