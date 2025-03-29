package com.ces3.project.ces3project.service;

import com.ces3.project.ces3project.dto.StatisticsDTO;

public class StatisticsService {
    PlayerService playerService;
    TeamService teamService;
    EventService eventService;

    public StatisticsService(PlayerService playerService, TeamService teamService, EventService eventService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.eventService = eventService;
    }
    public StatisticsDTO getStatisticsDTO() {
        return new StatisticsDTO(
                eventService.getEventsPerSport(),
                eventService.getEventsPerTeam(),
                teamService.getAvgPlayerPerTeam(),
                eventService.getOccupancyPerEvent()
        );
    }
}
