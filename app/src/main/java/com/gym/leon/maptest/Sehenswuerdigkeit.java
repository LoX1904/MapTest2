package com.gym.leon.maptest;

/**
 * Created by Leon on 31.08.2017.
 */

public class Sehenswuerdigkeit {

    private String name;
    private int id;
    private double longi;
    private double lat;
    private Question question;

    public Sehenswuerdigkeit() {
        name = null;
        longi = 0;
        lat = 0;
        question = null;
        id = 0;
    }

    public Sehenswuerdigkeit(int id, String name, double longi, double lat){
        this.name = name;
        this.longi= longi;
        this.lat = lat;
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
