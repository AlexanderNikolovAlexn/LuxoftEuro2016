package com.samodeika.filtering;

import com.samodeika.entity.Player;

import java.util.*;

/**
 * Created by Alexander Nikolov on 14.6.2016 ?..
 */
public class CollectionFiltering {

    public static List<Player> filterCollectionByDate(List<Player> inputList, Date date) {
        List<Player> resultList = new LinkedList<>(inputList);
        for (Iterator<Player> iterator = resultList.iterator(); iterator.hasNext();) {
            Player player = iterator.next();
            if (player.getBirthDate().compareTo(date) <= 0) {
                // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }

        System.out.println("After filtering results are " + resultList.size());
        return resultList;
    }

    public static void sortCollectionByDate(List<Player> inputList) {
        Collections.sort(inputList, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return (o1.getBirthDate().compareTo(o2.getBirthDate())) * (-1);
            }
        });
    }

}
