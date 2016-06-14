package com.samodeika.concurentreadingcallable;

import com.samodeika.entity.Player;
import com.samodeika.json.JsonProcessor;
import com.samodeika.utils.FileUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class FileProcessorCallable implements Callable {

    private final JsonProcessor jsonProcessor;
    private final File file;

    public FileProcessorCallable(JsonProcessor jsonProcessor, File file) {
        this.jsonProcessor = jsonProcessor;
        this.file = file;
    }

    @Override
    public List<Player> call() throws Exception {
        return jsonProcessor.processFile(FileUtils.readFile(file));
    }
}
