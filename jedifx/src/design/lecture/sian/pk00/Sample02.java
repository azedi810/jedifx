package design.lecture.sian.pk00;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Sample02 extends Application {
	

	 
	//UI 를 생성하고, 실질적으로 프로그램을 동작 시키는 메소드 
	@Override
	public void start(Stage primaryStage) {
		 try {
			Parent root = FXMLLoader.load(getClass().getResource("2.fxml"));
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("javafx scene builder 디자인");
			primaryStage.show();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
