package org.example.repository;

import org.example.model.Rute;
import org.example.model.Station;

import java.util.ArrayList;

public class RuteRepository {
    // Dette inneholder en samling av alle rutene
    private ArrayList<Rute> ruter = new ArrayList<>();

    public RuteRepository() {

        Rute l2 = new Rute("L2");
        Station stabekk = new Station("1", "stabekk");
        l2.addStop(stabekk);
        Station lysaker = new Station("2", "Lysaker");
        l2.addStop(lysaker);
        Station skoyen = new Station("3", "Skøyen");
        l2.addStop(skoyen);
        Station nationaltheatret = new Station("4", "Nationaltheatret");
        l2.addStop(nationaltheatret);
        Station osloS = new Station("5", "Oslo S");
        l2.addStop(osloS);
        Station nordstrand = new Station("6", "Nordstrand");
        l2.addStop(nordstrand);
        Station ljan = new Station("7", "Ljan");
        l2.addStop(ljan);
        Station hauketo = new Station("8", "Hauketo");
        l2.addStop(hauketo);
        Station holmlia = new Station("9", "Holmlia");
        l2.addStop(holmlia);
        Station rosenholm = new Station("10", "Rosenholm");
        l2.addStop(rosenholm);
        Station kolbotn = new Station("11", "Kolbotn");
        l2.addStop(kolbotn);
        Station solbratan = new Station("12", "Solbråtan");
        l2.addStop(solbratan);
        Station myrvoll = new Station("13", "Myrvoll");
        l2.addStop(myrvoll);
        Station greverud = new Station("14", "Greverud");
        l2.addStop(greverud);
        Station oppegard = new Station("15", "Oppegård");
        l2.addStop(oppegard);
        Station vevelstad = new Station("16", "Vevelstad");
        l2.addStop(vevelstad);
        Station langhus = new Station("17", "Langhus");
        l2.addStop(langhus);
        Station ski = new Station("18", "Ski");
        l2.addStop(ski);

        this.ruter.add(l2);
    }

    public ArrayList<Rute> getRuter() {
        return ruter;
    }

    public Rute getRuteByName(String ruteName){
        for (Rute rute : ruter){
            // Vi sjekker rute repositoriet vårt for en rute.
            if (ruteName.toUpperCase().equals(rute.getId())){
                return rute;
            }
        }
        return null;
    }
}
