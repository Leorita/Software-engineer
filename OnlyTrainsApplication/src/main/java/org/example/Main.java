package org.example;
import org.example.model.Train;
import org.example.repository.TrainRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void start(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Velkommen til OnlyTrains ===");

        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Brukernavn: ");
            String username = scanner.nextLine();

            System.out.print("Passord: ");
            String password = scanner.nextLine();

            if (username.equals("admin") && password.equals("admin123")) {
                System.out.println("Innlogging vellykket!\n");
                loggedIn = true;
            } else {
                System.out.println("Feil brukernavn eller passord. Prøv igjen.\n");
            }
        }

        TrainRepository trainRepository = new TrainRepository("json");

        // Her kan du fortsette med meny eller funksjoner etter innlogging
        System.out.println("Du er nå logget inn som admin.");


        boolean running = true;
        while (running) {
            System.out.println("\n=== Hovedmeny ===");
            System.out.println("1 - Vis tog fra en stasjon");
            System.out.println("2 - Fjern Tog fra lista");
            System.out.println("0 - Avslutt applikasjonen");
            System.out.print("Velg et alternativ: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Skriv inn stasjons-ID (f.eks. S01): ");
                    String stationId = scanner.nextLine().trim();

                    System.out.print("Skriv inn tid (HH:MM): ");
                    String timeInput = scanner.nextLine().trim();
                    LocalTime time = LocalTime.parse(timeInput);

                    ArrayList<Train> result = trainRepository.getTrainsWithStopFromTime(stationId, time);

                    if (result.isEmpty()) {
                        System.out.println("Ingen tog funnet fra stasjon " + stationId + " etter " + time);
                    } else {
                        System.out.println("Tog fra stasjon " + stationId + " etter " + time + ":");
                        for (Train t : result) {
                            System.out.println("• Tog " + t.getId() + " | Rute: " + t.getRoute().getId());
                        }
                    }
                    break;
                case "2":
                    System.out.print("Skriv inn tog-ID som skal fjernes (f.eks. T064): ");
                    String trainIdToRemove = scanner.nextLine().trim();

                    if (trainRepository.getTrainById(trainIdToRemove) == null) {
                        System.out.println("Fant ikke tog med ID: " + trainIdToRemove);
                    } else {
                        trainRepository.removeTrain(trainIdToRemove);
                        System.out.println("Tog " + trainIdToRemove + " ble fjernet.");
                    }
                    break;
                case "0":
                    System.out.println("Avslutter applikasjonen...");
                    running = false;
                    break;
                default:
                    System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }

        scanner.close();
    }

}
