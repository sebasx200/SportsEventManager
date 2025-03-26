package com.ces3.project.ces3project.model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private Integer id;
    private String name;
    private Date date;
    private String place;
    private String sport;
    private ArrayList<Integer> teamsId;
    private Integer capacity;
    private Integer soldTickets;
    private EventStatus status;

    public Event() {}

    public Event(String name, Date date, String place, String sport, ArrayList<Integer> teamsId, Integer capacity, Integer soldTickets, EventStatus status) {
        this.name = name;
        this.date = date;
        this.place = place;
        this.sport = sport;
        this.teamsId = teamsId;
        this.capacity = capacity;
        this.soldTickets = soldTickets;
        this.status = status;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public ArrayList<Integer> getTeamsId() {
        return teamsId;
    }

    public void setTeamsId(ArrayList<Integer> teamsId) {
        this.teamsId = teamsId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Integer soldTickets) {
        this.soldTickets = soldTickets;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", place='" + place + '\'' +
                ", sport='" + sport + '\'' +
                ", teamsId=" + teamsId +
                ", capacity=" + capacity +
                ", soldTickets=" + soldTickets +
                ", status=" + status +
                '}';
    }
}
