package design.lecture.sian.pk01;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UiDeesign01 extends Application {

	
	/*
	 * ������ ���� ������ �� �ִ� ��Ʃ�� ���� :
	 * https://www.youtube.com/watch?v=jtdTHAQSb6c&list=PL383vhYFx-7owa-bxRJixFfdxDF3HnAkt&index=1&pbjreload=10
	 *  ������ �ø�Ƽ�� �ֻ�� ���Ǵ� 19�� ���� ��
	 */
	@Override
	public void start(Stage stage) {
		 try {
			Parent root = FXMLLoader.load(getClass().getResource("UiDesign01.fxml"));
			
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
