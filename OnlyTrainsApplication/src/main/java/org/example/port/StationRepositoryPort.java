package org.example.port;

public interface StationRepositoryPort {

    public void loadStationsFromCSV(String filePath);

    public void loadStationsFromJson(String filepath);

    public void addStation(String stationName);

    public boolean stationExists(String stationName);

}
