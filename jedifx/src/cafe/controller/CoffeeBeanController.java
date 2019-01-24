package cafe.controller;

import cafe.SceneChanger;
import cafe.controller.ui.BaseChoiceControl;
import cafe.model.Beverage;
import cafe.model.CoffeeBean;
import cafe.model.Ingredient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class CoffeeBeanController implements Initializable {
	@FXML private VBox root;
	@FXML private TextField menuNameField;
	@FXML private ScrollPane coffeeBeanOuterContainer;
	@FXML private TilePane coffeeBeanContainer;
	@FXML private Label priceLabel;
	@FXML private Button prevBtn;
	@FXML private Button nextBtn;

	private Beverage beverage;
	private CoffeeBean.Origin selected = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 값 초기화
		Platform.runLater(() -> {
			beverage = (Beverage) root.getUserData();

			if(beverage != null) {
				menuNameField.setText(beverage.getName());
				priceLabel.setText(beverage.getPrice() + "원");
			}

		});

		nextBtn.setDisable(true);
		menuNameField.setDisable(true);

		coffeeBeanContainer.setStyle("-fx-focus-color: transparent;");
		coffeeBeanContainer.setStyle("-fx-background-color: white;");
		coffeeBeanOuterContainer.setStyle("-fx-focus-color: transparent;");
		coffeeBeanOuterContainer.setStyle("-fx-background-color: white;");

		// Control 추가
		for (CoffeeBean.Origin origin : CoffeeBean.Origin.values()) {
			BaseChoiceControl baseChoiceControl = new BaseChoiceControl();
			baseChoiceControl.nameLabel.setText(origin.getName());
			baseChoiceControl.costLabel.setVisible(false);
			baseChoiceControl.getChildren().remove(baseChoiceControl.amountContainer);

			// 클릭 이벤트 처리
			baseChoiceControl.setOnMouseClicked(event -> {
				coffeeBeanContainer.getChildren().forEach(node -> {
					BaseChoiceControl control = (BaseChoiceControl) node;

					control.choiceCircle.setFill(Color.rgb(221, 221, 221));
					control.nameLabel.setTextFill(Color.BLACK);
				});

				selected = origin;
				baseChoiceControl.choiceCircle.setFill(Color.rgb(111, 78, 55));
				baseChoiceControl.nameLabel.setTextFill(Color.WHITE);

				nextBtn.setDisable(false);
			});

			coffeeBeanContainer.getChildren().add(baseChoiceControl);
		}

		prevBtn.setOnAction(event -> SceneChanger.getInstance().back());
		nextBtn.setOnAction(event -> {
			for(Ingredient ingredient : beverage.getIngredients()) {
				if(ingredient instanceof CoffeeBean) {
					((CoffeeBean) ingredient).setOrigin(selected.getName());
				}
			}

			SceneChanger.getInstance().back();
			SceneChanger.getInstance().back();
			SceneChanger.getInstance().back();
			SceneChanger.getInstance().back();
			SceneChanger.getInstance().next(SceneChanger.Location.MENU, beverage);
		});
	}
}
