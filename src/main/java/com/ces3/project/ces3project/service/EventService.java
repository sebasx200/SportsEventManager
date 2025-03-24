package com.ces3.project.ces3project.service;

import com.ces3.project.ces3project.dao.EventDAO;
import com.ces3.project.ces3project.model.Event;

import java.util.List;
import java.util.Optional;

public class EventService {
    private final EventDAO eventDAO;

    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public Optional<Event> getEventById(Integer id){
        return eventDAO.get(id);
    }

    public List<Event> getAllEvents(){
        return eventDAO.getAll();
    }

    public void createEvent(Event event) {
        eventDAO.save(event);
    }
    public void updateEvent(Integer id, Event event) {
        eventDAO.update(id, event);
    }
    public void deleteEvent(Event event) {
        eventDAO.delete(event);
    }
}
