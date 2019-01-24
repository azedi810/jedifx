package design.lecture.sian.pk02.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import design.lecture.sian.pk02.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MenuController implements Initializable {
	

    @FXML
    private AnchorPane content;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
    @FXML
    void handle_logou(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../views/Login.fxml"));
			content.getChildren().removeAll();
			content.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
