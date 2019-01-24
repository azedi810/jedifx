package cafe.controller;

import cafe.SceneChanger;
import cafe.SceneChanger.Location;
import cafe.controller.ui.IngredientControlFactory;
import cafe.model.Ingredient;
import cafe.model.Menu;
import cafe.model.MenuBoard;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BaseIngredientController implements Initializable {
	@FXML VBox root;
	@FXML ListView<Ingredient> ingredientListView;
	@FXML TextField menuNameField;
	@FXML Button nextBtn;
	@FXML Button prevBtn;
	@FXML Label priceLabel;

	private ObservableList<Ingredient> baseIngredientList;
	private StringProperty menuNameProperty;
	private Boolean isMenuExist = true;

	public BaseIngredientController() {
		baseIngredientList = FXCollections.observableArrayList();
		baseIngredientList.add(0, new Ingredient());	// 추가 버튼 위치

		menuNameProperty = new SimpleStringProperty(null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 메뉴 이름, 텍스트필드 바인딩
		menuNameProperty.bindBidirectional(menuNameField.textProperty());

		ingredientListView.setStyle("-fx-control-inner-background: white;");

		// 값 초기화
		Platform.runLater(() -> {
			Menu initMenu = (Menu) root.getUserData();

			if(!initMenu.isDummy()) {
				menuNameProperty.setValue(initMenu.getName());
				baseIngredientList.addAll(initMenu.getBaseIngredients());
			} else {
				menuNameProperty.setValue("");
			}
		});

		// Event Handling
		baseIngredientList.addListener((ListChangeListener<Ingredient>) c -> {			// 재료 변경 이벤트
			// 첫 번째 Null(추가 버튼) 뺀 subList
			List<Ingredient> withoutNull = baseIngredientList.subList(1, baseIngredientList.size());

			Menu existingMenu = MenuBoard.getInstance().getMenu(withoutNull);
			isMenuExist = existingMenu != null;

			if(baseIngredientList.size() > 1) {
				if (!isMenuExist) {
					menuNameField.setDisable(false);
					nextBtn.setDisable(true);
					menuNameProperty.setValue("");
					priceLabel.setText(calcBasePrice(withoutNull) + "원");
				} else {
					menuNameField.setDisable(true);
					nextBtn.setDisable(false);
					menuNameProperty.setValue(existingMenu.getName());
					priceLabel.setText(existingMenu.getPrice() + "원");
				}
			} else {
				menuNameField.setDisable(true);
				nextBtn.setDisable(true);
				menuNameProperty.setValue("");
				priceLabel.setText("0원");
			}
		});

		menuNameProperty.addListener((observable, oldValue, newValue) -> {			// 메뉴 이름 변경 이벤트
			if(!isMenuExist) {
				// 존재하는 메뉴의 이름인지 판별
				Menu existingMenu = MenuBoard.getInstance().getMenu(newValue);

				if ("".equals(newValue)) {
					// 메뉴명 빈 칸
					nextBtn.setDisable(true);
				} else if (existingMenu != null) {
					// 이미 있는 메뉴명
					nextBtn.setDisable(true);
				} else {
					nextBtn.setDisable(false);
				}
			}
		});

		prevBtn.setOnAction(event -> SceneChanger.getInstance().back());

		nextBtn.setOnAction(event -> {
			String name = menuNameProperty.getValue();
			Menu result;
			Menu existingMenu = MenuBoard.getInstance().getMenu(name);

			menuNameField.setDisable(true);

			if(existingMenu == null) {	// 새로운 메뉴이면
				result = new Menu(name);

				result.getBaseIngredients().addAll(baseIngredientList.subList(1, baseIngredientList.size()));

				if(result.findBaseIngredient("샷") != null) {		// 샷이 있는 메뉴일 때
					// 새로운 메뉴는 샷 추가만 가능
					result.addExtraIngredient("샷");
				}

				MenuBoard.getInstance().addMenu(result);
			} else {
				result = new Menu(existingMenu);
			}

			MenuBoard.getInstance().saveToFile();
			SceneChanger.getInstance().next(Location.EXTRA, result);
		});

		ingredientListView.setItems(baseIngredientList);
		ingredientListView.setPlaceholder(new Label("재료가 없습니다."));
		ingredientListView.setCellFactory(new IngredientControlFactory());
	}

	private int calcBasePrice(List<Ingredient> baseIngredients) {
		int price = Menu.getBasePrice();

		for(Ingredient ingredient : baseIngredients) {
			price += ingredient.getCost() * ingredient.getAmount();
		}

		return price;
	}
}
