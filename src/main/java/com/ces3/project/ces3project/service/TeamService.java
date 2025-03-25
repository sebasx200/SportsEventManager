package com.ces3.project.ces3project.service;

import com.ces3.project.ces3project.dao.TeamDAO;
import com.ces3.project.ces3project.model.Player;
import com.ces3.project.ces3project.model.Team;
import com.ces3.project.ces3project.utils.UtilMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamService {
    private final TeamDAO teamDAO;

    private static final int LENGTH_ID_TEAM = 10;

    public TeamService(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public Optional<Team> getTeamById(Integer id){
        return teamDAO.get(id);
    }

    public List<Team> getAllTeams() {
        List<Team> teams = teamDAO.getAll();
        for (Team team : teams) {
            team.setTeamPlayers(new ArrayList<>());
        }
        return teams;
    }

    public void createTeam(Team team) {
        DuplicateStatus status = isTeamDuplicate(team);

        switch (status) {
            case NO_DUPLICATE:
                team.setId(UtilMethods.generateUniqueBigInteger(LENGTH_ID_TEAM).intValue());
                teamDAO.save(team);
                break;
            case NAME_DUPLICATE:
                throw new IllegalArgumentException("A team with the same name already exists.");
            case SPORT_DUPLICATE:
                throw new IllegalArgumentException("A team with the same sport already exists.");
        }
    }
    public void updateTeam(Integer id, Team team) {
        teamDAO.update(id, team);
    }
    public void deleteTeam(Team team) {
        teamDAO.delete(team);
    }

    private DuplicateStatus isTeamDuplicate(Team team) {
        return teamDAO.getAll().stream()
                .filter(existingTeam -> existingTeam.getName().equalsIgnoreCase(team.getName()))
                .findFirst().map(t -> DuplicateStatus.NAME_DUPLICATE)
                .orElseGet(() -> teamDAO.getAll().stream()
                        .filter(existingTeam -> existingTeam.getSport().equalsIgnoreCase(team.getSport()))
                        .findFirst().map(t -> DuplicateStatus.SPORT_DUPLICATE)
                        .orElse(DuplicateStatus.NO_DUPLICATE));
    }

    public enum DuplicateStatus {
        NAME_DUPLICATE,
        SPORT_DUPLICATE,
        NO_DUPLICATE
    }
}
