package com.example.quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    static Stage MAIN_STAGE;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("QuizApp - Borgonia");
        stage.setScene(Scenes.LOGIN_SCREEN);
        stage.show();
        MAIN_STAGE = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}