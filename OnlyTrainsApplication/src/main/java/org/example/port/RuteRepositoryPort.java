package org.example.port;

import org.example.model.Rute;

public interface RuteRepositoryPort {
    public void loadRoutesFromJson(String filepath);
    public Rute getRuteByName(String name);
}
