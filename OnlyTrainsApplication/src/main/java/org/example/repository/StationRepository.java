package org.example.repository;

import org.example.model.Station;
import org.example.port.StationRepositoryPort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StationRepository implements StationRepositoryPort {

    private ArrayList<Station> stations = new ArrayList<>(); // liste over alle stasjonsobjekter
    private static int counter = 1;
    public StationRepository() {
        loadStationsFromCSV("src/main/java/org/example/csv/stations.csv");
    }

    private void loadStationsFromCSV(String filePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // hopper over første linje (overskriften)
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // deler på komma
                if (values.length >= 2) {
                    //String id = values[0].trim();
                    String id = Integer.toString(counter);
                    String name = values[1].trim();
                    stations.add(new Station(id, name));
                    counter++;
                }
            }
        } catch (IOException e) {
            System.out.println("Feil ved lesing av CSV-fil: " + e.getMessage());
        }
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public Station findById(String id) {
        for (Station s : stations) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "StationRepository{" +
                "stations=" + stations +
                '}';
    }


    @Override
    public void addStation(String stationName) {
        try {
            if (stationExists(stationName)){
                System.err.println("Stasjonen finnes allerede: " + stationName);
                return; // Stasjonen finnes allerede, vi avslutter metoden her.
            }
            Station newStation = new Station(stationName);
            newStation.setId(Integer.toString(counter));
            counter++;
            stations.add(newStation);
            System.out.println(stationName + " Lagt til.");
        } catch (Exception e) {
            System.err.println("Feil ved sjekking av eksisterende stasjoner: " + e.getMessage());
        }

    }

    @Override
    public boolean stationExists(String stationName) {
        // TODO vi lager en metode for å sjekke om en stasjon allerede eksisterer.
        for (Station station : stations) {
            if (station.getName().equalsIgnoreCase(stationName)) {
                return true; // Stasjonen finnes allerede, avslutt metoden
            }
        }
        return false;
    }
}
