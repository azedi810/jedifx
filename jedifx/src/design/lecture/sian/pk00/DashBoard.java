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
	

	 // ��Ʃ�� ���� ���� : https://www.youtube.com/watch?v=o0Z9RmSXF3k
	//UI �� �����ϰ�, ���������� ���α׷��� ���� ��Ű�� �޼ҵ� 
	@Override
	public void start(Stage stage) {
		 try {
			Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);   // ���ȭ�� ����
			
			stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setTitle("javafx scene builder ������");
			stage.show();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
