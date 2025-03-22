package com.example.quiz;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelloController {

    public Button btnA;
    public Button btnB;
    public Button btnC;
    public Button btnD;
    public Button btnBack;
    public Button btnNext;
    public Label lbScore;
    public Label lbQuestion;
    public List<Question> questions;
    public Button[] btnChoices;
    public ArrayList<Boolean> answered;
    private int page = 1;
    private int score = 0;

    public void initialize(){

        questions = new ArrayList<>();
        answered = new ArrayList<>();

        String URL = "jdbc:mysql://localhost:3306/csit228f2";
        String USER = "root";
        String PASSWORD = "";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()){

            String query = "SELECT * FROM questions";

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String question = resultSet.getString("question");
                String choice_a = resultSet.getString("choice_a");
                String choice_b = resultSet.getString("choice_b");
                String choice_c = resultSet.getString("choice_c");
                String choice_d = resultSet.getString("choice_d");

                System.out.println("FROM QUIZ | " +  question + " " + choice_a + " " + choice_b + " " + choice_c + " " + choice_d);
                questions.add(new Question(id, question, choice_a, choice_b, choice_c, choice_d));
                answered.add(false);
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        lbScore.setText("Score: " + score + "/" + questions.size());
        lbQuestion.setText("Question 1: " + questions.get(0).question);
        List<Button> btnClones = new ArrayList<>();
        btnClones.add(btnA);
        btnClones.add(btnB);
        btnClones.add(btnC);
        btnClones.add(btnD);
        btnChoices = new Button[]{btnA, btnB, btnC, btnD};
        for(int i = 0; i < questions.get(0).choices.length; i++){
            int rand = (int) (Math.random() * btnClones.size());
            btnClones.remove(rand).setText(questions.get(0).choices[i]);
        }
    }

    public void onNavigationClick(ActionEvent actionEvent){

        if(actionEvent.getSource() == btnBack){
            if(page <= 1){
                HelloApplication.MAIN_STAGE.setScene(Scenes.LOGIN_SCREEN);
                score = 0;
                lbScore.setText("Score: " + score + "/" + questions.size());
                for(Button b : btnChoices){
                    b.setBackground(Background.fill(Paint.valueOf("WHITE")));
                }
                return;
            }
            page--;
        }else {
            if(page >= questions.size()){
                Congratulations congratulations = (Congratulations) Scenes.congratsfxml.getController();
                congratulations.setScore(String.format("%d", score));
                HelloApplication.MAIN_STAGE.setScene(Scenes.CONGRATULATIONS);
                page = 1;
                loadItem();
                score = 0;
                lbScore.setText("Score: " + score + "/" + questions.size());
                return;
            }
            page++;
        }

        loadItem();

    }

    public void loadItem(){
        lbQuestion.setText("Question " + page + ": " + questions.get(page-1).question);
        List<Button> btnClones = new ArrayList<>();
        btnClones.add(btnA);
        btnClones.add(btnB);
        btnClones.add(btnC);
        btnClones.add(btnD);
        for(int i = 0; i < questions.get(page-1).choices.length; i++){
            int rand = (int) (Math.random() * btnClones.size());
            Button b = btnClones.remove(rand);
            b.setText(questions.get(page-1).choices[i]);
            b.setBackground(Background.fill(Paint.valueOf("WHITE")));
        }
    }

    public void onAnswerClick(ActionEvent actionEvent) {
        String ans = ((Button) actionEvent.getSource()).getText();

        if(!answered.get(page - 1)){
            if(Objects.equals(ans, questions.get(page - 1).choices[0])){
                score++;
                lbScore.setText("Score: " + score + "/" + questions.size());
                answered.set(page-1, true);
            }
            for(Button b : btnChoices){
                String item = b.getText();
                if(Objects.equals(item, questions.get(page - 1).choices[0])){
                    b.setBackground(Background.fill(Paint.valueOf("GREEN")));
                }else{
                    b.setBackground(Background.fill(Paint.valueOf("RED")));
                }
            }
        }
    }
}