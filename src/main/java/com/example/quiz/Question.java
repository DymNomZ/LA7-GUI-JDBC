package com.example.quiz;

public class Question {

    String question;
    String[] choices;
    int correct;

    public Question(String question, String... choices) {
        this.question = question;
        this.choices = choices;
    }

}
