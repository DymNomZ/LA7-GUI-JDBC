package com.example.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTables {

    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/csit228f2";
        String USER = "root";
        String PASSWORD = "";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE users (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT," +
                            "name VARCHAR(50)," +
                            "email VARCHAR(50)," +
                            "password VARCHAR(50)," +
                            "type VARCHAR(50)" +
                            ")"
            )){

            int rowsAffected = 0, j = 0;

            rowsAffected += statement.executeUpdate();

            if(rowsAffected >= 1) {
                System.out.println("Successfully added " + rowsAffected + " rows");
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE questions (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT," +
                            "question VARCHAR(100)," +
                            "choice_a VARCHAR(100)," +
                            "choice_b VARCHAR(100)," +
                            "choice_c VARCHAR(100)," +
                            "choice_d VARCHAR(100)" +
                            ")"
            )){

            int rowsAffected = 0, j = 0;

            rowsAffected += statement.executeUpdate();

            if(rowsAffected >= 1) {
                System.out.println("Successfully added " + rowsAffected + " rows");
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
