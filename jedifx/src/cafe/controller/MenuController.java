package cafe.controller;

import cafe.SceneChanger;
import cafe.controller.ui.OrderControlFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import cafe.controller.ui.MenuControl;
import cafe.model.Beverage;
import cafe.model.Menu;
import cafe.model.MenuBoard;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable {
	@FXML private VBox root;
	@FXML private TilePane menuPane;
	@FXML private ScrollPane scrollPane;
	@FXML private ListView<Beverage> orderListView;
	@FXML private Label totalPriceLabel;
	@FXML private Button btnComplete;
	@FXML private Button btnCancel;

	private static ObservableList<Beverage> orderedBeverages = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scrollPane.setStyle("-fx-background-color:transparent;");	// ScrollPane border 투명화

		// 값 초기화
		Platform.runLater(() -> {
			Beverage beverage = (Beverage) root.getUserData();

			if(beverage != null) {
				orderedBeverages.add(beverage);
				for(Beverage b : orderedBeverages) {
					// 수량 변화에 따른 가격 합 갱신
					b.amountProperty().addListener(
							(observable, oldValue, newValue) -> totalPriceLabel.setText(String.valueOf(calcTotalPrice())));
				}

			}
		});

		List<Menu> menuList = MenuBoard.getInstance().getMenuList();

		for(Menu menu : menuList) {
			menuPane.getChildren().add(new MenuControl(menu));
		}

		totalPriceLabel.setText(String.valueOf(calcTotalPrice()));

		orderListView.setItems(orderedBeverages);
		orderListView.setPlaceholder(new Label("주문된 음료가 없습니다."));
		orderListView.setCellFactory(new OrderControlFactory());

		// Add Event Listeners
		orderedBeverages.addListener((ListChangeListener<? super Beverage>) c -> {	// 리스트에 변화가 생기면 가격 합 갱신
			totalPriceLabel.setText(String.valueOf(calcTotalPrice()));
		});

		btnComplete.setOnAction(event -> SceneChanger.getInstance().next(SceneChanger.Location.RESULT, orderedBeverages.toArray()));

		btnCancel.setOnAction(event -> {
			// 주문 초기화
			resetOrderedBeverages();
			SceneChanger.getInstance().back();
		});
	}

	// 총 음료 가격 합계 계산
	private int calcTotalPrice() {
		int totalPrice = 0;

		for(Beverage b : orderedBeverages) {
			totalPrice += b.getPrice() * b.getAmount();
		}

		return totalPrice;
	}

	public static void resetOrderedBeverages() {
		orderedBeverages = FXCollections.observableArrayList();
	}
}
