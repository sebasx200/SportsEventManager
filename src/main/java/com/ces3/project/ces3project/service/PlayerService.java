package com.ces3.project.ces3project.service;

import com.ces3.project.ces3project.dao.PlayerDAO;
import com.ces3.project.ces3project.dto.TeamDTO;
import com.ces3.project.ces3project.model.Player;
import com.ces3.project.ces3project.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerService {
    PlayerDAO playerDAO;
    TeamService teamService;

    public PlayerService(PlayerDAO playerDAO, TeamService teamService) {
        this.playerDAO = playerDAO;
        this.teamService = teamService;
    }
    public Optional<Player> getPlayerById(Integer id){
        return playerDAO.get(id);
    }

    public ArrayList<Player> getPlayersByIds(ArrayList<Integer> playerIds) {
        ArrayList<Player> players = new ArrayList<>();
        for (Integer id : playerIds) {
            playerDAO.get(id).ifPresent(players::add);
        }
        return players;
    }

    public List<Player> getAllPlayers(){
        List<Player> players = playerDAO.getAll();
        for (Player player : players) {
            Optional<Team> teamOptional = teamService.getTeamById(player.getTeamId());
            if (teamOptional.isPresent()) {
                Team team = teamOptional.get();
                TeamDTO teamDTO = new TeamDTO(team.getName());
                player.setTeam(teamDTO);
            }
        }
        return players;
    }

    public void createPlayer(Player player) {
        Optional<Player> existingPlayer = playerDAO.get(player.getId());
        if (existingPlayer.isPresent()) {
            throw new IllegalArgumentException("Player with ID " + player.getId() + " already exists.");
        }
        Optional<Team> team = teamService.getTeamById(player.getTeamId());
        if (team.isEmpty()) {
            throw new IllegalArgumentException("Team with ID " + player.getTeamId() + " does not exist.");
        }
        teamService.addPlayerToTeam(player, player.getTeamId());
        playerDAO.save(player);
    }
    public void transferPlayerToTeam(Integer playerId, Integer teamId) {
        Optional<Player> existingPlayer = playerDAO.get(playerId);
        if (existingPlayer.isEmpty()) {
            throw new IllegalArgumentException("Player with ID " + playerId + " does not exist.");
        }
        Optional<Team> existingTeam = teamService.getTeamById(teamId);
        if (existingTeam.isEmpty()) {
            throw new IllegalArgumentException("Team with ID " + teamId + " does not exist.");
        }

        Player player = existingPlayer.get();
        Team team = existingTeam.get();

        if (team.getTeamPlayers() != null && team.getTeamPlayers().contains(playerId)) {
            throw new IllegalArgumentException("The player with ID " + playerId + " already belongs to the team.");
        }

        Optional<Team> oldTeamPlayer = teamService.getTeamById(player.getTeamId());
        if (oldTeamPlayer.isPresent()) {
            Team oldTeam = oldTeamPlayer.get();
            oldTeam.removePlayer(playerId);
            teamService.updateTeam(oldTeam.getId(), oldTeam);
        }
        player.setTeamId(teamId);
        teamService.addPlayerToTeam(player, team.getId());
        teamService.updateTeam(team.getId(), team);
    }
    public void updatePlayer(Integer id, Player player) {
        playerDAO.update(id, player);
    }
    public void deletePlayer(Player player) {
        playerDAO.delete(player);
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }
}
