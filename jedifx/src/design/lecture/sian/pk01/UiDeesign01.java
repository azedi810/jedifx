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
	 * 동영상 강의 따라할 수 있는 유튜브 강좌 :
	 * https://www.youtube.com/watch?v=jtdTHAQSb6c&list=PL383vhYFx-7owa-bxRJixFfdxDF3HnAkt&index=1&pbjreload=10
	 *  디자인 컬리티가 최상급 강의는 19개 정도 됨
	 */
	@Override
	public void start(Stage stage) {
		 try {
			Parent root = FXMLLoader.load(getClass().getResource("UiDesign01.fxml"));
			
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
