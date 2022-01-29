package com.company;

import java.util.List;
import java.util.Map;

public interface ChampionshipManager {

    void mainMenu();
    void createDriver();
    void deleteDriver();
    void displayAboutSelectedDriver();
    void displayAllDrivers();
    void changeDriver();
//    void saveDrivers();
    void writeData();
    void readData();
    Race addRandomGenerateRace();
    void formula1ChampionshipTable();
    void addRace();
    Map<String, Formula1Driver> getDriverMap();
    List<Race> getRaceList();
    String randomDate();
    void writeAllRaces();
    void readAllRaces();


}
