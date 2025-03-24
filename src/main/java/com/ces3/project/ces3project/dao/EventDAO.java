package com.ces3.project.ces3project.dao;

import com.ces3.project.ces3project.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class EventDAO implements Dao<Event> {

    private List<Event> events = new ArrayList<>();

    @Override
    public Optional<Event> get(Integer id) {
        return events.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Event> getAll() {
        return new ArrayList<>(events);
    }

    @Override
    public void save(Event event) {
        events.add(event);
    }

    @Override
    public void update(Integer id, Event event) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equals(id)) {
                events.set(i, event);
                return;
            }
        }
        throw new NoSuchElementException("Event with ID " + id + " not found.");
    }

    @Override
    public void delete(Event event) {
        events.removeIf(p -> p.getId().equals(event.getId()));
    }
}
