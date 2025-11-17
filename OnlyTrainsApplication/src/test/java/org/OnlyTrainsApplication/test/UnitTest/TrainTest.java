package org.OnlyTrainsApplication.test.UnitTest;

import org.example.model.Train;
import org.example.model.TrainStop;
import org.example.port.TrainRepositoryPort;
import org.example.repository.TrainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

/**
 *
 **/
public class TrainTest {

    private TrainRepository trainRepository;
    // Mocker ArtistRepositoryPort slik at vi kan definere hvordan denne skal fungere i testene våre


    @BeforeEach
    public void setUp() {
        trainRepository = new TrainRepository("json");
    }

    @Test
    public void arrivalTimeNullAtFirstStop() throws Exception{
        // Arrange
        Train train1 = trainRepository.getTrains().get(0);
        Train train2 = trainRepository.getTrains().get(1);
        Train train3 = trainRepository.getTrains().get(2);
        // Act
        LocalTime arrivalTimeTrain1 = train1.getTrainStops().getFirst().getArrivalTime();
        LocalTime arrivalTimeTrain2 = train2.getTrainStops().getFirst().getArrivalTime();
        LocalTime arrivalTimeTrain3 = train3.getTrainStops().getFirst().getArrivalTime();

        // Assert
        assertEquals(null, arrivalTimeTrain1);
        assertEquals(null, arrivalTimeTrain2);
        assertEquals(null, arrivalTimeTrain3);
    }

    @Test
    void departureTimeNullAtLastStop(){
        // Arrange
        Train train1 = trainRepository.getTrains().get(0);
        Train train2 = trainRepository.getTrains().get(1);
        Train train3 = trainRepository.getTrains().get(2);

        // Act
        LocalTime departureTimeTimeTrain1 = train1.getTrainStops().getLast().getDepartureTime();
        LocalTime departureTimeTrain2 = train2.getTrainStops().getLast().getDepartureTime();
        LocalTime departureTimeTrain3 = train3.getTrainStops().getLast().getDepartureTime();

        // Assert
        assertEquals(null, departureTimeTimeTrain1);
        assertEquals(null, departureTimeTrain2);
        assertEquals(null, departureTimeTrain3);
    }

    @Test
    public void ArrivalTimeLessThanDepartureTime() throws Exception {
        // TODO Vi ønsker å teste om ankomsttiden er større enn avgangstiden.
        // Arrange
        Train train1 = trainRepository.getTrains().get(0);
        Train train2 = trainRepository.getTrains().get(1);
        Train train3 = trainRepository.getTrains().get(2);

        // Act
        boolean result1 = true;
        boolean result2 = true;
        boolean result3 = true;


        for (TrainStop stop : train1.getTrainStops()) {
            if (!stop.equals(train1.getTrainStops().getFirst()) && !stop.equals(train1.getTrainStops().getLast())) {
                // Vi ønsker å sjekke alle stoppene mellom første og siste stopp.
                if (stop.getArrivalTime().isAfter(stop.getDepartureTime())) {
                    result1 = false;
                }
            }
        }

        for (TrainStop stop : train2.getTrainStops()) {
            if (!stop.equals(train2.getTrainStops().getFirst()) && !stop.equals(train2.getTrainStops().getLast())) {
                // Vi ønsker å sjekke alle stoppene mellom første og siste stopp.
                if (stop.getArrivalTime().isAfter(stop.getDepartureTime())) {
                    result2 = false;
                }
            }
        }
        for (TrainStop stop : train3.getTrainStops()) {
            if (!stop.equals(train3.getTrainStops().getFirst()) && !stop.equals(train3.getTrainStops().getLast())) {
                // Vi ønsker å sjekke alle stoppene mellom første og siste stopp.
                if (stop.getArrivalTime().isAfter(stop.getDepartureTime())) {
                    result3 = false;
                }
            }
        }


        // Assert
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);

    }

    @Test
    void trainStopsAtStationReturnsCorrectValue(){
        // TODO Vi ønsker å teste om tog x stopper innom stasjon y.
        // Arrange
        Train train1 = trainRepository.getTrains().get(0);
        Train train2 = trainRepository.getTrains().get(1);
        Train train3 = trainRepository.getTrains().get(2);
        Train train4 = trainRepository.getTrainsByRoute("R22").getFirst();

        // Act
        boolean result1 = train1.trainStopsAtStation("Ski");
        boolean result2 = train4.trainStopsAtStation("Heia");
        boolean result3 = train1.trainStopsAtStation("Heia");

        // Assert
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);

    }

    @Test
    void getTrainsByRouteReturnsCorrectValue(){
        // TODO Vi ønsker å teste funksjonen som henter tog basert på rute.
        // Arrange
        Train train1 = trainRepository.getTrainsByRoute("RE20").getFirst();
        Train train2 = trainRepository.getTrainsByRoute("R21").getFirst();
        Train train3 = trainRepository.getTrainsByRoute("L2").getFirst();
        Train train4 = trainRepository.getTrainsByRoute("R22").getFirst();

        // Act

        // Assert
        assertEquals("RE20", train1.getRoute().getId());
        assertEquals("R21", train2.getRoute().getId());
        assertEquals("L2", train3.getRoute().getId());
        assertEquals("R22", train4.getRoute().getId());
        assertEquals(null, trainRepository.getTrainsByRoute("Denne ruten finnes ikke!."));
    }

}