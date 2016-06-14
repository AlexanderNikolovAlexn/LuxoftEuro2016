package com.samodeika.tmp;

import com.samodeika.entity.Player;
import com.samodeika.utils.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class TestAppMain {

    private static Date COMPARE_DATE;

    public static void main(String[] args) {
        List<PlayerShort> tmpData = null;
        try {
            tmpData = randomData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long startTime = System.nanoTime();
        // testTreeMap(tmpData);
        testLinkedList(tmpData);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time duration is " + duration + " miliseconds");
    }

    private static void testLinkedList(List<PlayerShort> inputData) {
        List<PlayerShort> linkedList = new LinkedList<>();
        for (PlayerShort player : inputData) {
            linkedList.add(player);
        }
        System.out.println("Elements count BEFORE is " + linkedList.size());
        System.out.println(linkedList);
        List<PlayerShort> rs = linkedList.stream().
                filter(d -> d.getBirthDate().compareTo(COMPARE_DATE) > 0).
                collect(Collectors.toList());
        Collections.sort(rs, new PlayerComparator());
        System.out.println("Elements count is " + rs.size());
        System.out.println(rs);
        List<PlayerShort> indexesToRemove = new ArrayList<>();
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).getBirthDate().compareTo(COMPARE_DATE) <= 0) {
                indexesToRemove.add(linkedList.get(i));
            }
        }
        indexesToRemove.forEach(linkedList::remove);
        System.out.println(linkedList);
    }

    private static List<PlayerShort> randomData() throws ParseException {
        List<PlayerShort> players = new ArrayList<>();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        COMPARE_DATE = formatter.parse("01-03-2003");


        PlayerShort player1 = new PlayerShort("Alex Nikolov", 11, formatter.parse("01-10-2005"));
        PlayerShort player2 = new PlayerShort("Ivan Mihovski", 3, formatter.parse("11-10-2001"));
        PlayerShort player3 = new PlayerShort("Georgi Gocev", 1, DateUtils.generateRandomDate());

        players.add(player1);
        players.add(player2);
        players.add(player3);

//        for (int i = 0; i < 1000000; i++) {
//            PlayerShort player = new PlayerShort("Player " + i, i, DateUtils.generateRandomDate());
//            players.add(player);
//        }

        return players;
    }
}
