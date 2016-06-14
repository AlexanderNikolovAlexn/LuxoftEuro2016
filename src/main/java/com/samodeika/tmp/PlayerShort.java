package com.samodeika.tmp;

import com.samodeika.utils.DateUtils;

import java.util.Date;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class PlayerShort {

    private String name;
    private int teamNumber;
    private Date birthDate;

    public PlayerShort() {
    }

    public PlayerShort(String name, int teamNumber, Date birthDate) {
        this.name = name;
        this.teamNumber = teamNumber;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "PlayerShort{" +
                "name='" + name + '\'' +
                ", teamNumber=" + teamNumber +
                ", birthDate=" + DateUtils.printDate(birthDate) +
                '}';
    }

}
