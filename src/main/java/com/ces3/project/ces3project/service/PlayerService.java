package com.ces3.project.ces3project.service;

import com.ces3.project.ces3project.dao.PlayerDAO;
import com.ces3.project.ces3project.model.Player;

import java.util.List;
import java.util.Optional;

public class PlayerService {
    private final PlayerDAO playerDAO;

    public PlayerService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }
    public Optional<Player> getPlayerById(Integer id){
        return playerDAO.get(id);
    }

    public List<Player> getAllPlayers(){
        return playerDAO.getAll();
    }

    public void createPlayer(Player player) {
        playerDAO.save(player);
    }
    public void updatePlayer(Integer id, Player player) {
        playerDAO.update(id, player);
    }
    public void deletePlayer(Player player) {
        playerDAO.delete(player);
    }
}
