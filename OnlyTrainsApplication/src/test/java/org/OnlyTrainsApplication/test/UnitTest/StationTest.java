package org.OnlyTrainsApplication.test.UnitTest;

import org.example.model.Station;
import org.example.repository.StationRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StationTest {

    /**
     * Positiv test
     * Tester å få stasjon bassert på stasjonsnavnet.
     * Oslo S er skrevet med anhver stor og liten bokstav for å i tillegg sjekke om den er casesensetive.
     */
    @Test
    public void testGetStationByName() {
        // Arrange
        StationRepository repository = new StationRepository("json");

        // Act
        Station result = repository.getStationByName("OsLo S");

        // Assert
        assertNotNull(result);
        assertEquals("S01", result.getId());
        assertEquals("Oslo S", result.getName());
    }

    /**
     * Negativ test
     * Forventer NULL dersom Stasjonen man søker etter ikke finnes i JSON filen
     * dersom man ønkser en stasjon med navnet oppgitt, må det opprettes
     */
    @Test
    public void testGetStationByName_Negative() {
        // Arrange
        StationRepository repository = new StationRepository("json");

        // Act
        Station result = repository.getStationByName("Tulle Stasjonen");

        // Assert
        assertNull(result);
    }

    /**
     * Positiv test
     * Tester om man får stasjonsnavnet bassert på stasjonens ID
     * Tester: "S24" som skal være Halden
     */
    @Test
    public void testGetStationByID_Positive() {
        // Arrange
        StationRepository repository = new StationRepository("json");

        // Act
        Station result = repository.getStationByID("S24");

        // Assert
        assertNotNull(result);
        assertEquals("S24", result.getId());
        assertEquals("Halden", result.getName());
    }

    /**
     * Negativ test
     * Forventer NULL dersom vi søker etter et ugyldig stasjons ID
     */
    @Test
    public void testGetStationByID_Negative() {
        // Arrange
        StationRepository repository = new StationRepository("json");

        // Act
        Station result = repository.getStationByID("S99");

        // Assert
        assertNull(result);
    }


}
