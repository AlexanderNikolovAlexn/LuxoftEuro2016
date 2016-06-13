package com.samodeika.json;

import com.samodeika.entity.Player;

import java.util.List;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public interface JsonProcessor {

    List<Player> processFile(String fileContent);

}
