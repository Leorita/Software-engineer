package org.OnlyTrainsApplication.test.UnitTest;

import org.example.model.Rute;
import org.example.model.Station;
import org.example.repository.RuteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteTest {
    RuteRepository testRepo = new RuteRepository();

    @BeforeEach
    void setTestRepo() {
        testRepo.getRuter().clear();
    }

    @Test
    void testGetRuteByIdReturnsCorrectRoute() {

        // Arrange
        Rute r1 = new Rute("R1", "RE20", false, new ArrayList<>());
        Rute r2 = new Rute("R2", "R21", false, new ArrayList<>());


        // Act
        testRepo.getRuter().add(r1);
        testRepo.getRuter().add(r2);

        Rute result1 = testRepo.getRuteById("R1");
        Rute result2 = testRepo.getRuteById("R2");
        Rute result3 = testRepo.getRuteById("R22222");


        // Assert
        assertEquals(result1, r1);
        assertEquals(result2, r2);
        assertEquals(null, result3);


    }

    @Test
    void testGetRuteByNameReturnsCorrectRoute() {
        // Arrange
        Rute r1 = new Rute("R1", "RE20", false, new ArrayList<>());
        Rute r2 = new Rute("R2", "R21", false, new ArrayList<>());


        // Act
        testRepo.getRuter().add(r1);
        testRepo.getRuter().add(r2);

        Rute result1 = testRepo.getRuteByName("RE20");
        Rute result2 = testRepo.getRuteByName("R21");
        Rute result3 = testRepo.getRuteByName("R22222");


        // Assert
        assertEquals(result1, r1);
        assertEquals(result2, r2);
        assertEquals(null, result3);

    }

    @Test
    void testCommonStopsBetweenTwoRoutes() {
        // Arrange
        Station s1 = new Station("S1", "Oslo S");
        Station s2 = new Station("S2", "Lillestrøm");
        Station s3 = new Station("S3", "Eidsvoll");
        Station s4 = new Station("S4", "Ski");
        Station s5 = new Station("S5", "Fredrikstad");

        Rute r1 = new Rute("R1", "Rute 1", true, new ArrayList<>());


        Rute r2 = new Rute("R2", "Rute 2", true, new ArrayList<>());

        // Act
        r1.addStop(s1);
        r1.addStop(s2);
        r1.addStop(s3);
        r1.addStop(s4);

        r2.addStop(s3);
        r2.addStop(s4);
        r2.addStop(s5);

        testRepo.getRuter().add(r1);
        testRepo.getRuter().add(r2);

        String idR1 = r1.getId();
        String idR2 = r2.getId();
        ArrayList<Station> commonStops = testRepo.commonStopsBetweenTwoRoutes(idR1, idR2);

        // Assert
        assertEquals("S3", commonStops.getFirst().getId());
        assertEquals(2, commonStops.size());


    }
}
