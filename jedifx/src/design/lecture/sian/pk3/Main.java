package design.lecture.sian.pk3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*
 * °­ÀÇ
 * https://www.youtube.com/watch?v=YuiXnPefmhU&index=15&list=PLlGZc17KPrVAKj3Tl1im5HN8Lh5nYTXyB
*/

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Home.fxml"));
        primaryStage.setTitle("1223344444444444");
        primaryStage.getIcons().add(new Image("/design/lecture/sian/pk3/icons/icon.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    //you can download the glyph browser - link provided.
    public static void main(String[] args) {
        launch(args);
    }
}