package com.example.quiz;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditQuestion {

    public TextField questionTF;
    public TextField aTF;
    public TextField bTF;
    public TextField cTF;
    public TextField dTF;
    public Button saveBtn;
    public Button cancelBtn;

    public QuestionAddedListener listener;
    public Question question;

    public void setQuestion(Question question) {
        if (question != null) {
            this.question = question;
            questionTF.setText(question.question);
            aTF.setText(question.choices[0]);
            bTF.setText(question.choices[1]);
            cTF.setText(question.choices[2]);
            dTF.setText(question.choices[3]);
        }
    }

    public void save(){

        String URL = "jdbc:mysql://localhost:3306/csit228f2";
        String USER = "root";
        String PASSWORD = "";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE questions SET " +
                            "question = ?," +
                            "choice_a = ?," +
                            "choice_b = ?," +
                            "choice_c = ?," +
                            "choice_d = ? " +
                            "WHERE id = ?"
            )){

            statement.setString(1, questionTF.getText());
            statement.setString(2, aTF.getText());
            statement.setString(3, bTF.getText());
            statement.setString(4, cTF.getText());
            statement.setString(5, dTF.getText());
            statement.setInt(6, question.id);

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
        HelloApplication.MAIN_STAGE.setScene(Scenes.CRUD_QUESTIONS);
    }

}
