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
        System.out.println("Hello world!");

        // TODO vi tester rute repository
        RuteRepository ruteRepository = new RuteRepository();
        StationRepository stationRepository = new StationRepository();



    }
}