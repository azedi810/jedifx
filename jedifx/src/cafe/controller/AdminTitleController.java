package cafe.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cafe.SceneChanger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class AdminTitleController implements Initializable {
	@FXML
	private Button btnMenu;
	@FXML
	private Button btnIng;
	@FXML
	private Button btnBack;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleMenu(event);
			}
		});
		btnIng.setOnAction(event -> handleIng(event));
		btnBack.setOnAction(event -> handleBack(event));
	}

	public void handleMenu(ActionEvent event) {
		SceneChanger.getInstance().next(SceneChanger.Location.ADMENU);
	}

	public void handleIng(ActionEvent event) {
		SceneChanger.getInstance().next(SceneChanger.Location.ADING);
	}

	public void handleBack(ActionEvent event) {
		SceneChanger.getInstance().back();
	}
}
