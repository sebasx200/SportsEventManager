package com.ces3.project.ces3project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Team {
    private Integer id;
    private String name;
    private String sport;
    private String city;
    private Date fundationDate;
    private String logo;
    private ArrayList<Player> teamPlayers;

    public Team(String name, String sport, String city, Date fundationDate, String logo, ArrayList<Player> teamPlayers) {

        this.name = name;
        this.sport = sport;
        this.city = city;
        this.fundationDate = fundationDate;
        this.logo = logo;
        this.teamPlayers = teamPlayers;
    }

    public Team(String name, String sport, String city, Date fundationDate, String logo) {
        this.name = name;
        this.sport = sport;
        this.city = city;
        this.fundationDate = fundationDate;
        this.logo = logo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getFundationDate() {
        return fundationDate;
    }

    public void setFundationDate(Date fundationDate) {
        this.fundationDate = fundationDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ArrayList<Player> getTeamPlayers() { return teamPlayers;}

    public void setTeamPlayers(ArrayList<Player> teamPlayers) { this.teamPlayers = teamPlayers; }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sport='" + sport + '\'' +
                ", city='" + city + '\'' +
                ", fundationDate=" + fundationDate +
                ", logo='" + logo + '\'' +
                ", playersId=" + teamPlayers +
                '}';
    }
}
