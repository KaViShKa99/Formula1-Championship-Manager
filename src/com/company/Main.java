package com.company;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {



//        ChampionshipManager championshipManager = new Formula1ChampionshipManager();
        ChampionshipManager championshipManager = Formula1ChampionshipManager.getInstance();

        championshipManager.readData();
        championshipManager.readAllRaces();
        championshipManager.mainMenu();
//        championshipManager.writeData();
//        championshipManager.writeAllRaces();




//swing application

//        Formula1ChampionshipMainMenuGUI f1 = new Formula1ChampionshipMainMenuGUI();
//        Formula1ChampionshipTableGUI f2 = new  Formula1ChampionshipTableGUI();
//         new Test();

    }
}
