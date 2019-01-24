package design.lecture.sian.pk02;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application {
	/*
	 * ������ ���� ������ �� �ִ� ��Ʃ�� ���� :
	 * https://www.youtube.com/watch?v=jtdTHAQSb6c&list=PL383vhYFx-7owa-bxRJixFfdxDF3HnAkt&index=1&pbjreload=10
	 *  ������ �ø�Ƽ�� �ֻ�� ���Ǵ� 19�� ���� ��
	 */
	
	public static Stage stage = null;
	
	@Override
	public void start(Stage stage) {
		 try {
			Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
			
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);   // ���ȭ�� ����
			
			stage.setScene(scene);
			this.stage = stage;
			stage.initStyle(StageStyle.TRANSPARENT); 
			stage.show();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
