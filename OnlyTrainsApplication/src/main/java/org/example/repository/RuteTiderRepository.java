package org.example.repository;

public interface RuteTiderRepository {

    // Dette lagrer Rutene for hvert tog, samtidig avgangstider og ankomstider
    // Merk at første stopp vil ikke ha en ankomsttid, og siste stopp vil ikke ha en avgangstid
    // eksempel på lagring: Rute1 : { (oslo S, NULL, 08:00), (Fredrikstad, 08:50, 08:55)}
}
