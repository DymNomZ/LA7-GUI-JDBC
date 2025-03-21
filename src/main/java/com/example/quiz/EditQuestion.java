package com.example.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditQuestion {

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

//            statement.setString(1, questionTF.getText());
//            statement.setString(2, aTF.getText());
//            statement.setString(3, bTF.getText());
//            statement.setString(4, cTF.getText());
//            statement.setString(5, dTF.getText());
//            statement.setInt(6, CrudQuestions.selectedQuestion.id);

            int rowsAffected = 0, j = 0;

            rowsAffected += statement.executeUpdate();

            if(rowsAffected >= 1) {
                System.out.println("Successfully added " + rowsAffected + " rows");
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void cancel(){
        HelloApplication.MAIN_STAGE.setScene(Scenes.CRUD_QUESTIONS);
    }

}
