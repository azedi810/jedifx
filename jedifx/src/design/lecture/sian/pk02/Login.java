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
	 * 동영상 강의 따라할 수 있는 유튜브 강좌 :
	 * https://www.youtube.com/watch?v=jtdTHAQSb6c&list=PL383vhYFx-7owa-bxRJixFfdxDF3HnAkt&index=1&pbjreload=10
	 *  디자인 컬리티가 최상급 강의는 19개 정도 됨
	 */
	
	public static Stage stage = null;
	
	@Override
	public void start(Stage stage) {
		 try {
			Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
			
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);   // 배경화면 투명
			
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
