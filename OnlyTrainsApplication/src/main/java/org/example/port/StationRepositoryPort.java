package org.example.port;

public interface StationRepositoryPort {

    public void addStation(String stationName);

    public boolean stationExists(String stationName);
}
