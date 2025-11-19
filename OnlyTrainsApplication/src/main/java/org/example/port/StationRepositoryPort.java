package org.example.port;

import org.example.model.Station;

public interface StationRepositoryPort {

    public void loadStationsFromCSV(String filePath);

    public void loadStationsFromJson(String filepath);

    public void loadStationsFromNeo4j(String uri, String user, String password);


    public void addStation(String stationName);

    public boolean stationExists(String stationName);

    public Station getStationByName(String stationName);

    public Station getStationByID(String stationId);
}
