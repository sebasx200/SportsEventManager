package com.ces3.project.ces3project.service;

import com.ces3.project.ces3project.dao.TeamDAO;
import com.ces3.project.ces3project.dto.PaginationDTO;
import com.ces3.project.ces3project.dto.PlayerDTO;
import com.ces3.project.ces3project.model.Player;
import com.ces3.project.ces3project.model.Team;
import com.ces3.project.ces3project.utils.UtilMethods;

import java.util.*;

public class TeamService {

    TeamDAO teamDAO;
    PlayerService playerService;

    private static final int LENGTH_ID_TEAM = 10;

    public TeamService(TeamDAO teamDAO, PlayerService playerService) {
        this.teamDAO = teamDAO;
        this.playerService = playerService;
    }

    public Optional<Team> getTeamById(Integer id){
        return teamDAO.get(id);
    }

    public List<Team> getAllTeams(PaginationDTO paginationDTO) {

        int page = (paginationDTO.getPage() == null || paginationDTO.getPage() < 1) ? 1 : paginationDTO.getPage();
        int size = (paginationDTO.getSize() == null || paginationDTO.getSize() < 1) ? 10 : paginationDTO.getSize();

        List<Team> teams = teamDAO.getAllTeams(page, size);

        if (teams.isEmpty()) {
            throw new NoSuchElementException("There are no teams in the database");
        }

        for (Team team : teams) {
            if (team.getTeamPlayersIds() != null && !team.getTeamPlayersIds().isEmpty()) {
                ArrayList<Player> players = playerService.getPlayersByIds(team.getTeamPlayersIds());
                ArrayList<PlayerDTO> playerBasicInfoList = getPlayerDTOS(players);
                team.setPlayers(playerBasicInfoList);
            } else {
                team.setPlayers(new ArrayList<>());
            }
        }
        return teams;
    }

    public void addPlayerToTeam(Player player, Integer teamId) {
        Optional<Team> teamOptional = teamDAO.get(teamId);
        if (teamOptional.isEmpty()) {
            throw new NoSuchElementException("Team with ID " + teamId + " not found.");
        }
        Team team = teamOptional.get();
        if (team.getTeamPlayers() == null) {
            team.setTeamPlayers(new ArrayList<>());
        }
        if (!validatePlayerNumber(player.getNumber(), teamId)){
            throw new IllegalArgumentException("Player number " + player.getNumber() + " is already in use.");
        }
        team.addPlayer(player.getId());
        teamDAO.update(teamId, team);
    }

    public boolean validatePlayerNumber(Integer playerNumber, Integer teamId) {
        Optional<Team> team = teamDAO.get(teamId);
        if (team.isEmpty() || team.get().getTeamPlayersIds() == null || team.get().getTeamPlayersIds().isEmpty()) {
            return true;
        }
        List<Player> players = playerService.getPlayersByIds(team.get().getTeamPlayersIds());
        for (Player player : players) {
            if (player.getNumber().equals(playerNumber)) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<PlayerDTO> getPlayerDTOS(ArrayList<Player> players) {
        ArrayList<PlayerDTO> playerBasicInfoList = new ArrayList<>();

        for (Player player : players) {
            PlayerDTO playerBasicInfo = new PlayerDTO(
                    player.getName(),
                    player.getLastName(),
                    player.getPosition(),
                    player.getNumber(),
                    player.getActive()
            );
            playerBasicInfoList.add(playerBasicInfo);
        }
        return playerBasicInfoList;
    }

    public void createTeam(Team team) {
        DuplicateStatus status = isTeamDuplicate(team);

        switch (status) {
            case NO_DUPLICATE:
                team.setId(UtilMethods.generateUniqueBigInteger(LENGTH_ID_TEAM).intValue());
                List<Optional<Player>> players = new ArrayList<>();
                if (team.getTeamPlayersIds() != null && !team.getTeamPlayersIds().isEmpty()) {
                    for (Integer playerId : team.getTeamPlayers()) {
                        Optional<Player> player = playerService.getPlayerById(playerId);
                        if (player.isEmpty()) {
                            throw new IllegalArgumentException("Player with ID " + playerId + " not found.");
                        }
                        players.add(player);
                    }
                }
                teamDAO.save(team);
                break;
            case NAME_AND_SPORT_DUPLICATE:
                throw new IllegalArgumentException("A team with the same name and sport already exists.");
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
                .filter(existingTeam -> existingTeam.getName().equalsIgnoreCase(team.getName()) &&
                        existingTeam.getSport().equalsIgnoreCase(team.getSport()))
                .findFirst().map(t -> DuplicateStatus.NAME_AND_SPORT_DUPLICATE)
                .orElse(DuplicateStatus.NO_DUPLICATE);
    }

    public enum DuplicateStatus {
        NAME_AND_SPORT_DUPLICATE,
        NO_DUPLICATE
    }

    public Map<String, Double> getAvgPlayerPerTeam() {
        List<Team> teams = teamDAO.getAll();
        Map<String, Double> avgPlayerCount = new HashMap<>();

        for (Team team : teams) {
            String teamName = team.getName();
            if (team.getTeamPlayersIds() != null && !team.getTeamPlayersIds().isEmpty()) {
                int playerCount = team.getTeamPlayers().size();
                avgPlayerCount.put(teamName, (double) playerCount);
            }
        }
        double totalPlayers = avgPlayerCount.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalTeams = avgPlayerCount.size();

        Map<String, Double> averagePerTeam = new HashMap<>();
        for (Map.Entry<String, Double> entry : avgPlayerCount.entrySet()) {
            averagePerTeam.put(entry.getKey(), totalPlayers / totalTeams);
        }
        return averagePerTeam;
    }

}
