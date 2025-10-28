package org.example.repository;

import org.example.model.Station;
import org.example.port.StationRepositoryPort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StationRepository implements StationRepositoryPort {

    private ArrayList<Station> stations = new ArrayList<>(); // liste over alle stasjonsobjekter

    public StationRepository(String inputType) {

        if (inputType.equals("csv")) {
            loadStationsFromCSV("src/main/java/org/example/csv/stations.csv");
        } else if (inputType.equals("json")) {
            loadStationsFromJson("/path/to/stations.json");
        } else {
            System.out.println("Ugyldig input type. Vennligst bruk 'csv' eller 'json'.");
        }
    }

    @Override
    public void loadStationsFromCSV(String filePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // hopper over første linje (overskriften)
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // deler på komma
                if (values.length >= 2) {
                    String id = values[0].trim();
                    String name = values[1].trim();
                    stations.add(new Station(id, name));
                }
            }
        } catch (IOException e) {
            System.out.println("Feil ved lesing av CSV-fil: " + e.getMessage());
        }
    }

    @Override
    public void loadStationsFromJson(String filepath) {

    }

    public List<Station> getAll() {
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
    public void addStation(String stationName) {

    }

    @Override
    public boolean stationExists(String stationName) {
        return false;
    }
}
