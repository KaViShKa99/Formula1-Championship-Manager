package com.company;

import java.util.Comparator;

public class PointsComparator implements Comparator<Formula1Driver> {
    public int compare(Formula1Driver d1,Formula1Driver d2){
        if (d1.getNumberOFPoints() == d2.getNumberOFPoints()){
            return Integer.compare(d2.getNumberOfWins(), d1.getNumberOfWins());
        }if (d1.getNumberOFPoints() < d2.getNumberOFPoints()){
            return 1;
        }else{
            return -1;
        }
    }
}
