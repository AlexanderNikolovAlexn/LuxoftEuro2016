package com.samodeika.tmp;

import java.util.Comparator;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class PlayerComparator implements Comparator<PlayerShort> {

    @Override
    public int compare(PlayerShort o1, PlayerShort o2) {
        return o1.getBirthDate().compareTo(o2.getBirthDate());
    }
}
