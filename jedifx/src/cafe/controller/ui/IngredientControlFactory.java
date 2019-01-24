package cafe.controller.ui;

import cafe.model.Ingredient;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


public class IngredientControlFactory implements Callback<ListView<Ingredient>, ListCell<Ingredient>> {
	@Override
	public ListCell<Ingredient> call(ListView<Ingredient> param) {
		return new IngredientControl();
	}
}
