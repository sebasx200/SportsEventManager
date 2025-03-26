package com.ces3.project.ces3project.service;

import com.ces3.project.ces3project.dao.EventDAO;
import com.ces3.project.ces3project.dao.TeamDAO;
import com.ces3.project.ces3project.model.Event;
import com.ces3.project.ces3project.model.Team;
import com.ces3.project.ces3project.utils.UtilMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventService {
    EventDAO eventDAO;
    TeamService teamService;
    private static final int LENGTH_ID_EVENT = 10;

    public EventService(EventDAO eventDAO, TeamService teamService) {
        this.eventDAO = eventDAO;
        this.teamService = teamService;
    }

    public Optional<Event> getEventById(Integer id){
        return eventDAO.get(id);
    }

    public List<Event> getAllEvents(){
        if (eventDAO.getAll().isEmpty()){
            throw new ArrayIndexOutOfBoundsException("There are no events in the database");
        }
        return eventDAO.getAll();
    }

    public void createEvent(Event event) {

        if (event.getTeamsId() == null || event.getTeamsId().size() < 2) {
            throw new IllegalArgumentException("An event must have at least two participating teams.");
        }
        List<Optional<Team>> teams = new ArrayList<>();
        for (Integer teamId : event.getTeamsId()) {
            Optional<Team> team = teamService.getTeamById(teamId);
            if (team.isEmpty()) {
                throw new IllegalArgumentException("Team with ID " + teamId + " not found.");
            }
            teams.add(team);
        }
        String sport = teams.getFirst().orElseThrow().getSport();
        boolean sameSport = teams.stream().allMatch(t -> t.orElseThrow().getSport().equalsIgnoreCase(sport));
        if (!sameSport) {
            throw new IllegalArgumentException("All teams must participate in the same sport.");
        }
        event.setId(UtilMethods.generateUniqueBigInteger(LENGTH_ID_EVENT).intValue());
        eventDAO.save(event);
    }
    public void updateEvent(Integer id, Event event) {
        eventDAO.update(id, event);
    }
    public void deleteEvent(Event event) {
        eventDAO.delete(event);
    }
}
