package org.example;
import org.example.model.Train;
import org.example.model.TrainStop;
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
            System.out.println("3 - Legg til tog");
            System.out.println("4 - Legg til forsinkelse");
            System.out.println("0 - Avslutt applikasjonen");
            System.out.print("Velg et alternativ: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Skriv inn stasjons-ID (f.eks. S01): ");
                    String stationId = scanner.nextLine().trim().toUpperCase(); // gjør input enkel og case-insensitiv

                    System.out.print("Skriv inn tid (HH:MM): ");
                    String timeInput = scanner.nextLine().trim();
                    LocalTime time = LocalTime.parse(timeInput);

                    ArrayList<Train> result = trainRepository.getTrainsWithStopFromTime(stationId, time);

                    if (result.isEmpty()) {
                        System.out.println("Ingen tog funnet fra stasjon " + stationId + " etter " + time);
                    } else {
                        System.out.println("\nTog fra stasjon " + stationId + " etter " + time + ":");
                        System.out.println("---------------------------------------------------------------");
                        System.out.printf("%-8s  %-6s  %-6s  %-20s  %-4s%n", "Avgang", "Tog", "Rute", "Destinasjon", "Spor");
                        System.out.println("---------------------------------------------------------------");

                        for (Train t : result) {
                            // Finn stoppet for denne stasjonen
                            TrainStop stop = t.getStopByName(
                                    trainRepository.availableRoutes.getAvailableStations().getStationByID(stationId).getName()
                            );

                            // Avgangstid (bruk ankomst hvis avgang er null)
                            LocalTime dep = (stop.getDepartureTime() != null) ? stop.getDepartureTime() : stop.getArrivalTime();
                            String depStr = (dep != null) ? dep.toString() : "-";

                            // Tog-ID
                            String trainId = t.getId();

                            // Rute-ID
                            String routeId = t.getRoute().getId();

                            // Destinasjonen her er siste stoppet i ruta
                            String destination = t.getRoute().getStops().get(t.getRoute().getStops().size() - 1).getName();

                            // Spor
                            String trackStr = (stop.getTrack() > 0) ? String.valueOf(stop.getTrack()) : "-";

                            // Print som likner wireframe, se rapport
                            System.out.printf("%-8s  %-6s  %-6s  %-20s  %-4s%n", depStr, trainId, routeId, destination, trackStr);
                        }
                        System.out.println("---------------------------------------------------------------");
                    }
                    break;
                case "2":
                    System.out.print("Skriv inn tog-ID som skal fjernes (f.eks. T064): ");
                    String trainIdToRemove = scanner.nextLine().trim().toUpperCase();

                    if (trainRepository.getTrainById(trainIdToRemove) == null) {
                        System.out.println("Fant ikke tog med ID: " + trainIdToRemove);
                    } else {
                        trainRepository.removeTrain(trainIdToRemove);
                        System.out.println("Tog " + trainIdToRemove + " ble fjernet.");
                    }
                    break;
                case "3":
                    System.out.println("Metoden er under utvikling");
                    break;
                case "4":
                    System.out.println("Metoden er under utvikling");
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
