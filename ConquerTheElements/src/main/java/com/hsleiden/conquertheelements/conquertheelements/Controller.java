package com.hsleiden.conquertheelements.conquertheelements;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField textField;
    String username;

    public void submit(ActionEvent event) throws IOException {
        username = textField.getText();
        System.out.println(username);

    }

}
