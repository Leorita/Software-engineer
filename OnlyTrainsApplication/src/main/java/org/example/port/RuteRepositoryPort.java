package org.example.port;

import org.example.model.Rute;
import org.example.model.Station;

import java.util.ArrayList;

public interface RuteRepositoryPort {
    public void loadRoutesFromJson(String filepath);
    public Rute getRuteByName(String name);
    public Rute getRuteById(String id);
    public ArrayList<Station> commonStopsBetweenTwoRoutes(String IdRoute1, String IdRoute2);
    public boolean stopExistsInRoute(Station stop, Rute route);
}
