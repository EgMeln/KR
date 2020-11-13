package com.bsu.by;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Horse {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private String id;
    private String club;
    private String name;
    private Date date;
    private String competition;
    private int victories;

    public Horse(String id, String club, String name, Date date, String competition, int victories) {
        this.id = id;
        this.club = club;
        this.name = name;
        this.date = date;
        this.competition = competition;
        this.victories = victories;
    }

    public Horse(String args[]) throws IllegalArgumentException, ParseException {
        if (args.length != 6) {
            throw new IllegalArgumentException("wrong DATA used");
        }
        this.id = args[0];
        this.club = args[1];
        this.name = args[2];
        this.date = dateFormat.parse(args[3]);
        this.competition = args[4];
        this.victories = Integer.parseInt(args[5]);
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return victories == horse.victories &&
                Objects.equals(id, horse.id) &&
                Objects.equals(club, horse.club) &&
                Objects.equals(name, horse.name) &&
                Objects.equals(date, horse.date) &&
                Objects.equals(competition, horse.competition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, club, name, date, competition, victories);
    }

    @Override
    public String toString() {
        return "Horse{" +
                "id='" + id + '\'' +
                ", club='" + club + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", competition='" + competition + '\'' +
                ", victories=" + victories +
                '}';
    }
}
