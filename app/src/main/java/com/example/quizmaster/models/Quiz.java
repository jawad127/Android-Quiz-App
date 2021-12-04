package com.example.quizmaster.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Quiz implements Serializable {
    public String id;
    @SerializedName("title")
    public String title;
    @SerializedName("imageId")
    public int imageID;
    @SerializedName("questions")
    public Map<String,Question> questions;

    public Quiz() {

    }

    public Quiz(String id, String title, int imageID, Map<String, Question> questions) {
        this.id = id;
        this.title = title;
        this.imageID = imageID;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public Map<String, Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imageID=" + imageID +
                ", questions=" + questions +
                '}';
    }
}
