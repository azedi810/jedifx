package cafe;

import cafe.SceneChanger.Location;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage	primaryStage;
	public static Main instance;

	public Main() {
		instance = this;
	}

	@Override
	public void	start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("On Demand Cafe");
		primaryStage.setResizable(false);

		SceneChanger.getInstance().next(Location.TITLE);
	}

	public Stage getPrimaryStage()
	{
		return primaryStage;
	}
	
	// Run Program
	public static void main(String[] args) {
		launch(args);
	}
}
