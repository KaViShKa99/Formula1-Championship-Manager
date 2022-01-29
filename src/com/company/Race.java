package com.company;

import java.io.Serializable;
import java.util.List;

public class Race implements Serializable {

    private final String raceDate;
    private final List<Formula1Driver> endingPositions;


    public Race(String raceDate, List<Formula1Driver> startingPostions){
        this.raceDate = raceDate;
        this.endingPositions = startingPostions;
    }


    public List<Formula1Driver> getEndingPositions() {
        return endingPositions;
    }


    public String getRaceDate() {
        return raceDate;
    }

}
