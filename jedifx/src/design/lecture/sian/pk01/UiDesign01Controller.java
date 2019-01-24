package design.lecture.sian.pk01;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class UiDesign01Controller implements Initializable {
	
	@FXML
	private VBox vbox;
	
	@FXML
	private Parent fxml;
	
	@FXML
	private AnchorPane content_main; 
	
	@FXML
	private AnchorPane content_login; 
	

	@FXML
	private void loginProc(ActionEvent event)  {
		System.out.println("##################  ·Î±×ÀÎ ");
		try {
			Parent main = FXMLLoader.load(getClass().getResource("Main.fxml"));
			System.out.println("##################  content_login  ::::::::::  " + content_login);
			System.out.println("##################  main  ::::::::::  " + main);
			content_login.getChildren().removeAll();
			content_login.getChildren().setAll(main);
			if(null != content_login) {
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("##################  initialize");

		TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
		t.setToX(5);
		t.play();
		t.setOnFinished((e) ->{
			try {
				fxml = FXMLLoader.load(getClass().getResource("UiDesign01Signin.fxml"));
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
				
			} catch (IOException ex) {
				 Logger.getLogger(UiDesign01Controller.class.getName()).log(Level.SEVERE, null, ex);		
			}
		});
	}
	
	@FXML
	private void openSignup(ActionEvent event) {
		System.out.println("##################  openSignup");

		TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
		//t.setToX(vbox.getLayoutX()*20);
		t.setToX(5);
		t.play();
		t.setOnFinished((e) ->{
			try {
				fxml = FXMLLoader.load(getClass().getResource("UiDesign01Signup.fxml"));
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
				
			} catch (IOException ex) {
				 Logger.getLogger(UiDesign01Controller.class.getName()).log(Level.SEVERE, null, ex);		
			}
		});
	}
	
	@FXML
	private void openSingin(ActionEvent event) {
		System.out.println("##################  openSingin" + vbox.getLayoutX());
		TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
//		t.setToX((vbox.getLayoutX() + 1 )*20);
		t.setToX(460);
		t.play();
		t.setOnFinished((e) ->{
			try {
				fxml = FXMLLoader.load(getClass().getResource("UiDesign01Signin.fxml"));
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
				
			} catch (IOException ex) {
				 Logger.getLogger(UiDesign01Controller.class.getName()).log(Level.SEVERE, null, ex);		
			}
		});
				
	}
	
	


}
