package com.ces3.project.ces3project.model;

import java.util.ArrayList;
import java.util.Date;

public class Team {
    private Integer id;
    private String name;
    private String sport;
    private String city;
    private Date fundationDate;
    private String logo;
    private ArrayList<Integer> playersId;

    public Team() {}

    public Team(Integer id, String name, String sport, String city, Date fundationDate, String logo, ArrayList<Integer> playersId) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.city = city;
        this.fundationDate = fundationDate;
        this.logo = logo;
        this.playersId = playersId;
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

    public ArrayList<Integer> getPlayersId() {
        return playersId;
    }

    public void setPlayersId(ArrayList<Integer> playersId) {
        this.playersId = playersId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sport='" + sport + '\'' +
                ", city='" + city + '\'' +
                ", fundationDate=" + fundationDate +
                ", logo='" + logo + '\'' +
                ", playersId=" + playersId +
                '}';
    }
}
