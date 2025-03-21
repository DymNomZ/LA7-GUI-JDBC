package com.example.quiz;

import javafx.scene.control.TextField;

import java.sql.*;
import java.util.Objects;

public class LoginScreen {

    public TextField nameTF;
    public TextField passwordTF;

    public void createAccount(){

        HelloApplication.MAIN_STAGE.setScene(Scenes.CREATE_USER);
    }

    public void login(){

        String enteredName = nameTF.getText();
        String enteredPass = passwordTF.getText();
        boolean match = false;
        String type = "";

        String URL = "jdbc:mysql://localhost:3306/csit228f2";
        String USER = "root";
        String PASSWORD = "";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()){

            String query = "SELECT * FROM users"; // to be improved lmao

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                if(Objects.equals(resultSet.getString("name"), enteredName)){
                    if(Objects.equals(resultSet.getString("password"), enteredPass))
                    match = true;
                    type = resultSet.getString("type");
                    break;
                }
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        if(!match){
            System.out.println("No such user in database!");
            return;
        }

        if(Objects.equals(type, "Student")) HelloApplication.MAIN_STAGE.setScene(Scenes.QUIZ_PROPER);
        else if(Objects.equals(type, "Teacher")) HelloApplication.MAIN_STAGE.setScene(Scenes.QUIZ_PROPER);
    }
}
