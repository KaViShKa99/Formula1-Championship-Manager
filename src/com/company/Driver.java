package com.company;

import java.io.Serializable;

public abstract class Driver implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
    private String name;
    private String location;
    private String team;

    public Driver(String name,String location,String team){
        this.name = name;
        this.team = team;
        this.location = location;
    }
    public Driver(String name,String location){
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }
    public String getTeam(){
        return team;
    }
    public String getLocation(){
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
