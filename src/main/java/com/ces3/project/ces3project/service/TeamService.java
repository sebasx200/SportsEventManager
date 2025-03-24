package com.ces3.project.ces3project.service;

import com.ces3.project.ces3project.dao.TeamDAO;
import com.ces3.project.ces3project.model.Team;

import java.util.List;
import java.util.Optional;

public class TeamService {
    private final TeamDAO teamDAO;
    
    public TeamService(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public Optional<Team> getTeamById(Integer id){
        return teamDAO.get(id);
    }

    public List<Team> getAllTeams(){
        return teamDAO.getAll();
    }

    public void createTeam(Team team) {
        teamDAO.save(team);
    }
    public void updateTeam(Integer id, Team team) {
        teamDAO.update(id, team);
    }
    public void deleteTeam(Team team) {
        teamDAO.delete(team);
    }
}
