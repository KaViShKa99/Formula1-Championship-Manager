package com.company;


import java.io.*;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager {


    private static Formula1ChampionshipManager instance;
    private final Scanner input = new Scanner(System.in);
    private final Scanner getChoice = new Scanner(System.in);
    private final InputValidation inputValidation = new InputValidation();
    private static Map<String,Formula1Driver> driverMap = new HashMap<>();
    private List<Race> races = new ArrayList<>();


    private Formula1ChampionshipManager(){

    }

    @Override
    public Map<String, Formula1Driver> getDriverMap() {
        return driverMap;
    }

    public List<Race> getRaceList(){return races;}

    public static Formula1ChampionshipManager getInstance(){
        if (instance == null){
            instance = new Formula1ChampionshipManager();
        }
        return instance;
    }

    public void mainMenu(){
        int choice = 0;
        while(choice != 5){

            System.out.println("------------------------------------------------------------");
            System.out.println("----------- * Add New Driver               ----- press num 1");
            System.out.println("----------- * Delete Driver                ----- press num 2");
            System.out.println("----------- * Changing Driver Team         ----- press num 3");
            System.out.println("----------- * Display About Driver Details ----- press num 4");
            System.out.println("----------- * Add Race                     ----- press num 5");
            System.out.println("----------- * Open GUI                     ----- press num 6");
            System.out.println("------------------------------------------------------------\n");

            choice = getChoice.nextInt();
            switch (choice) {
                case 1:
                    createDriver();
                    break;
                case 2:
                    deleteDriver();
                    break;

                case 3:
                    changeDriver();
                    break;
                case 4:
                    displayAboutSelectedDriver();
                    break;
                case 5:
                    addRace();
                    break;
                case 6:
                    Formula1ChampionshipMainMenuGUI f1 = new Formula1ChampionshipMainMenuGUI();
                    break;
            }
        }
    }

    public void createDriver(){

        String yesNoChoice="";

        yesNoChoice = inputValidation.inputStringCheck("Do you want to add a race ?(y/n)");
//        System.out.println(yesNoChoice);
        while (yesNoChoice.equals("y")){

            System.out.println("\n");
            String driverName = inputValidation.inputStringCheck("Enter Driver Name : ");
            String driverLocation = inputValidation.inputStringCheck("Enter Driver Location : ");
            String driverConstructor = inputValidation.inputStringCheck("Enter Driver Team : ");
            driverMap.put(driverConstructor,new Formula1Driver(driverName,driverLocation,driverConstructor));

            writeData();
            yesNoChoice = inputValidation.inputStringCheck("Do you want to add a race ?(y/n)");
        }

    }

    public void deleteDriver (){
        String deleteDriverName = inputValidation.inputStringCheck("Enter Driver Team : ");
        driverMap.remove(deleteDriverName);

    }

    public void changeDriver(){
        String newName = inputValidation.inputStringCheck("Enter the team name who wants to change ? ");
        String newLocation = inputValidation.inputStringCheck("Enter the location who wants to change ? ");
        String newTeam = inputValidation.inputStringCheck("Enter the driver name who wants to change the team ? ");
        driverMap.put(newName,new Formula1Driver(newName,newLocation,newTeam));
    }


    public void displayAboutSelectedDriver(){
        String selectedDriver = inputValidation.inputStringCheck("Enter Driver Name for get statistics :");
        for (Formula1Driver driverDetails : driverMap.values()){
            if (driverDetails.getName().toLowerCase(Locale.ROOT).equals(selectedDriver.toLowerCase(Locale.ROOT))) {
                System.out.println("Driver statistics");
                System.out.println("Name \tCountry \tTeam");
                System.out.printf("%-8s %-10s %-10s%n", driverDetails.getName(), driverDetails.getLocation(), driverDetails.getTeam());
            }
        }
    }

    public void displayAllDrivers(){
        List<Formula1Driver> list = new ArrayList<>(driverMap.values());
        list.sort(new PointsComparator());
        System.out.println("Rank\t Driver\t Points\t Wins \t Date Of Race");
        int rank = 1;
        for (Formula1Driver driverInfo : list){
            String sf = String.format(" %d  %-6s %-6d %-6d %-6s ",rank,driverInfo.getName(),driverInfo.getNumberOFPoints(),driverInfo.getNumberOfWins(),driverInfo.getDate());
            System.out.println(sf);
            rank++;
        }
    }

    public void writeData()  {
        try{
            FileOutputStream fout=new FileOutputStream("driverDetails.txt",false);
            ObjectOutputStream out=new ObjectOutputStream(fout);
            out.writeObject(driverMap);
            out.flush();
            out.close();
            fout.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void readData(){
        try{
            FileInputStream fileInputStream = new FileInputStream("driverDetails.txt");
            ObjectInputStream ob = new ObjectInputStream(fileInputStream);
            driverMap = (Map<String, Formula1Driver>)ob.readObject();
            ob.close();
            fileInputStream.close();

        }catch (IOException | ClassNotFoundException e){
//            e.printStackTrace();
            System.out.println("Driver Details Info File not found");
        }
    }

    public void writeAllRaces()  {
        try{
            FileOutputStream allRaces=new FileOutputStream("allRaces.txt",false);
            ObjectOutputStream out=new ObjectOutputStream(allRaces);
            out.writeObject(races);
            out.flush();
            out.close();
            allRaces.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void readAllRaces(){
        try{
            FileInputStream fileInputStream = new FileInputStream("allRaces.txt");
            ObjectInputStream ob = new ObjectInputStream(fileInputStream);
            races = (List<Race>)ob.readObject();
            ob.close();
            fileInputStream.close();

        }catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println("All Races Info File not found");
        }
    }

    public void addRace() {
        int[] score = new int[] {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        List<Formula1Driver> manuallyAddedDriver = new ArrayList<>(driverMap.values());
        String yesNoChoice="";
        int player = 0;
        String raceDate = "";
        yesNoChoice = inputValidation.inputStringCheck("Do you want to add a race ?(y/n)");
         System.out.println(yesNoChoice);
         while (yesNoChoice.equals("y")){



             driverListPrint();

             raceDate = inputValidation.inputDateCheck("Enter date of the race (\"dd-MM-yyyy\") :");
             while (player != 1){
                int driverNo = inputValidation.inputIntegerCheck("Enter the Driver No");
                int newRank = inputValidation.inputIntegerCheck("Enter the Driver Rank ");
                manuallyAddedDriver.get(driverNo-1).addScore(score[newRank]);
                manuallyAddedDriver.get(driverNo-1).setDate(raceDate);
                player++;
            }
            player = 0;

             formula1ChampionshipTable();
        }
     }

    public void driverListPrint(){
        String printFormat;
        int position = 1;
            System.out.println("No \tDriver \tLocation");

        for (Formula1Driver driver:driverMap.values()){
            System.out.printf("%-8d %-10s %-10s%n",position,driver.getName(),driver.getLocation());
            position++;
        }

    }
    public Race addRandomGenerateRace(){
        int rank =1 ;
        List<Formula1Driver> readListKeys = new ArrayList<>(driverMap.values());
        Collections.shuffle(readListKeys);

        int[] score = new int[] {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        int[] winPercentage = new int[] {40,30,10,10,2,2,2,2,2,0};
        int[] raceRank = new int[] {1,2,3,4,5,6,7,8,9,10};

        for(int i = 0; i < readListKeys.toArray().length; i++) {
            readListKeys.get(i).addScore(score[i]);
            readListKeys.get(i).setStartPosition(winPercentage[i]);
            readListKeys.get(i).setRank(raceRank[i]);
        }

        readListKeys.get(0).addFirstPosition();
        System.out.println("Name\tTeam\tCountry\tPoints\tWins");

        for(Formula1Driver formula1Driver : readListKeys){
            String driversList =String.format( "%-2d %-6s %-12s %-6s %-6d %-2d",rank,formula1Driver.getName(),formula1Driver.getTeam(),formula1Driver.getLocation(),score[rank],formula1Driver.getNumberOfWins());
            System.out.println(driversList);
            rank ++;

        }

        Race randomRace = new Race(randomDate(), readListKeys);
        races.add(randomRace);
        writeData();
        writeAllRaces();
        return randomRace;
    }

    public Race probabilityBasedRace() {
        int[] score = new int[] {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        List<Formula1Driver> startingPositions = new ArrayList<>(driverMap.values());
        Collections.shuffle(startingPositions);
        for(int i = 0; i < startingPositions.size(); i++) {
            startingPositions.get(i).setStartPosition(i+1);
        }
        List<Formula1Driver> endingPositions = new ArrayList<>();
        int random = (int)(Math.random() * 100);

        if(random < 40) {
            endingPositions.add(startingPositions.get(0));
            startingPositions.remove(0);
        }
        else if(random < 70) {
            endingPositions.add(startingPositions.get(1));
            startingPositions.remove(1);
        }
        else if (random < 90) {
            if(Math.random() < 0.5) {
                endingPositions.add(startingPositions.get(2));
                startingPositions.remove(2);
            }
            else {
                endingPositions.add(startingPositions.get(3));
                startingPositions.remove(3);
            }
        }
        else {

            int position = (int) ((Math.random() * (8 - 4)) + 4);

               try {

                endingPositions.add(startingPositions.get(position));
                startingPositions.remove(position);

               }catch (IndexOutOfBoundsException err){
                   System.out.println("");
               }



        }
        endingPositions.addAll(startingPositions);
        for(int i = 0; i < endingPositions.size(); i++) {
            endingPositions.get(i).addScore(score[i]);
        }

        Race randomRace = new Race(randomDate(), endingPositions);
//        races.add(randomRace);


        return randomRace;
    }

    public void winingProbability(List<Formula1Driver> readListKeys){
        System.out.println("Name\tTeam\tCountry\tWin prob %");

        for(Formula1Driver formula1Driver : readListKeys){
            System.out.printf("%-6s %-12s %-6s %-6d %n",formula1Driver.getName(),formula1Driver.getTeam(),formula1Driver.getLocation(),formula1Driver.getStartPosition());

        }
    }


    public void formula1ChampionshipTable(){
        System.out.println("\n\n");
        System.out.println("Rank\t Driver\t Points\t Wins \t Number Of Race");
        int rank = 1;
        for (Formula1Driver driverInfo : driverMap.values()){
            String sf = String.format(" %d  Name: %-6s Points: %-6d Wins: %-6d ",rank,driverInfo.getName(),driverInfo.getNumberOFPoints(),driverInfo.getNumberOfWins());
            System.out.println(sf);
            rank++;
        }
    }

    public  String randomDate() {
        int startYear=2021;
        int endYear=2022;
        int year = (int)(Math.random()*(endYear-startYear+1))+startYear;
        int month= (int)(Math.random()*12)+1;
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        int day=(int)(Math.random()*dayOfMonth+1)	;
        return year + "-" + month + "-" + day;
    }

}
