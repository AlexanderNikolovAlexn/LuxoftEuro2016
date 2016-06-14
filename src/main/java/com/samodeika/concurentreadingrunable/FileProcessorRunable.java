package com.samodeika.concurentreadingrunable;

import com.samodeika.entity.Player;
import com.samodeika.json.JsonProcessor;
import com.samodeika.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Nikolov on 14.6.2016 ?..
 */
public class FileProcessorRunable implements Runnable {

    private final JsonProcessor jsonProcessor;
    private final File file;
    private List<Player> resultSet;

    public FileProcessorRunable(JsonProcessor jsonProcessor, File file) {
        this.jsonProcessor = jsonProcessor;
        this.file = file;
        this.resultSet = new ArrayList<>();
    }

    public List<Player> getResultSet() {
        return resultSet;
    }

    @Override
    public void run() {
        this.resultSet = jsonProcessor.processFile(FileUtils.readFile(this.file));
    }
}
