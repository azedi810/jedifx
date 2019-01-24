package cafe.controller.ui;

import cafe.model.Beverage;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


public class OrderControlFactory implements Callback<ListView<Beverage>, ListCell<Beverage>> {
	@Override
	public ListCell<Beverage> call(ListView<Beverage> param) {
		return new OrderControl();
	}
}
