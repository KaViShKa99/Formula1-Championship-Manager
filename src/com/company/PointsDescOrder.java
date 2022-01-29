package com.company;

import java.util.Comparator;

public class PointsDescOrder implements Comparator<Formula1Driver> {

    public int compare(Formula1Driver d1,Formula1Driver d2){
        if (d1.getNumberOFPoints() == d2.getNumberOFPoints()){
            if (d1.getNumberOfWins() == d2.getNumberOfWins()){
                return 0;
            }else if ( d1.getNumberOfWins() < d2.getNumberOfWins()){
                return 1;
            }else {
                return -1;
            }
        }else if (d1.getNumberOFPoints()<d2.getNumberOFPoints()){
            return 1;
        }else{
            return -1;
        }
    }

}
