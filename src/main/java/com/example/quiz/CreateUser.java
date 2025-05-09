package com.example.quiz;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateUser {

    public TextField nameTF;
    public TextField emailTF;
    public TextField passwordTF;
    public RadioButton studentRB;
    public RadioButton teacherRB;
    public String choice = "";
    public Label error;

    public void clear(){
        nameTF.clear();
        emailTF.clear();
        passwordTF.clear();
        studentRB.setSelected(false);
        teacherRB.setSelected(false);
        choice = "";
        error.setText("");
    }

    public void back(){
        clear();
        HelloApplication.MAIN_STAGE.setScene(Scenes.LOGIN_SCREEN);
    }

    public void setChoice(ActionEvent event){
        if(event.getSource() == studentRB) choice = studentRB.getText();
        else if(event.getSource() == teacherRB) choice = teacherRB.getText();
    }

    public void addUser(ActionEvent event){

        String URL = "jdbc:mysql://localhost:3306/csit228f2";
        String USER = "root";
        String PASSWORD = "";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (name, email, password, type) VALUES (?, ?, ?, ?)"
            )){

            statement.setString(1, nameTF.getText());
            statement.setString(2, emailTF.getText());
            statement.setString(3, passwordTF.getText());

            if(nameTF.getText().isEmpty() || passwordTF.getText().isEmpty() || emailTF.getText().isEmpty()){
                error.setText("Must input all fields!");
                return;
            }
            else if(choice.isEmpty()) {
                error.setText("Must select type!");
                return;
            }
            else statement.setString(4, choice);

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected >= 1) {
                System.out.println("Successfully added " + rowsAffected + " rows");
                clear();
                HelloApplication.MAIN_STAGE.setScene(Scenes.LOGIN_SCREEN);
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }
}
