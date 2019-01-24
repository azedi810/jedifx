package cafe.controller.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import cafe.SceneChanger;
import cafe.SceneChanger.Location;
import cafe.model.Menu;

import java.io.IOException;

public class MenuControl extends StackPane {
	@FXML
	private Label name;
	@FXML
	private StackPane customLabel;
	@FXML
	public Rectangle rectangle;

	private Menu menu;

	public MenuControl(Menu menu) {
		this.menu = menu;

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cafe/view/ui/control_menu.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();

			// Control 요소 세팅
			name.setText(menu.isDummy() ? "+" : menu.getName());

			customLabel.setVisible(menu.isCustom());	// On Demand 라벨 표시 여부

			// 특정 컨트롤 Mouse Event 무효화
			customLabel.setMouseTransparent(true);
			name.setMouseTransparent(true);


			// Mouse Event Handling

			if(!menu.isDummy()) {
				rectangle.setOnMouseEntered(event -> {
					rectangle.setFill(Color.rgb(243, 156, 18));
					name.setText(String.valueOf(menu.getPrice()));
				});

				rectangle.setOnMouseExited(event -> {
					rectangle.setFill(Color.rgb(0xE0, 0xE0, 0xE0));
					name.setText(menu.getName());
				});
			}

			rectangle.setOnMouseClicked(event -> {
				SceneChanger.getInstance().next(Location.BASE, menu);
			});
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}

