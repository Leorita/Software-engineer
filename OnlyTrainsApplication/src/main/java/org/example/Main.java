package org.example;

import org.example.model.*;
import org.example.repository.RuteRepository;
import org.example.repository.StationRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Til neo4j kobling
        String uri = "bolt://localhost:7687";
        String user= "neo4j";
        String password = "Gruppe20";

        String filePath = "src/main/resources/ruter.csv";

        try (RuteRepository ruteRepo = new RuteRepository(uri, user, password)) {

            // 🔹 Les ruter fra CSV
            Map<String, Rute> ruterMap = new HashMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                br.readLine(); // hopp over overskrift
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        String ruteId = parts[0].trim();
                        String stasjonId = parts[1].trim();
                        String stasjonNavn = parts[2].trim();

                        // Opprett rute hvis den ikke finnes fra før
                        ruterMap.putIfAbsent(ruteId, new Rute(ruteId));

                        // Legg til stasjon i ruten
                        ruterMap.get(ruteId).addStop(new Station(stasjonId, stasjonNavn));
                    }
                }
            } catch (IOException e) {
                System.err.println("❌ Feil ved lesing av CSV: " + e.getMessage());
            }

            // 🔹 Lagre alle ruter i Neo4j
            for (Rute r : ruterMap.values()) {
                ruteRepo.saveRoute(r);
            }

            // 🔹 Hent alle ruter fra Neo4j etterpå (for å bekrefte)
            System.out.println("\n🗺️ Ruter i Neo4j:");
            for (Rute r : ruteRepo.getAllRoutes()) {
                System.out.println("Rute " + r.getId() + ":");
                for (Station s : r.getStops()) {
                    System.out.println("   • " + s.getName());
                }
            }

        } catch (Exception e) {
            System.err.println("⚠️ Feil: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n🔚 Program avsluttet.");



        // TODO vi tester rute repository
        RuteRepository ruteRepository = new RuteRepository(uri, user, password);
        StationRepository stationRepository = new StationRepository();

        Rute l2 = ruteRepository.getRuteByName("L2");
        System.out.println("Stopp for rute: " + l2.getId());
        System.out.println("{");
        for (Station station : l2.getStops()){
            System.out.println("    " + station.getName() + ","  + " Avgangstid" + ", Ankomsttid" + ",");
        }
        System.out.println("}");

        // Vi tester med input fra bruker
        // Scriptet er delvis generert av AI
        Scanner input = new Scanner(System.in);
        System.out.print("\nHvor reiser du fra: ");
        String origin = input.nextLine();

        System.out.print("Hvor reiser du til: ");
        String destination = input.nextLine();

        System.out.print("Hvilken dato reiser du? (format: yyyy-MM-dd): ");
        String dateInput = input.nextLine();
        LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.print("Hva er avreisetidspunktet? (format: HH:mm): ");
        String timeInput = input.nextLine();
        LocalTime time = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm")); //Klokkelsett

        System.out.println("\n--- Reiseinformasjon ---");
        System.out.println("Fra: " + origin);
        System.out.println("Til: " + destination);
        System.out.println("Dato: " + date);
        System.out.println("Tid: " + time);
        input.close();


        StationRepository stationRepo = new StationRepository();

        System.out.println("📍 Stasjoner i nettverket vårt:");
        for (Station s : stationRepo.getAll()) {
            System.out.println(s.getId() + " - " + s.getName());
        }
    }
}