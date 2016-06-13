package com.samodeika.main;

import com.samodeika.dao.PlayerDao;
import com.samodeika.dao.PlayerDaoImpl;
import com.samodeika.entity.Player;
import com.samodeika.json.JsonProcessor;
import com.samodeika.json.JsonProcessorImpl;
import com.samodeika.utils.FileReaderConcurent;
import com.samodeika.utils.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class App {

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
        List<String> files = null;
        if(FileUtils.isValidDirectory(folder)) {
            System.out.println("Reading list of files!");
            files = FileUtils.listFilesFromFolder(folder);
        }

        //define resultSet
        List<Player> players = new ArrayList<>();

        // define database dao
        PlayerDao playerDao = new PlayerDaoImpl();
        playerDao.truncateTable();

        // define json parser
        JsonProcessor jsonProcessor = new JsonProcessorImpl();

        System.out.println("Processing json files!");
        long readingFilesStartTime = System.nanoTime();
        //players = singleThreadedExecution(files, jsonProcessor);
        players = multiThreadingExecution(files, jsonProcessor);

        

        //filter collection

        long readingFilesEndTime = System.nanoTime();
        duration = (readingFilesEndTime - startTime) / 1000000;
        System.out.println("Reading time is: " + duration + " miliseconds");

        System.out.println("Saving data into database!");
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

    private static List<Player> multiThreadingExecution(List<String> files, JsonProcessor jsonProcessor) {
        List<Player> players = new ArrayList<>();
        FileReaderConcurent fileReaderConcurent = new FileReaderConcurent(files, jsonProcessor);
        players = fileReaderConcurent.processFiles();
        return players;
    }

    private static List<Player> singleThreadedExecution(List<String> files, JsonProcessor jsonProcessor) {
        List<Player> players = new ArrayList<>();
        for (String filePath : files) {
            File file = new File(filePath);
            List<Player> newPlayers = jsonProcessor.processFile(FileUtils.readFile(file));
            players.addAll(newPlayers);
        }
        return players;
    }


}
