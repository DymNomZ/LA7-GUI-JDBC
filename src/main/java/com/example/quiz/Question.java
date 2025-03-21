package com.example.quiz;

public class Question {

    int id;
    String question;
    String[] choices;
    int correct;

    public Question(int id, String question, String... choices) {
        this.id = id;
        this.question = question;
        this.choices = choices;
    }

    @Override
    public String toString(){
        return question;
    }

}
