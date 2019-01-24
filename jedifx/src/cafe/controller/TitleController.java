package cafe.controller;

import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class TitleController implements Initializable {
	
	@FXML private Label			pressSpaceLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), pressSpaceLabel);
		fadeTransition.setFromValue(0.0);
		fadeTransition.setToValue(1.0);
		fadeTransition.setCycleCount(Animation.INDEFINITE);
		fadeTransition.play();
		
		/*.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.SPACE)
			{
				SceneChanger.getInstance().next(Location.MENU);
			}
		});*/
	}
}