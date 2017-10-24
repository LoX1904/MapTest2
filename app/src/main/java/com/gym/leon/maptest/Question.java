package com.gym.leon.maptest;
import java.util.ArrayList;

/**
 * Created by Leon on 31.08.2017.
 */

public class Question {
    private String frage;
    private String richtigeAntwort;
    private ArrayList antworten;


    public Question(){
        this.antworten = null;
        this.richtigeAntwort = null;
        this.frage = null;
    }

    public Question(String frage, String richtigeAntwort, ArrayList antworten){
        this.frage = frage;
        this.richtigeAntwort = richtigeAntwort;
        this.antworten = antworten;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public ArrayList getAntworten() {
        return antworten;
    }

    public void setAntworten(ArrayList antworten) {
        this.antworten = antworten;
    }

    public String getRichtigeAntwort() {
        return richtigeAntwort;
    }

    public void setRichtigeAntwort(String richtigeAntwort) {
        this.richtigeAntwort = richtigeAntwort;
    }
    public boolean istFrageRichtigBeantwortet(String antwort){
        return antwort.equals(this.richtigeAntwort);
    }
}
