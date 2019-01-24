package cafe.controller;

import cafe.SceneChanger;
import cafe.controller.ui.BaseChoiceControl;
import cafe.model.Beverage;
import cafe.model.Ingredient;
import cafe.model.Menu;
import cafe.model.MenuBoard;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class ExtraIngredientController implements Initializable {
	@FXML private VBox root;
	@FXML private ToggleGroup isHot;
	@FXML private TextField menuNameField;
	@FXML private Label priceLabel;
	@FXML private Button prevBtn;
	@FXML private Button nextBtn;
	@FXML private CheckBox isExtraCheck;
	@FXML private VBox leftContainer;
	@FXML private HBox extraContainer;
	@FXML private ScrollPane extraOuterContainer;

	private ObservableList<Ingredient> extraIngredientList;
	private Beverage beverage;
	private Menu initMenu;
	private boolean hasOptions = false;

	public ExtraIngredientController() {
		extraIngredientList = FXCollections.observableArrayList();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 값 초기화
		Platform.runLater(() -> {
			initMenu = (Menu) root.getUserData();

			if(!initMenu.isDummy()) {
				menuNameField.setText(initMenu.getName());
				extraIngredientList.addAll(initMenu.getExtraIngredients());
				priceLabel.setText(initMenu.getPrice() + "원");

				for(Ingredient ingredient : extraIngredientList) {
					BaseChoiceControl baseChoice = new BaseChoiceControl();
					baseChoice.nameLabel.setText(ingredient.getName());
					baseChoice.costLabel.setText("(+" + ingredient.getCost() + ")");
					baseChoice.amountField.setText("" + ingredient.getAmount());
					baseChoice.defaultAmountLabel.setText("(기본 " + ingredient.getAmount() + ")");

					// Event Handling
					baseChoice.amountField.textProperty().addListener((observable, oldValue, newValue) -> {
						// 0 이상의 정수만 입력
						try {
							int inputNum = "".equals(newValue) ? 0 : Integer.parseInt(newValue);

							if (inputNum < 0) {
								baseChoice.amountField.setText(oldValue);
							} else {
								baseChoice.amountField.setText(String.valueOf(inputNum));
								ingredient.setAmount(inputNum);
							}
						} catch (NumberFormatException e) {
							baseChoice.amountField.setText(oldValue);
						}

						priceLabel.setText(calcFinalPrice(extraIngredientList) + "원");
					});

					extraContainer.getChildren().add(baseChoice);
				}

				if(extraIngredientList.size() == 0) {
					leftContainer.getChildren().remove(extraOuterContainer);
				}
			}
		});

		menuNameField.setDisable(true);

		// Event Handling
		isExtraCheck.selectedProperty().addListener(
				(observable, oldValue, newValue) -> priceLabel.setText(calcFinalPrice(extraIngredientList) + "원"));

		prevBtn.setOnAction(event -> SceneChanger.getInstance().back());

		nextBtn.setOnAction(event -> {
			beverage = new Beverage(initMenu);
			beverage.setPrice(calcPrice(extraIngredientList));
			beverage.setExtra(isExtraCheck.isSelected());
			beverage.setHot(isHot.getSelectedToggle().getUserData().equals("true"));


			String beverageName = beverage.getName();
			beverageName += "(";
			if(beverage.isHot()) beverageName += "H";
			else beverageName += "I";

			if(beverage.isExtra()) beverageName += ", E";

			beverageName += ")";

			if(hasOptions) beverageName += "*";


			beverage.setName(beverageName);


			if(initMenu.findBaseIngredient("샷") == null) {		// 샷이 없으면 원두 선택 없이 바로 주문 추가
				SceneChanger.getInstance().back();
				SceneChanger.getInstance().back();
				SceneChanger.getInstance().back();
				SceneChanger.getInstance().next(SceneChanger.Location.MENU, beverage);
			} else {
				SceneChanger.getInstance().next(SceneChanger.Location.COFFE_BEAN, beverage);

			}
		});
	}

	// 사이즈 포함 가격 (extra 고려)
	private int calcFinalPrice(List<Ingredient> extraIngredients) {
		int price = calcPrice(extraIngredients);

		price += isExtraCheck.isSelected() ? Menu.getSizePrice() : 0;

		return price;
	}

	// 사이즈 제외 음료 가격 (extra 포함)
	private int calcPrice(List<Ingredient> extraIngredients) {
		Menu origMenu = MenuBoard.getInstance().getMenu(initMenu.getName());
		List<Ingredient> origList = origMenu.getExtraIngredients();

		int price = origMenu.getPrice();
		hasOptions = false;

		// 재료 이름으로 정렬
		origList.sort(Comparator.comparing(Ingredient::getName));
		extraIngredients.sort(Comparator.comparing(Ingredient::getName));

		for(int i=0; i<extraIngredients.size(); i++) {
			int amountDiff = extraIngredients.get(i).getAmount() - origList.get(i).getAmount();

			if(amountDiff != 0) hasOptions = true;

			price += origList.get(i).getCost() * amountDiff;
		}

		return price;
	}
}
