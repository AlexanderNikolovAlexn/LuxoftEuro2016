package com.samodeika.entity;

import java.util.Date;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class Player {

    private String name;
    private String bio;
    private boolean photo;
    private String specialPlayer;
    private int number;
    private int caps;
    private int goalsScored;
    private String club;
    private String league;
    private Date birthDate;
    private double matchRating1;
    private double matchRating2;
    private double matchRating3;

    public Player() {
    }

    public Player(String name, String bio, boolean photo, String specialPlayer, int number, int caps, int goalsScored, String club, String league, Date birthDate, double matchRating1, double matchRating2, double matchRating3) {
        this.name = name;
        this.bio = bio;
        this.photo = photo;
        this.specialPlayer = specialPlayer;
        this.number = number;
        this.caps = caps;
        this.goalsScored = goalsScored;
        this.club = club;
        this.league = league;
        this.birthDate = birthDate;
        this.matchRating1 = matchRating1;
        this.matchRating2 = matchRating2;
        this.matchRating3 = matchRating3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isPhoto() {
        return photo;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
    }

    public String getSpecialPlayer() {
        return specialPlayer;
    }

    public void setSpecialPlayer(String specialPlayer) {
        this.specialPlayer = specialPlayer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCaps() {
        return caps;
    }

    public void setCaps(int caps) {
        this.caps = caps;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getMatchRating1() {
        return matchRating1;
    }

    public void setMatchRating1(double matchRating1) {
        this.matchRating1 = matchRating1;
    }

    public double getMatchRating2() {
        return matchRating2;
    }

    public void setMatchRating2(double matchRating2) {
        this.matchRating2 = matchRating2;
    }

    public double getMatchRating3() {
        return matchRating3;
    }

    public void setMatchRating3(double matchRating3) {
        this.matchRating3 = matchRating3;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", photo=" + photo +
                ", specialPlayer='" + specialPlayer + '\'' +
                ", number=" + number +
                ", caps=" + caps +
                ", goalsScored=" + goalsScored +
                ", club='" + club + '\'' +
                ", league='" + league + '\'' +
                ", birthDate=" + birthDate +
                ", matchRating1=" + matchRating1 +
                ", matchRating2=" + matchRating2 +
                ", matchRating3=" + matchRating3 +
                '}';
    }

}
