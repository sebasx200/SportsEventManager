package com.ces3.project.ces3project.model;

import java.util.Date;

public class Player {

    private Integer id;
    private String name;
    private String lastName;
    private Date birthDate;
    private String nationality;
    private String position;
    private Integer number;
    private Integer teamId;
    private Boolean isActive;

    public Player() {
    }

    public Player(Integer id, String name, String lastName, Date birthDate, String nationality, String position, Integer number, Integer teamId, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.position = position;
        this.number = number;
        this.teamId = teamId;
        this.isActive = isActive;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                ", position='" + position + '\'' +
                ", number=" + number +
                ", teamId=" + teamId +
                ", isActive=" + isActive +
                '}';
    }
}
