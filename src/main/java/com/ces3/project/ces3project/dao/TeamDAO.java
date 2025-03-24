package com.ces3.project.ces3project.dao;

import com.ces3.project.ces3project.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TeamDAO implements Dao<Team> {

    private List<Team> teams = new ArrayList<>();

    @Override
    public Optional<Team> get(Integer id) {
        return teams.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Team> getAll() {
        return new ArrayList<>(teams);
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
