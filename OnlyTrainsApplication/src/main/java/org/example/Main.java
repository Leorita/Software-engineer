package org.example;

import org.example.model.*;
import org.example.repository.RuteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

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


        TimeSchedule scheduledTime = new TimeSchedule(LocalTime.of(23, 15), LocalTime.of(9, 20));

        System.out.println( "Avgangstid: " + scheduledTime.getScheduledDeparture());

        Station osloS = new Station("1", "Oslo S");
        StationTimetable osloSTimeTable = new StationTimetable(
                osloS,
                scheduledTime,
                re20,
                re20.getOrigin(),
                re20.getDestination(),
                2);

        System.out.println(osloSTimeTable);

        System.out.print("\n Rute for tog " + re20.getId() + ": [ ");
        for (Station stop : re20.getRoute().getStops()){
            System.out.print(stop.getName() + ", ");
        }
        System.out.print("]\n");


        // TODO vi tester rute repository
        RuteRepository ruteRepository = new RuteRepository();

        Rute l2 = ruteRepository.getRuteByName("l2");
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










    }
}