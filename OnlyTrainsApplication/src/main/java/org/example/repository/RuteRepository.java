package org.example.repository;

import org.example.model.Rute;
import org.example.model.Station;

import java.util.List;

public interface RuteRepository {

    // Dette er et repository for å lagre selve rutene til togene
    void lagre(Rute rute);
    Rute finnEtterNavn(String navn);
    List<Rute> finnAlle();
    List<Rute> finnRuterGjennomStasjon(Station stasjon);


}
