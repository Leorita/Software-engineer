package org.example;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.model.*;
import org.example.repository.RuteRepository;
import org.example.repository.StationRepository;
import org.example.repository.TrainRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, World!");
        // TODO vi tester Station repository
        StationRepository stationRepository = new StationRepository("json");

        System.out.println(stationRepository.getStations() + "\n\n");
        System.out.println(stationRepository.getStations().size());

        // TODO vi tester rute repository
        RuteRepository ruteRepository = new RuteRepository();

        for (Rute rute : ruteRepository.getRuter()) {
            System.out.println("Rute ID: " + rute.getId() + " Origin-Destinasjon: " + rute.getStops().getFirst().getName()
                    + " -> " +
                    rute.getStops().getLast().getName());
        }

        System.out.println("Felles stasjoner for rute RE20 og R21");
        for (Station station : ruteRepository.commonStopsBetweenTwoRoutes("RE20", "R21")){
            System.out.println(station);
        }
        // TODO vi tester train repository
        TrainRepository trainRepository = new TrainRepository("json");
        //File trainJsonFile = new File("src/main/java/org/example/json/trains2.json");
        //writeTrainsToJSON(trainRepository.getTrains(), trainJsonFile);



        LocalTime departureTime = LocalTime.of(12, 0);
        System.out.println("KL: " + departureTime + "\n");
        int visAntall = 5;
        int counter = 1;
        String stationID = "S02";
        for (Train train : trainRepository.getTrainsWithStopFromTime(stationID, departureTime)) {
            System.out.println("Tog ID: " + train.getId() + ", Rute: " + train.getRoute().getId() + " ," + train.getRoute().getName()
                    + "\nTrain Stops: ");
            for (TrainStop trainstop : train.getTrainStops()) {
                {
                    if (trainstop.getStop().getId().equals(stationID)){
                        System.out.println("   ---- Train Stop ----");
                        System.out.println("   Stopp: " + trainstop.getStop().getName());
                        System.out.println("   ArrivalTime: " + trainstop.getArrivalTime());
                        System.out.println("   DepartureTime: " + trainstop.getDepartureTime());
                        System.out.println("   Track: " + trainstop.getTrack());
                        System.out.println("   isDelayed: " + trainstop.isDelayed());
                        System.out.println("   isCancelled: " + trainstop.isCancelled());
                        System.out.println("   -----------------");
                    }
                }
            }
            System.out.println("-----------------------------------");
            if (counter >= visAntall){
                System.out.println("Viste " + visAntall + " tog. Avslutter.");
                break;
            }
            counter++;
        }


    }

    public static void writeTrainsToJSON(ArrayList<Train> listOfTrains, File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, listOfTrains);
        } catch (DatabindException exception) {
            System.err.println(exception.getMessage());
        } catch (StreamWriteException exception) {
            System.err.println(exception);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}