package org.example.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Rute;
import org.example.model.Station;
import org.example.port.RuteRepositoryPort;
import org.neo4j.driver.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.neo4j.driver.Values.parameters;

public class RuteRepository implements AutoCloseable, RuteRepositoryPort {

    // TODO instansvariabel som holder på alle rutene.
    private ArrayList<Rute> ruter = new ArrayList<>();
    //private final Driver driver;
    // TODO lager en konstant instans av StationRepository for å hente stasjonsdata.
    private final StationRepository AvailableStations = new StationRepository("json");

    //Konstruktør: kobler til Neo4j-databasen
//    public RuteRepository(String uri, String user, String password) {
//        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
//    }

    // Konstruktør som skal koble til jsonFilen
    public RuteRepository(){
        // TODO Vi oppretter en instans av StationRepository for å håndtere stasjonsdata
        loadRoutesFromJson("src/main/java/org/example/json/routes.json");
    }


    //Sørger for at driveren lukkes etter bruk
    @Override
    public void close() {
        //driver.close();
    }
//
//    //Lagrer én rute med alle stasjoner i Neo4j
//    public void saveRoute(Rute rute) {
//        try (Session session = driver.session()) {
//            // Opprett eller finn ruten
//            session.run("MERGE (r:Rute {id: $id})",
//                    parameters("id", rute.getId()));
//
//            //Opprett stasjoner og relasjoner
//            for (Station station : rute.getStops()) {
//                session.run("""
//                                MERGE (s:Station {id: $id})
//                                SET s.name = $name
//                                MERGE (r:Rute {id: $ruteId})
//                                MERGE (r)-[:HAS_STOP]->(s)
//                                """,
//                        parameters("id", station.getId(),
//                                "name", station.getName(),
//                                "ruteId", rute.getId()));
//            }
//
//            // Koble stasjonene i riktig rekkefølge
//            for (int i = 0; i < rute.getStops().size() - 1; i++) {
//                Station current = rute.getStops().get(i);
//                Station next = rute.getStops().get(i + 1);
//                session.run("""
//                                MATCH (a:Station {id: $idA}), (b:Station {id: $idB})
//                                MERGE (a)-[:NEXT_STOP]->(b)
//                                """,
//                        parameters("idA", current.getId(),
//                                "idB", next.getId()));
//            }
//
//            System.out.println("Lagret rute " + rute.getId() + " i Neo4j!");
//        } catch (Exception e) {
//            System.err.println("Feil under lagring av rute " + rute.getId() + ": " + e.getMessage());
//        }
//    }

    // Henter én rute med alle stasjoner fra Neo4j
//    public Rute getRuteByName(String ruteId) {
//        try (Session session = driver.session()) {
//            var result = session.run("""
//                MATCH (r:Rute {id: $id})-[:HAS_STOP]->(s:Station)
//                RETURN r.id AS routeId, collect({id: s.id, name: s.name}) AS stops
//                """, parameters("id", ruteId));
//
//            if (result.hasNext()) {
//                var record = result.next();
//                Rute rute = new Rute(record.get("routeId").asString());
//
//                var stopsList = record.get("stops").asList();
//                for (Object stopObj : stopsList) {
//                    @SuppressWarnings("unchecked")
//                    Map<String, Object> map = (Map<String, Object>) stopObj;
//                    rute.addStop(new Station(
//                            map.get("id").toString(),
//                            map.get("name").toString()
//                    ));
//                }
//
//                System.out.println("Fant rute " + ruteId + " i Neo4j");
//                return rute;
//            } else {
//                System.out.println("Ingen rute funnet med id: " + ruteId);
//                return null;
//            }
//        } catch (Exception e) {
//            System.err.println("Feil under henting av rute " + ruteId + ": " + e.getMessage());
//            return null;
//        }
//    }

    // Henter alle ruter fra Neo4j
//    public ArrayList<Rute> getAllRoutes() {
//        ArrayList<Rute> routes = new ArrayList<>();
//
//        try (Session session = driver.session()) {
//            var result = session.run("""
//                MATCH (r:Rute)-[:HAS_STOP]->(s:Station)
//                RETURN r.id AS routeId, collect({id: s.id, name: s.name}) AS stops
//                ORDER BY routeId
//            """);
//
//            while (result.hasNext()) {
//                var record = result.next();
//                String routeId = record.get("routeId").asString();
//                Rute route = new Rute(routeId);
//                List<Object> stopsList = record.get("stops").asList();
//
//                for (Object stopObj : stopsList) {
//                    @SuppressWarnings("unchecked")
//                    Map<String, Object> map = (Map<String, Object>) stopObj;
//                    String id = map.get("id").toString();
//                    String name = map.get("name").toString();
//                    route.addStop(new Station(id, name));
//                }
//
//                routes.add(route);
//            }
//            System.out.println("Hentet " + routes.size() + " ruter fra Neo4j");
//        } catch (Exception e) {
//            System.err.println("Feil under henting av alle ruter: " + e.getMessage());
//        }
//
//        return routes;
//    }


    public ArrayList<Rute> getRuter() {
        return ruter;
    }

    @Override
    public void loadRoutesFromJson(String filepath) {
        // TODO lese stasjoner fra JSON-fil og legge dem til i stations-listen
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File routeJsonFile = new File(filepath);
            // TODO lese JSON-filen, iterere gjennom rutene og legge dem til i stations-listen

            JsonNode rootNode = objectMapper.readTree(routeJsonFile);
            if (rootNode.isArray()) {
                for (JsonNode routeNode : rootNode) {
                    // TODO vi henter ut informasjonen om hver rute
                    String id = routeNode.get("id").asText();
                    String name = routeNode.get("name").asText();
                    boolean outbound = routeNode.get("outbound").asBoolean();

                    // TODO vi legger inn alle stoppene i en midlertidig liste med IDer først
                    ArrayList<String> stopsIDs = new ArrayList<>();
                    for (JsonNode stopNode : routeNode.get("stops")) {
                        stopsIDs.add(stopNode.asText());
                    }
                    ArrayList<Station> stops = new ArrayList<>();
                    for(String stopId : stopsIDs) {
                        if (AvailableStations.findById(stopId) != null) {
                            // TODO vi sjekker om stasjonen finnes i available stations før vi legger den til
                            stops.add(AvailableStations.findById(stopId));
                        }
                    }
                    ruter.add(new Rute(id, name, outbound, stops));
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
    public Rute getRuteByName(String name) {
        for (Rute r : ruter) {
            if (r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Rute getRuteById(String id) {
        for (Rute r : ruter) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Station> commonStopsBetweenTwoRoutes(String IdRoute1, String IdRoute2) {
        // TODO Vi ønsker å finne felles stasjoner mellom to ruter
        ArrayList<Station> commonStops = new ArrayList<>();
        Rute route1 = getRuteById(IdRoute1);
        Rute route2 = getRuteById(IdRoute2);

        for (Station stop: route1.getStops()){
            if (stopExistsInRoute(stop, route2)){
                commonStops.add(stop);
            }
        }

        return commonStops;
    }

    @Override
    public boolean stopExistsInRoute(Station stop, Rute route) {
        // TODO vi ønsker å sjekke om et stopp eksisterer i en rute.
        for (Station s : route.getStops()){
            if(s.equals(stop)){
                return true;
            }
        }
        return false;
    }

    public StationRepository getAvailableStations() {
        return AvailableStations;
    }



    @Override
    public String toString() {
        return "RuteRepository{" +
                "ruter=" + ruter +
                '}';
    }
}