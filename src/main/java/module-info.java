module com.example.perfectnumber {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.quiz to javafx.fxml;
    exports com.example.quiz;
}