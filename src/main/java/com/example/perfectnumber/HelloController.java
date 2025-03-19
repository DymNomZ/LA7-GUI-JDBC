package com.example.perfectnumber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    public TextField check_num;
    public TextField thread_num;
    public Label result;
    public Button check_btn;
    public TextField tfNumber;
    public TextField tfThreads;
    public Button ok_btn;
    public ListView lvList;
    public HBox hbProgresses;

    @FXML
    public void onOKButtonClick(ActionEvent actionEvent) {

        try{
            int number = Integer.parseInt(tfNumber.getText());
            int threads = Integer.parseInt(tfThreads.getText());
            List<Thread> threadsList = new ArrayList<>();
            int before = 0;
            for(int i = 0; i < threads; i++){
                ProgressIndicator pi = new ProgressIndicator();
                hbProgresses.getChildren().add(pi);
                threadsList.add(new Thread(new PerfectRunnable(0, number, pi, lvList.getItems())));
            }
            for(Thread t : threadsList) t.start();

        }catch (Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            a.show();
            tfNumber.setText("");
            tfThreads.setText("");
        }

    }
}