package org.example.repository;

import java.util.ArrayList;
import org.example.model.Train;
import java.time.LocalTime;
public interface TrainRepositoryPort {


    // TODO lage metode for å legge til tog
    public void Addtrain(Train  train);

    // TODO metode for å hente tog fra en stasjon fra en gitt tid.
    public ArrayList<Train> getTrainsFromStaionFromTime(String StationName, LocalTime time);


    public void removeTrain(String trainId);

    // TODO lage metode for å oppdatere tog
    public void UpdateTrain(Train train);
}
