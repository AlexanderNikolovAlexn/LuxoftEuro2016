package com.samodeika.concurentreadingrunable;

import com.samodeika.entity.Player;
import com.samodeika.json.JsonProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Alexander Nikolov on 14.6.2016 ?..
 */
public class FileReaderRunable {

    private final static int NUM_CPUS = 4;

    private List<String> inputFiles;
    private JsonProcessor jsonProcessor;
    private List<String> resultData;

    public FileReaderRunable(List<String> datafiles, JsonProcessor jsonProcessor) {
        this.inputFiles = datafiles;
        this.jsonProcessor = jsonProcessor;
        this.resultData = new ArrayList<>();
    }

    public List<Player> processFiles() {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_CPUS);
        List<Player> resultSet = new ArrayList<>();
        List<FileProcessorRunable> tasks = new ArrayList<>();
        for (String file : inputFiles) {
            FileProcessorRunable fileProcessor = new FileProcessorRunable(jsonProcessor, new File(file));
            tasks.add(fileProcessor);
            executor.submit(fileProcessor);
        }

        executor.shutdown();
        try {
            if(executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
                for (FileProcessorRunable fileProcessorRunable : tasks) {
                    resultSet.addAll(fileProcessorRunable.getResultSet());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ResultSetRunable count is " + resultSet.size());

        return resultSet;
    }
}
