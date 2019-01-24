package design.lecture.sian.pk00;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Sample02 extends Application {
	

	 
	//UI �� �����ϰ�, ���������� ���α׷��� ���� ��Ű�� �޼ҵ� 
	@Override
	public void start(Stage primaryStage) {
		 try {
			Parent root = FXMLLoader.load(getClass().getResource("2.fxml"));
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("javafx scene builder ������");
			primaryStage.show();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
