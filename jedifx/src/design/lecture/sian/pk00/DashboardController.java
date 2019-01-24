package design.lecture.sian.pk00;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DashboardController implements Initializable {
	
	double x=0, y=0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void Dragged(MouseEvent event) {
		Node node = (Node) event.getSource();
		
		Stage stage = (Stage) node.getScene().getWindow();
		
		
		stage.setX(event.getScreenX()-x);
		stage.setY(event.getScreenY()-y);
		
	}
	
	@FXML
	private void Pressed(MouseEvent event) {
		x=event.getSceneX();
		y=event.getSceneY();
				
	}

}
