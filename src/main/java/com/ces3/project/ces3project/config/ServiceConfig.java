package com.ces3.project.ces3project.config;

import com.ces3.project.ces3project.dao.EventDAO;
import com.ces3.project.ces3project.dao.PlayerDAO;
import com.ces3.project.ces3project.dao.TeamDAO;
import com.ces3.project.ces3project.service.EventService;
import com.ces3.project.ces3project.service.PlayerService;
import com.ces3.project.ces3project.service.TeamService;

public class ServiceConfig {
    public static PlayerDAO playerDAO;
    public static PlayerService playerService;
    public static TeamDAO teamDAO;
    public static TeamService teamService;
    public static EventDAO eventDAO;
    public static EventService eventService;

    static {
        playerDAO = new PlayerDAO();
        teamDAO = new TeamDAO();
        eventDAO = new EventDAO();

        playerService = new PlayerService(playerDAO, null);
        teamService = new TeamService(teamDAO, playerService);
        eventService = new EventService(eventDAO, teamService);

        playerService.setTeamService(teamService);
    }

    public static PlayerService getPlayerService() {
        return playerService;
    }

    public static TeamService getTeamService() {
        return teamService;
    }

    public static EventService getEventService() {
        return eventService;
    }

    public static PlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    public static TeamDAO getTeamDAO() {
        return teamDAO;
    }

    public static EventDAO getEventDAO() {
        return eventDAO;
    }
}
