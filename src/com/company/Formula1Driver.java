package com.company;

public class Formula1Driver extends Driver {

    private int countOfFirstPositions = 0;
    private int countOfSecondPositions = 0;
    private int countOfThirdPositions = 0;
    private int numberOFPoints = 0;
    private int numberOFRaces = 0;
    private int rank = 0;
    private String date = null;
    private int startPosition =0;

//    private String team;

    public Formula1Driver(String name, String location,String team) {
        super(name, location,team);
    }


    public int getNumberOFRaces(){
        return numberOFRaces;
    }

    public void addScore(int point) {
        this.numberOFPoints += point;
    }

    public int getNumberOfWins(){
        return countOfFirstPositions;
    }

    public int getNumberOFPoints(){
        return numberOFPoints;
    }

    public void addFirstPosition(){
        countOfFirstPositions++;
    }

    public void setDate(String date){this.date = date;}

    public String getDate(){
        return  date;
    }

    public void setStartPosition(int winProb){
        this.startPosition = winProb;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getRank(){
        return  rank;
    }

    public int getStartPosition(){
        return startPosition;
    }

}
