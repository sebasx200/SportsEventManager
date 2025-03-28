package com.ces3.project.ces3project.dao;

import com.ces3.project.ces3project.model.Team;

import java.util.*;

public class TeamDAO implements Dao<Team> {

    private final List<Team> teams = new ArrayList<>();

    @Override
    public Optional<Team> get(Integer id) {
        return teams.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Team> getAll() {
        return new ArrayList<>(teams);
    }
    public List<Team> getAllTeams(Integer page, Integer size) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (size == null || size < 1) {
            size = 10;
        }

        int fromIndex = (page - 1) * size;
        if (fromIndex >= teams.size()) {
            return Collections.emptyList();
        }
        int toIndex = Math.min(fromIndex + size, teams.size());
        return teams.subList(fromIndex, toIndex);
    }

    @Override
    public void save(Team team) {
        teams.add(team);
    }

    @Override
    public void update(Integer id, Team team) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getId().equals(id)) {
                teams.set(i, team);
                return;
            }
        }
        throw new NoSuchElementException("Team with ID " + id + " not found.");
    }

    @Override
    public void delete(Team team) {
        teams.removeIf(p -> p.getId().equals(team.getId()));
    }
}
