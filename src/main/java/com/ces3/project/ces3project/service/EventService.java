package com.ces3.project.ces3project.service;

import com.ces3.project.ces3project.dao.EventDAO;
import com.ces3.project.ces3project.dao.TeamDAO;
import com.ces3.project.ces3project.model.Event;
import com.ces3.project.ces3project.model.Team;
import com.ces3.project.ces3project.utils.UtilMethods;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

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
            throw new NoSuchElementException("There are no events in the database");
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

    public Map<String, Long> getEventsPerSport() {
        List<Event> events = eventDAO.getAll();
        return events.stream()
                .collect(Collectors.groupingBy(Event::getSport, Collectors.counting()));
    }

    public Map<String, Long> getEventsPerTeam() {
        List<Event> events = eventDAO.getAll();

        Map<String, Long> teamEventCount = events.stream()
                .flatMap(event -> event.getTeamsId().stream()
                        .map(teamId -> teamService.getTeamById(teamId)
                                .map(team -> new AbstractMap.SimpleEntry<>(team.getName(), 1L))
                                .orElse(null)))
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)));

        return teamEventCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public Map<String, Double> getOccupancyPerEvent() {
        List<Event> events = eventDAO.getAll();

        Map<String, Double> occupancyMap = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#.00");

        for (Event event : events) {
            String eventName = event.getName();
            Integer capacity = event.getCapacity();
            Integer soldTickets = event.getSoldTickets();

            double occupancyPercentage = 0.0;
            if (capacity != null && capacity > 0) {
                occupancyPercentage = (double) soldTickets / capacity * 100;
                occupancyPercentage = Math.round(occupancyPercentage * 100.0) / 100.0;
            }
            occupancyMap.put(eventName, occupancyPercentage);
        }
        return occupancyMap;
    }
}
