package com.example.quiz;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.sql.*;
import java.util.ArrayList;

public class CrudQuestions implements QuestionAddedListener, QuestionEditListener {

    public Button addBtn;
    public Button editBtn;
    public Button deleteBtn;
    public Button logoutBtn;
    public ListView<Question> questionsLV;
    ObservableList<Question> questions;
    Question selectedQuestion = null;

    public void initialize(){

        String URL = "jdbc:mysql://localhost:3306/csit228f2";
        String USER = "root";
        String PASSWORD = "";

        questions = FXCollections.observableArrayList();

        questionsLV.setItems(questions);

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

                System.out.println("FROM CRUD | " + question + " " + choice_a + " " + choice_b + " " + choice_c + " " + choice_d);
                questions.add(new Question(id, question, choice_a, choice_b, choice_c, choice_d));
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void logout(){
        HelloApplication.MAIN_STAGE.setScene(Scenes.LOGIN_SCREEN);
    }

    public void onSelect() {
        selectedQuestion = questionsLV.getSelectionModel().getSelectedItem();
        System.out.println(selectedQuestion.question);
    }

    @Override
    public void onQuestionAdded() { // Implement listener method
        initialize();
    }

    public void add(){
        HelloApplication.MAIN_STAGE.setScene(Scenes.ADD_QUESTION);
        AddQuestion addQuestionController = (AddQuestion) Scenes.addquesfxml.getController();
        addQuestionController.listener = this;
    }

    @Override
    public void onQuestionEdited() {
        initialize();
    }

    public void edit(){
        if(selectedQuestion != null){
            HelloApplication.MAIN_STAGE.setScene(Scenes.EDIT_QUESTION);
            EditQuestion editQuestionController = (EditQuestion) Scenes.editquesfxml.getController();
            editQuestionController.listener = this;
            editQuestionController.setQuestion(selectedQuestion);
        }
    }

    public void delete(){

        String URL = "jdbc:mysql://localhost:3306/csit228f2";
        String USER = "root";
        String PASSWORD = "";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM questions WHERE id = ?"
            )){

            statement.setInt(1, selectedQuestion.id);

            int rowsAffected = 0, j = 0;

            rowsAffected += statement.executeUpdate();

            if(rowsAffected >= 1) {
                System.out.println("Successfully deleted " + rowsAffected + " rows");
                initialize();
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

}
