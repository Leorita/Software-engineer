package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.example.model.Station;
import org.example.port.StationRepositoryPort;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StationRepository implements StationRepositoryPort {

    private ArrayList<Station> stations = new ArrayList<>(); // liste over alle stasjonsobjekter

    public StationRepository(String inputType) {

        if (inputType.equals("csv")) {
            loadStationsFromCSV("src/main/java/org/example/csv/stations.csv");
        } else if (inputType.equals("json")) {
            loadStationsFromJson("src/main/java/org/example/json/stations.json");
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
        // TODO lese stasjoner fra JSON-fil og legge dem til i stations-listen
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File stationJsonFile = new File(filepath);

            // TODO lese JSON-filen, iterere gjennom stasjonene og legge dem til i stations-listen

            JsonNode rootNode = objectMapper.readTree(stationJsonFile); // Denne er generert av AI.
            if (rootNode.isArray()) {
                for (JsonNode stationNode : rootNode) {
                    String id = stationNode.get("id").asText();
                    String name = stationNode.get("name").asText();
                    stations.add(new Station(id, name));
                }
            }
        }
        catch(FileNotFoundException fileNotFoundException){
            System.out.println("Finner ikke filen: " + fileNotFoundException.getMessage());
        }
        catch (IOException e) {
            System.out.println("Feil ved lesing av JSON-fil: " + e.getMessage());
        }

    }

    @Override
    public void loadStationsFromNeo4j(String filepath) {

    }

    public List<Station> getStations() {
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

    @Override
    public Station getStationByName(String stationName) {
        for (Station station : stations){
            if (station.getName().toLowerCase().equals(stationName.toLowerCase())){
                return station;
            }
        }
        return null;
    }

    @Override
    public Station getStationByID(String stationId) {
        for (Station station : stations){
            if (station.getId().equals(stationId)){
                return station;
            }
        }
        return null;
    }
}
