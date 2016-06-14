package com.samodeika.concurentreadingcallable;

import com.samodeika.entity.Player;
import com.samodeika.json.JsonProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class FileReaderCallable {

    private final static int NUM_CPUS = 4;

    private List<File> inputFiles;
    private JsonProcessor jsonProcessor;
    private List<String> resultData;

    public FileReaderCallable(List<File> datafiles, JsonProcessor jsonProcessor) {
        this.inputFiles = datafiles;
        this.jsonProcessor = jsonProcessor;
        this.resultData = new ArrayList<>();
    }

    public List<Player> processFiles() {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_CPUS);
        List<Future> futures = new ArrayList<>();
        List<Player> resultSet = new ArrayList<>();
        for (File file : inputFiles) {
            Future f = executor.submit(new FileProcessorCallable(jsonProcessor, file));
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

        System.out.println("ResultSetCallable count is " + resultSet.size());

        return resultSet;
    }
}
