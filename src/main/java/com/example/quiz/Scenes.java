package com.example.quiz;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class Scenes {

    static FXMLLoader loginfxml = new FXMLLoader(Scenes.class.getResource("login_screen.fxml"));
    static Scene LOGIN_SCREEN;

    static FXMLLoader createuserfxml = new FXMLLoader(Scenes.class.getResource("create_user.fxml"));
    static Scene CREATE_USER;

    static FXMLLoader quizfxml = new FXMLLoader(Scenes.class.getResource("quiz-view.fxml"));
    static Scene QUIZ_PROPER;

    static FXMLLoader crudfxml = new FXMLLoader(Scenes.class.getResource("crud_questions.fxml"));
    static Scene CRUD_QUESTIONS;

    static FXMLLoader addquesfxml = new FXMLLoader(Scenes.class.getResource("add_question.fxml"));
    static Scene ADD_QUESTION;

    static FXMLLoader editquesfxml = new FXMLLoader(Scenes.class.getResource("edit_question.fxml"));
    static Scene EDIT_QUESTION;

    static {
        try {
            QUIZ_PROPER = new Scene(quizfxml.load(), 600, 500);
            LOGIN_SCREEN = new Scene(loginfxml.load(), 600, 500);
            CREATE_USER = new Scene(createuserfxml.load(), 600, 500);
            CRUD_QUESTIONS = new Scene(crudfxml.load(), 600, 500);
            ADD_QUESTION = new Scene(addquesfxml.load(), 600 ,500);
            EDIT_QUESTION = new Scene(editquesfxml.load(), 600, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
