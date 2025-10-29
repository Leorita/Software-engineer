package org.example.repository;

import org.example.model.Station;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StationRepository {

    private ArrayList<Station> stations = new ArrayList<>(); // liste over alle stasjonsobjekter
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
                    String id = values[0].trim();
                    String name = values[1].trim();
                    stations.add(new Station(id, name));
                }
            }
        } catch (IOException e) {
            System.out.println("Feil ved lesing av CSV-fil: " + e.getMessage());
        }
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
}
