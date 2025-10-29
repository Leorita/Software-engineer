package org.example.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Rute;
import org.example.model.Station;
import org.example.model.Train;
import org.example.model.TrainStop;
import org.example.port.TrainRepositoryPort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class TrainRepository implements TrainRepositoryPort {

    private ArrayList<Train> trains = new ArrayList<>();
    private final RuteRepository availableRoutes = new RuteRepository();

    public TrainRepository() {
        // DEFINERER TOG I SYSTEMET
        ArrayList<String> availableTrainIds = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            // Vi ønsker å ha tog IDer fra T001 til T100
            availableTrainIds.add("T" + String.format("%03d", i));
        }
        Random randomGenerator = new Random();
        for (Rute rute : availableRoutes.getRuter()) {
            for (int i = 0; i < 3; i++) {
                // TODO vi oppretter tre tog for hver rute

                // Vi velger en tilfeldig ID fra listen
                int randomIndex = randomGenerator.nextInt(availableTrainIds.size());
                String trainId = availableTrainIds.get(randomIndex);
                Train train = new Train();
                train.setId(trainId);

                train.setRoute(rute);

                // TODO vi fjerner den brukte IDen fra listen
                availableTrainIds.remove(randomIndex);

                LocalTime FirstDepartureTime = LocalTime.of(6 + i * 3, 0); // Tog avganger kl 06:00, 09:00, 12:00 osv.
                for (Station StationStop : rute.getStops()){
                    // TODO Vi oppretter TrainStop objekt for hvert stopp i ruten
                    TrainStop trainStop = new TrainStop();

                    // TODO vi beregner ankomst og avgangstider programmatisk
                    LocalTime departureTime = FirstDepartureTime.plusMinutes(rute.getStops().indexOf(StationStop) * 15); // Antar 15 minutter mellom hver stasjon
                    LocalTime arrivalTime = departureTime.minusMinutes(5); // Antar toget ankommer 5 minutter før avgang

                    trainStop.setStop(StationStop);
                    trainStop.setDepartureTime(departureTime);
                    trainStop.setArrivalTime(arrivalTime);
                    trainStop.setTrack(1 + randomGenerator.nextInt(5)); // Tilfeldig spor mellom 1 og 5
                    trainStop.setDelayed(false);
                    train.addTrainStop(trainStop);

                }

                //TODO: Første stopp har ingen ankomsttid, siste stopp har ingen avgangstid
                train.getTrainStops().getFirst().setArrivalTime(null);
                train.getTrainStops().getLast().setDepartureTime(null);

                trains.add(train);

            }
        }
    }
    public TrainRepository(String inputType) {
        loadTrainsFromJson("src/main/java/org/example/json/trains.json");
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
    public ArrayList<Train> getTrainsFromStationFromTime(String StationName, LocalTime time) {
        return null;
    }

    @Override
    public void loadTrainsFromJson(String filepath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File trainJsonFile = new File(filepath);

            // TODO lese JSON-filen, iterere gjennom stasjonene og legge dem til i stations-listen

            JsonNode rootNode = objectMapper.readTree(trainJsonFile); // Denne er generert av AI.
            if (rootNode.isArray()) {
                for (JsonNode trainNode : rootNode) {
                    // TODO Vi itererer gjennom hele listen i JSON-filen
                    Train train = new Train();
                    String id = trainNode.get("id").asText();
                    String ruteName = trainNode.get("route").get("name").asText();
                    Rute trainRoute = availableRoutes.getRuteByName(ruteName);
                    train.setRoute(trainRoute);

                    // TODO: Koden nedenfor er delvis genereret av AI, var veldig vanskelig å få tak i verdier fra "trainstops"
                    JsonNode trainStopsNode = trainNode.get("trainStops");
                    if (trainStopsNode != null && trainStopsNode.isArray()) {
                        for (JsonNode trainStopNode : trainStopsNode) {
                            TrainStop trainStop = new TrainStop();

                            // Get station
                            String stationId = trainStopNode.get("stop").get("id").asText();
                            Station stop = availableRoutes.getAvailableStations().findById(stationId);
                            trainStop.setStop(stop);

                            JsonNode departureNode = trainStopNode.get("departureTime");
                            LocalTime departureTime = null;
                            if (departureNode != null && !departureNode.isNull() && departureNode.isArray()) {
                                ArrayList<Integer> departureTimeParameters = new ArrayList<>();
                                for (JsonNode departureNod : departureNode) {
                                    departureTimeParameters.add(departureNod.asInt());
                                }
                                if (departureTimeParameters.size() >= 2) {
                                    departureTime = LocalTime.of(departureTimeParameters.get(0), departureTimeParameters.get(1));
                                }
                            }
                            trainStop.setDepartureTime(departureTime);

                            // Handle arrival time (can be null)
                            JsonNode arrivalNode = trainStopNode.get("arrivalTime");
                            LocalTime arrivalTime = null;
                            if (arrivalNode != null && !arrivalNode.isNull() && arrivalNode.isArray()) {
                                ArrayList<Integer> arrivalTimeParameters = new ArrayList<>();
                                for (JsonNode arrivalNod : arrivalNode) {
                                    arrivalTimeParameters.add(arrivalNod.asInt());
                                }
                                if (arrivalTimeParameters.size() >= 2) {
                                    arrivalTime = LocalTime.of(arrivalTimeParameters.get(0), arrivalTimeParameters.get(1));
                                }
                            }
                            trainStop.setArrivalTime(arrivalTime);

                            // Set other properties
                            trainStop.setTrack(trainStopNode.get("track").asInt());
                            trainStop.setDelayed(trainStopNode.get("delayed").asBoolean());

                            train.addTrainStop(trainStop);
                        }
                    }
                    trains.add(train);
                }

                }
        }catch(FileNotFoundException fileNotFoundException){
            System.out.println("Finner ikke filen: " + fileNotFoundException.getMessage());
        }
        catch (IOException e) {
            System.out.println("Feil ved lesing av JSON-fil: " + e.getMessage());
        }
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
