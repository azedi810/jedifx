package design.lecture.sian.pk00;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashBoard extends Application {
	

	 // 유튜브 강의 참고 : https://www.youtube.com/watch?v=o0Z9RmSXF3k
	//UI 를 생성하고, 실질적으로 프로그램을 동작 시키는 메소드 
	@Override
	public void start(Stage stage) {
		 try {
			Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);   // 배경화면 투명
			
			stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setTitle("javafx scene builder 디자인");
			stage.show();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
