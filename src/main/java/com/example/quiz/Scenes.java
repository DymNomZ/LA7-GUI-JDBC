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

    static {
        try {
            QUIZ_PROPER = new Scene(quizfxml.load(), 600, 500);
            LOGIN_SCREEN = new Scene(loginfxml.load(), 600, 500);
            CREATE_USER = new Scene(createuserfxml.load(), 600, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
