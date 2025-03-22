package com.example.quiz;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Congratulations {

    public Label score;
    public Button retakeBtn;
    public Button logoutBtn;

    public void setScore(String score){
        this.score.setText(score);
    }

    public void retake(){
        HelloApplication.MAIN_STAGE.setScene(Scenes.QUIZ_PROPER);
    }

    public void logout(){
        HelloApplication.MAIN_STAGE.setScene(Scenes.LOGIN_SCREEN);
    }

}
