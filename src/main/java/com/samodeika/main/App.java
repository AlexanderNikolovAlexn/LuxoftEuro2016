package com.samodeika.main;

import com.samodeika.dao.PlayerDao;
import com.samodeika.dao.PlayerDaoImpl;
import com.samodeika.entity.Player;
import com.samodeika.json.JsonProcessor;
import com.samodeika.json.JsonProcessorImpl;
import com.samodeika.utils.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Run main!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Directory: ");
        String directory;
        directory = scanner.nextLine();
        if(directory == null || directory.isEmpty()) {
            directory = "C:\\dev\\jsons";
        }
        final File folder = new File(directory);
        List<String> files = null;
        if(FileUtils.isValidDirectory(folder)) {
            files = FileUtils.listFilesFromFolder(folder);
        }

        // define database dao
        PlayerDao playerDao = new PlayerDaoImpl();
        playerDao.truncateTable();

        // define json parser
        JsonProcessor jsonProcessor = new JsonProcessorImpl();
        for (String filePath : files) {
            File file = new File(filePath);
            List<Player> players = jsonProcessor.processFile(FileUtils.readFile(file));

            //save player
            //playerDao.savePlayer(players.get(0));

            //save all players
            playerDao.savePlayers(players);
        }
    }



}
