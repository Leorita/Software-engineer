package org.example.repository;

import org.example.model.Train;

import java.time.LocalTime;
import java.util.ArrayList;

public class TrainRepository implements TrainRepositoryPort {

    ArrayList<Train> trains = new ArrayList<>();

    public TrainRepository() {


    }
    // TODO lage metode for å hente tog
    public ArrayList<Train> getTrains() {
        // TODO Denne metoden skal hente alle tog i systemet
        return trains;
    }

    // TODO lage metode for å legge til tog
    @Override
    public void Addtrain(Train train) {

    }

    // TODO Metode for å hente alle tog fra en stasjon til en tid.
    @Override
    public ArrayList<Train> getTrainsFromStaionFromTime(String StationName, LocalTime time) {
        return null;
    }

    // TODO lage metode for å fjerne tog
    @Override
    public void removeTrain(String trainId) {

    }
    // TODO lage metode for å oppdatere tog
    @Override
    public void UpdateTrain(Train train) {

    }

    // TODO metode for å fjerne alle tog (admin)
    @Override
    public void clearAllTrains(String password) {
        // Denne metoden krever et passord, som kun er tilgjengelig for admin

    }

    @Override
    public ArrayList<Train> getTrainsByRoute(String routeId) {
        return null;
    }


}
