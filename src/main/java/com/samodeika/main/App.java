package com.samodeika.main;

import com.samodeika.concurentreadingrunable.FileReaderRunable;
import com.samodeika.dao.PlayerDao;
import com.samodeika.dao.PlayerDaoImpl;
import com.samodeika.entity.Player;
import com.samodeika.filtering.CollectionFilteringAndSorting;
import com.samodeika.json.JsonProcessor;
import com.samodeika.json.JsonProcessorImpl;
import com.samodeika.concurentreadingcallable.FileReaderCallable;
import com.samodeika.utils.DateUtils;
import com.samodeika.utils.FileUtils;

import java.io.*;
import java.util.*;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class App {

    private static final String JSON_EXTENSION = "json";
    private static final Date FILTER_DATE = DateUtils.getDateFromString("01-01-1992", "dd-MM-yyyy");

    public static void main(String[] args) {
        System.out.println("Run main!");
        long duration;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Directory: ");
        String directory;
        directory = scanner.nextLine();
        if(directory == null || directory.isEmpty()) {
            directory = "C:\\dev\\jsons";
        }
        long startTime = System.nanoTime();
        File folder = new File(directory);
        List<File> files = null;
        if(FileUtils.isValidDirectory(folder)) {
            files = FileUtils.listFilesFromFolder(folder);
            files = FileUtils.returnValidFiles(files, JSON_EXTENSION);
            if(files == null || files.isEmpty() || files.size() <= 0) {
                System.out.println("No valid files in specified directory " + directory);
                return;
            }

            //define resultSet
            List<Player> players = new ArrayList<>();

            // define database dao
            PlayerDao playerDao = new PlayerDaoImpl();
            playerDao.truncateTable();

            // define json parser
            JsonProcessor jsonProcessor = new JsonProcessorImpl();

            System.out.println("1-2)Reading and processing json files!");
            long readingFilesStartTime = System.nanoTime();
            //players = singleThreadedExecution(files, jsonProcessor);
            //players = multiThreadingRunable(files, jsonProcessor);
            players = multiThreadingCallable(files, jsonProcessor);

            long readingFilesEndTime = System.nanoTime();
            duration = (readingFilesEndTime - startTime) / 1000000;
            System.out.println("Reading time is: " + duration + " miliseconds");

            System.out.println("3)Applying filtiring and sorting!");
            //filter collection
            players = CollectionFilteringAndSorting.filterCollectionByDate(players, FILTER_DATE);
            CollectionFilteringAndSorting.sortCollectionByDate(players);
            //players = CollectionFilteringAndSorting.customFilterCollectionByDate(players, FILTER_DATE);
            //CollectionFilteringAndSorting.customMergeSort(players);

            long filtiringEndTime = System.nanoTime();
            duration = (filtiringEndTime - readingFilesEndTime) / 1000000;
            System.out.println("Filtering and sorting time is " + duration + " miliseconds");

            System.out.println("4)Saving data into database!");
            //save player
            //playerDao.savePlayer(players.get(0));

            //save all players
            playerDao.savePlayers(players);
            long dataPersistingEndTime = System.nanoTime();
            duration = (dataPersistingEndTime - readingFilesEndTime) / 1000000;
            System.out.println("Persisting time is: " + duration + " miliseconds");

            long endTime = System.nanoTime();
            duration = (endTime - startTime) / 1000000;
            System.out.println("Total execution time is: " + duration + " miliseconds");
        }
        else {
            System.out.println("Not a valid directory!");
        }
    }

    private static List<Player> multiThreadingRunable(List<File> files, JsonProcessor jsonProcessor) {
        List<Player> players = new ArrayList<>();
        FileReaderRunable fileReaderRunable = new FileReaderRunable(files, jsonProcessor);
        players = fileReaderRunable.processFiles();
        return players;
    }

    private static List<Player> multiThreadingCallable(List<File> files, JsonProcessor jsonProcessor) {
        List<Player> players = new ArrayList<>();
        FileReaderCallable fileReaderCallable = new FileReaderCallable(files, jsonProcessor);
        players = fileReaderCallable.processFiles();
        return players;
    }

    private static List<Player> singleThreadedExecution(List<File> files, JsonProcessor jsonProcessor) {
        List<Player> players = new ArrayList<>();
        for (File file : files) {
            List<Player> newPlayers = jsonProcessor.processFile(FileUtils.readFile(file));
            players.addAll(newPlayers);
        }
        return players;
    }


}
