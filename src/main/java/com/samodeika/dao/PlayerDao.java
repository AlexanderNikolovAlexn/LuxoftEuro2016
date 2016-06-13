package com.samodeika.dao;

import com.samodeika.entity.Player;

import java.util.List;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public interface PlayerDao {

    void truncateTable();
    boolean savePlayer(Player player);
    boolean savePlayers(List<Player> players);

}
