package com.ces3.project.ces3project.dao;

import com.ces3.project.ces3project.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PlayerDAO implements Dao<Player> {

    private List<Player> players = new ArrayList<>();

    @Override
    public Optional<Player> get(Integer id) {
        return players.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Player> getAll() {
        return new ArrayList<>(players);
    }

    @Override
    public void save(Player player) {
        players.add(player);
    }

    @Override
    public void update(Integer id, Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId().equals(id)) {
                players.set(i, player);
                return;
            }
        }
        throw new NoSuchElementException("Player with ID " + id + " not found.");
    }

    @Override
    public void delete(Player player) {
        players.removeIf(p -> p.getId().equals(player.getId()));
    }
}
