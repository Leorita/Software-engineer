package org.example.port;

import java.util.ArrayList;
import org.example.model.Train;
import java.time.LocalTime;
public interface TrainRepositoryPort {


    // TODO lage metode for å legge til tog
    public void Addtrain(Train  train);

    // TODO metode for å hente tog fra en stasjon fra en gitt tid.
    public ArrayList<Train> getTrainsFromStationFromTime(String StationName, LocalTime time);

    // TODO Metode for å opprette
    public void loadTrainsFromJson(String filepath);

    public void removeTrain(String trainId);

    // TODO lage metode for å oppdatere tog
    public void UpdateTrain(Train train);


    public void clearAllTrains(String password);

    public Train getTrainById(String trainId);

    // TODO metode for å hente tog basert på rute.
    public ArrayList<Train> getTrainsByRoute(String routeId);
}
