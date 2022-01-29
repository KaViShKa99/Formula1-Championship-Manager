package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Formula1ChampionshipTableGUI extends JFrame {

    private final DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Name","Team","Location","Points","Win Prob","Wins","Races"},0);
//    private final DefaultTableModel allRaceDetails = new DefaultTableModel(new Object[]{"Date","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th"},0);
    private final DefaultTableModel raceDetailsTable = new DefaultTableModel(new Object[]{"Date","1st","2nd","3rd","4th"},0);
    private final DefaultTableModel randomRaceTable = new DefaultTableModel(new Object[]{"Rank","Name","Team","Country","Points"},0);
    private final DefaultTableModel winProbTable = new DefaultTableModel(new Object[]{"Name","Team","Starting Position","Ending Position"},0);
//    private final DefaultTableModel winProbTable = new DefaultTableModel(0,0);
    private final Map<String,Formula1Driver> driverHashMap = Formula1ChampionshipManager.getInstance().getDriverMap();
    private final List<Race> raceList = Formula1ChampionshipManager.getInstance().getRaceList();
    private final List<Formula1Driver> driversList = new ArrayList<>(driverHashMap.values());
    private Object[] rows;


    public Formula1ChampionshipTableGUI(){
        searchBox();
        table();
        ascendingOrderOfPointsButton();
        descendingOrderOfPointsButton();
        addRandomRaceButton();
        winingProbability();
        randomRaceTable();
        allCompletedRaceTable();
        setLayout(null);
        setVisible(true);
        setSize(1500,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void tableTopic(String name,int xPos,int yPos,int width,int height){

        Font fn = new Font("Arial",Font.BOLD,20);
        JLabel mainTopic = new JLabel(name);
        mainTopic.setFont(fn);
        mainTopic.setBounds(xPos, yPos,width,height);
        add(mainTopic);

    }

    public void table(){
        JTable table = new JTable(tableModel);
        updateTableModel();
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(50, 100,500,250);
        add(jScrollPane);
    }
    public void allCompletedRaceTable(){
        tableTopic("Information About All Race ",100, 280,500,200);
        JTable racetbl = new JTable(raceDetailsTable);
        updateAllRaceDetailsTable();
        JScrollPane jScrollPane = new JScrollPane(racetbl);
        jScrollPane.setBounds(50, 400,500,300);
        add(jScrollPane);
    }

    public void randomRaceTable(){
        tableTopic("Random Generate Race",950, -50,500,200);
        JTable table3 = new JTable(randomRaceTable);
        JScrollPane jScrollPane = new JScrollPane(table3);
        jScrollPane.setBounds(950, 100,400,250);
        add(jScrollPane);
    }


    public void updateTableModel() {
        tableModel.setRowCount(0);
        for (Formula1Driver race : driversList) {
            tableModel.addRow(new Object[] {race.getName(),race.getTeam(),race.getLocation(),race.getNumberOFPoints(),race.getStartPosition(),race.getNumberOfWins(),race.getNumberOFRaces()});
        }
    }

    public void updateAllRaceDetailsTable() {
        raceDetailsTable.setRowCount(0);
        for(Race race : raceList) {
            raceDetailsTable.addRow(new Object[] {race.getRaceDate(), race.getEndingPositions().get(0).getName(), race.getEndingPositions().get(1).getName(), race.getEndingPositions().get(2).getName(), race.getEndingPositions().get(3).getName()});
        }
    }

    public void updateRandomRaceTable(Race race) {
        randomRaceTable.setRowCount(0);
        List<Formula1Driver> raceDrivers = race.getEndingPositions();
        int[] score = new int[] {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        for(int i = 0; i < raceDrivers.size(); i++) {
            randomRaceTable.addRow(new Object[] {i+1, raceDrivers.get(i).getName(), raceDrivers.get(i).getTeam(), raceDrivers.get(i).getLocation(), score[i]});
        }
    }

    public void updateWinProbTable() {
        winProbTable.setRowCount(0);
        Race race = Formula1ChampionshipManager.getInstance().probabilityBasedRace();
        List<Formula1Driver> endingPositions = race.getEndingPositions();

        for (int i = 0; i < endingPositions.size(); i++) {
            winProbTable.addRow(new Object[] {endingPositions.get(i).getName(), endingPositions.get(i).getTeam(), i+1, endingPositions.get(i).getStartPosition(), endingPositions.get(i).getStartPosition()});
        }
    }


    public void descendingOrderOfPointsButton() {

        CustomButton descOrderOfPoints = new CustomButton("Ascending Order of points");
        descOrderOfPoints.setPosition(600,150);
        descOrderOfPoints.setSizeForCbtn(300,40);
        add(descOrderOfPoints);

        descOrderOfPoints.addActionListener(e -> {
            tableModel.setRowCount(0);
            driversList.sort(new PointsComparator());
            Collections.reverse(driversList);
            updateTableModel();
        });

    }
    public void ascendingOrderOfPointsButton(){
        CustomButton asceOrderOfPoints = new CustomButton("Descending Order of points");
        asceOrderOfPoints.setPosition(600,100);
        asceOrderOfPoints.setSizeForCbtn(300,40);
        add(asceOrderOfPoints);

        asceOrderOfPoints.addActionListener(e -> {
            tableModel.setRowCount(0);
            driversList.sort(new PointsComparator());
            updateTableModel();
        });
    }


    public void addRandomRaceButton(){
        CustomButton raceGenerate = new CustomButton("Generate Race");
        raceGenerate.setPosition(600,200);
        raceGenerate.setSizeForCbtn(300,40);
        add(raceGenerate);
        raceGenerate.addActionListener(e -> {
            Race race = Formula1ChampionshipManager.getInstance().addRandomGenerateRace();
            updateTableModel();
            updateRandomRaceTable(race);
            updateAllRaceDetailsTable();
        });
    }

    public void winingProbability(){
        CustomButton winProbBtn = new CustomButton("Probability based race");
        winProbBtn.setPosition(600,250);
        winProbBtn.setSizeForCbtn(300,40);
        add(winProbBtn);

        tableTopic("Probability Based Race",950, 220,400,300);

        JTable table4 = new JTable(winProbTable);
        JScrollPane jScrollPane = new JScrollPane(table4);
        jScrollPane.setBounds(950, 400,400,300);
        add(jScrollPane);

        winProbBtn.addActionListener(e -> {
            updateWinProbTable();
            updateAllRaceDetailsTable();
        });
    }


    public void searchBox(){
        String[] array = new String[driversList.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = driversList.get(i).getName();
        }

        JComboBox jcb = new JComboBox(array);

        JTextField search = new JTextField();
        search.setBounds(50, 50,410,30);
        add(search);
        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(470,50,80,30);
        add(searchBtn);


//search box pop up menu

        JPopupMenu menu = new JPopupMenu();
        JMenuItem jm = new JMenuItem();
        menu.setPopupSize(410,200);
        add(menu);

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    for (int i = 0; i < driversList.size(); i++) {
                        tableModel.removeRow(0);
                    }

                }catch (IndexOutOfBoundsException er){
                    System.out.println(er);
                }


                System.out.println(tableModel.getRowCount());

                for (Formula1Driver driver :driversList){

                    if (search.getText().equals(driver.getName())){

                        rows = new Object[] {driver.getName(),driver.getTeam(),driver.getLocation(),driver.getNumberOFPoints(),driver.getNumberOfWins(),driver.getNumberOFRaces() };
                        tableModel.addRow(rows);

                    }

                }

                search.setText("");
            }
        });

    }



}
