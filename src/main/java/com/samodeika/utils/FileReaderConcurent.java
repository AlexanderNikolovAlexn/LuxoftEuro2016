package com.samodeika.utils;

import com.samodeika.entity.Player;
import com.samodeika.json.JsonProcessor;
import com.samodeika.json.JsonProcessorImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class FileReaderConcurent {

    private final static int NUM_CPUS = 4;

    private List<String> inputFiles;
    private JsonProcessor jsonProcessor;
    private List<String> resultData;

    public FileReaderConcurent(List<String> datafiles, JsonProcessor jsonProcessor) {
        this.inputFiles = datafiles;
        this.jsonProcessor = jsonProcessor;
        this.resultData = Collections.synchronizedList(new ArrayList<>());
    }

    public List<Player> processFiles() {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_CPUS);
        List<Future> futures = new ArrayList<>();
        List<Player> resultSet = new ArrayList<>();
        for (String file : inputFiles) {
            Future f = executor.submit(new FileProcessor(jsonProcessor, new File(file)));
            futures.add(f);
        }

        for (Future future : futures) {
            try {
                List<Player> players = (List<Player>) future.get();
                resultSet.addAll(players);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        return resultSet;
    }
}
