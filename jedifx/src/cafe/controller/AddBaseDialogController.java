package cafe.controller;

import cafe.controller.ui.BaseChoiceControl;
import cafe.model.Ingredient;
import cafe.model.Inventory;
import cafe.model.Menu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBaseDialogController extends DialogController implements Initializable {
	@FXML public TilePane	ingredientPane;
	@FXML private Button	cancelBtn;
	@FXML public Button		addBtn;

	private List<Ingredient> baseIngredients;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		addBtn.setOnAction(event -> {

			for (Node node : ingredientPane.getChildren())
			{
				BaseChoiceControl baseChoice = (BaseChoiceControl) node;

				if(!baseChoice.isClicked()) continue;

				Ingredient invIngredient = Inventory.getInstance().getIngredient(baseChoice.nameLabel.getText());
				baseIngredients.add(new Ingredient(invIngredient, 1));
			}

			addBtn.getScene().getWindow().hide();
		});
		
		cancelBtn.setOnAction(event -> {
			cancelBtn.getScene().getWindow().hide();
		});
	}
	
	public void initDialog(List<Ingredient> baseIngredients) {
		this.baseIngredients = baseIngredients;

		//To do 다시 새로 짜야될듯
		List<Ingredient> dialogIngredients = new ArrayList<>(Inventory.getInstance().getIngredientList());

		for (Ingredient ingredient : baseIngredients) {
			if(ingredient.isDummy()) continue;

			Ingredient invIngredient = Inventory.getInstance().getIngredient(ingredient.getName());

			// 인벤토리에 있는
			if(invIngredient != null) {
				dialogIngredients.remove(invIngredient);
			}
		}

		for(Ingredient ingredient : dialogIngredients) {
			BaseChoiceControl baseChoice = new BaseChoiceControl();

			baseChoice.nameLabel.setText(ingredient.getName());
			baseChoice.costLabel.setText(String.valueOf(ingredient.getCost()));

			baseChoice.amountContainer.setVisible(false);

			baseChoice.nameLabel.setMouseTransparent(true);
			baseChoice.costLabel.setMouseTransparent(true);

			/*
			baseChoice.choiceCircle.setOnMouseEntered(event -> baseChoice.choiceCircle.setFill(Color.rgb(243, 156, 18)));
			baseChoice.choiceCircle.setOnMouseExited(event -> {
				if(!baseChoice.isClicked()) {
					baseChoice.choiceCircle.setFill(Color.rgb(221, 221, 221));
				}
			});
			*/
			baseChoice.choiceCircle.setOnMouseClicked(event -> {
				if (!baseChoice.isClicked()) {
					baseChoice.choiceCircle.setFill(Color.rgb(243, 156, 18));
				} else {
					baseChoice.choiceCircle.setFill(Color.rgb(221, 221, 221));
				}

				baseChoice.setClicked(!baseChoice.isClicked());
			});

			ingredientPane.getChildren().add(baseChoice);
		}
	}
}
