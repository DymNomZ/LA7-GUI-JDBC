package com.example.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertQuizItems {

    static String[] questions = {
            "What is 2 + 2?", "What is an apple?", "What is the square root of 25?",
            "What is a chicken?", "What color is the sky?"
    };

    static String choices[] = {
            "4", "5", "6", "3",
            "A fruit", "A vegetable", "A weapon", "A drug",
            "5", "7", "6", "3",
            "A bird", "A dog", "A cat", "A frog",
            "Blue", "Red", "Green", "Brown"
    };

    public static void main(String[] args) {
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

            int rowsAffected = 0, j = 0;

            for(int i = 0; i < questions.length; i++){
                statement.setString(1, questions[i]);
                statement.setString(2, choices[j]);
                statement.setString(3, choices[j+1]);
                statement.setString(4, choices[j+2]);
                statement.setString(5, choices[j+3]);
                j += 4;
                rowsAffected += statement.executeUpdate();
            }

            if(rowsAffected >= 1) {
                System.out.println("Successfully added " + rowsAffected + " rows");
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
