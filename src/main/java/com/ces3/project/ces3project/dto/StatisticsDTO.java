package com.ces3.project.ces3project.dto;

import java.util.ArrayList;
import java.util.Map;

public class StatisticsDTO {
    private Map<String, Long> eventsPerSport;
    private Map<String, Long> teamsWithMostEvents;
    private Map<String, Double> avgPlayersPerTeam;
    private Map<String, Double> occupancyPerEvent;

    public StatisticsDTO(Map<String, Long> eventsPerSport,
                         Map<String, Long> teamsWithMostEvents,
                         Map<String, Double> avgPlayersPerTeam,
                         Map<String, Double> occupancyPerEvent) {
        this.eventsPerSport = eventsPerSport;
        this.teamsWithMostEvents = teamsWithMostEvents;
        this.avgPlayersPerTeam = avgPlayersPerTeam;
        this.occupancyPerEvent = occupancyPerEvent;
    }

    public Map<String, Long> getEventsPerSport() {
        return eventsPerSport;
    }

    public void setEventsPerSport(Map<String, Long> eventsPerSport) {
        this.eventsPerSport = eventsPerSport;
    }

    public Map<String, Long> getTeamsWithMostEvents() {
        return teamsWithMostEvents;
    }

    public void setTeamsWithMostEvents(Map<String, Long> teamsWithMostEvents) {
        this.teamsWithMostEvents = teamsWithMostEvents;
    }

    public Map<String, Double> getAvgPlayersPerTeam() {
        return avgPlayersPerTeam;
    }

    public void setAvgPlayersPerTeam(Map<String, Double> avgPlayersPerTeam) {
        this.avgPlayersPerTeam = avgPlayersPerTeam;
    }

    public Map<String, Double> getOccupancyPerEvent() {
        return occupancyPerEvent;
    }

    public void setOccupancyPerEvent(Map<String, Double> occupancyPerEvent) {
        this.occupancyPerEvent = occupancyPerEvent;
    }

    @Override
    public String toString() {
        return "StatisticsDTO{" +
                "eventsPerSport=" + eventsPerSport +
                ", teamsWithMostEvents=" + teamsWithMostEvents +
                ", avgPlayersPerTeam=" + avgPlayersPerTeam +
                ", occupancyPerEvent=" + occupancyPerEvent +
                '}';
    }
}