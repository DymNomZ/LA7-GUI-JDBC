package com.example.quiz;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddQuestion {

    public TextField questionTF;
    public TextField aTF;
    public TextField bTF;
    public TextField cTF;
    public TextField dTF;
    public Button saveBtn;
    public Button cancelBtn;
    public Label error;

    public QuestionAddedListener listener;

    public void clear(){
        questionTF.clear();
        aTF.clear();
        bTF.clear();
        cTF.clear();
        dTF.clear();
        error.setText("");
    }

    public void add(){

        String URL = "jdbc:mysql://localhost:3306/csit228f2";
        String USER = "root";
        String PASSWORD = "";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO questions (" +
                            "question," +
                            "choice_a," +
                            "choice_b," +
                            "choice_c," +
                            "choice_d" +
                            ") VALUES (?, ?, ?, ?, ?)"
            )){

            if(questionTF.getText().isEmpty() || aTF.getText().isEmpty() || bTF.getText().isEmpty() ||
            cTF.getText().isEmpty() || dTF.getText().isEmpty()){
                error.setText("Must input all fields!");
                return;
            }

            statement.setString(1, questionTF.getText());
            statement.setString(2, aTF.getText());
            statement.setString(3, bTF.getText());
            statement.setString(4, cTF.getText());
            statement.setString(5, dTF.getText());

            int rowsAffected = 0, j = 0;

            rowsAffected += statement.executeUpdate();

            if(rowsAffected >= 1) {
                System.out.println("Successfully added " + rowsAffected + " rows");
                if (listener != null) {
                    listener.onQuestionAdded();
                    cancel();
                }
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void cancel(){
        clear();
        HelloApplication.MAIN_STAGE.setScene(Scenes.CRUD_QUESTIONS);
    }

}
