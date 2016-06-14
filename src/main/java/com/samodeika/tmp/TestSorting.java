package com.samodeika.tmp;

import com.samodeika.entity.Player;
import com.samodeika.filtering.CollectionFilteringAndSorting;
import com.samodeika.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Nikolov on 14.6.2016 ?..
 */
public class TestSorting {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            Player player = new Player();
            player.setName("Name " + i);
            player.setBirthDate(DateUtils.getDateFromString("01-01-199" + i, "dd-MM-yyyy"));
            players.add(player);
        }
        System.out.println("Before sorting!");
        printArray(players);

        System.out.println("After sorting!");
        CollectionFilteringAndSorting.customMergeSort(players);
        printArray(players);


    }

    private static void printArray(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i));
        }
    }

}
