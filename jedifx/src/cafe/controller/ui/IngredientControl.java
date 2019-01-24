package cafe.controller.ui;

import cafe.SceneChanger;
import cafe.controller.AddBaseDialogController;
import cafe.model.Ingredient;
import cafe.model.MenuBoard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class IngredientControl extends ListCell<Ingredient> {
	@FXML Label nameLabel;
	@FXML Button deleteBtn;

	IngredientControl() {
		loadFXML();
	}

	private void loadFXML() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe/view/ui/control_ingredient.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();

			// Event Handling
			deleteBtn.setOnAction(event -> {
				// 리스트에서 Ingredient 제거
				getListView().getItems().remove(getItem());
			});
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	protected void updateItem(Ingredient item, boolean empty) {
		super.updateItem(item, empty);

		if(empty) {
			setText("");
			setContentDisplay(ContentDisplay.TEXT_ONLY);
		} else {
			if(item.isDummy()) {
				nameLabel.setText("+");
				deleteBtn.setVisible(false);

				nameLabel.setOnMouseClicked(event -> {
					((AddBaseDialogController) SceneChanger.getInstance()
							.newDialog(SceneChanger.Location.ADD_BASE)).initDialog(getListView().getItems());
				});
			} else {
				nameLabel.setText(item.getName());
				nameLabel.setOnMouseClicked(null);
				deleteBtn.setVisible(true);
			}

			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		}
	}
}
