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

public class LoginController implements Initializable {
	
	
	@FXML 
	private AnchorPane parent;
	
	@FXML 
	private HBox top;
	
	@FXML 
	private Pane content;
	
	private double xOffset =0;
	private double yOffset =0;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		makeStageDragable();
	}
	
	private void makeStageDragable() {
		System.out.println("makeStageDragable ############# ");
		parent.setOnMousePressed((event) -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		
		parent.setOnMouseDragged((event)  -> {
			Login.stage.setX(event.getScreenX() - xOffset);
			Login.stage.setY(event.getScreenY() - yOffset);
			Login.stage.setOpacity(0.8f);
		});
		
		parent.setOnDragDone((event)  -> {
			Login.stage.setOpacity(1.0f);
		});
		
		parent.setOnMouseReleased((event)  -> {
			Login.stage.setOpacity(1.0f);
		});
	}
	
	
	@FXML
	private void minisize_stage(MouseEvent event) {
		System.out.println("minisize_stage ############# ");
		Login.stage.setIconified(true);
	}
	
	
	@FXML
	private void close_stage(MouseEvent event) {
		System.out.println("close_stage ############# ");
		System.exit(0);
	}

	@FXML
	private void go_home(ActionEvent event) {
		content.getChildren().removeAll();
		content.getChildren().setAll();
	}
	
	@FXML
	private void handle_login(ActionEvent event) {
		System.out.println("handle_login ############# ");
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../views/Menu.fxml"));
			content.getChildren().removeAll();
			content.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@FXML
	private void handle_graphic(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../views/Graphic.fxml"));
			content.getChildren().removeAll();
			content.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleWebview(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../views/Webview.fxml"));
			content.getChildren().removeAll();
			content.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void handleTableView(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../views/TableView.fxml"));
			content.getChildren().removeAll();
			content.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
