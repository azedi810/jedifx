package cafe.controller.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import cafe.model.Beverage;

import java.io.IOException;

public class OrderControl extends ListCell<Beverage> {

	@FXML private Label titleLabel;
	@FXML private Button minusBtn;
	@FXML private TextField amountField;
	@FXML private Button plusBtn;
	@FXML private Button deleteBtn;


	OrderControl() {
		loadFXML();
	}

	private void loadFXML() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe/view/ui/control_order.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();

			// Event Handling
			amountField.textProperty().addListener((observable, oldValue, newValue) -> {
				// 0 이상의 정수만 입력
				try {
					int inputNum = Integer.parseInt(newValue);

					if (inputNum < 0) {
						amountField.setText(oldValue);
					} else {
						getItem().setAmount(inputNum);
					}

				} catch (NumberFormatException e) {
					amountField.setText(oldValue);
				}
			});

			minusBtn.setOnAction(event -> {
				if(getItem().getAmount() > 0) {
					getItem().setAmount(getItem().getAmount() - 1);;
					amountField.setText(String.valueOf(getItem().getAmount()));
				}
			});

			plusBtn.setOnAction(event -> {
				getItem().setAmount(getItem().getAmount() + 1);;
				amountField.setText(String.valueOf(getItem().getAmount()));
			});

			deleteBtn.setOnAction(event -> {
				// 리스트에서 Beverage 제거
				getListView().getItems().remove(getItem());
			});
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void updateItem(Beverage item, boolean empty) {
		super.updateItem(item, empty);

		if(empty) {
			setText("");
			setContentDisplay(ContentDisplay.TEXT_ONLY);
		} else {
			titleLabel.setText(item.getName());
			amountField.setText(String.valueOf(item.getAmount()));
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		}
	}
}
