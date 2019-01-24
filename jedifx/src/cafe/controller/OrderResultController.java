package cafe.controller;

import cafe.SceneChanger;
import cafe.model.Beverage;
import cafe.model.CoffeeBean;
import cafe.model.Ingredient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderResultController implements Initializable {
	@FXML VBox root;
	@FXML Label totalPriceLabel;
	@FXML VBox resultContainer;
	@FXML Button titleBtn;

	private Object[] objList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 값 초기화
		Platform.runLater(() -> {
			objList = (Object []) root.getUserData();

			int totalPrice = 0;

			for(Object obj : objList) {
				if(!(obj instanceof Beverage)) return;

				Beverage beverage = (Beverage) obj;

				totalPrice += beverage.getPrice() * beverage.getAmount();

				// 음료 정보 표시
				HBox beverageContainer = new HBox();
				beverageContainer.setAlignment(Pos.TOP_CENTER);
				beverageContainer.setSpacing(20.0);

				TitledPane titledPane = new TitledPane();
				titledPane.setPrefWidth(360);
				titledPane.setText(ellipsize(beverage.getName(), 20));
				titledPane.setFont(new Font("SansSerif Bold", 14));
				beverageContainer.getChildren().add(titledPane);

				VBox titledContents = new VBox();
				titledContents.setAlignment(Pos.TOP_CENTER);
				titledContents.maxWidth(360);
				titledContents.setSpacing(10.0);
				titledContents.setPadding(new Insets(10.0));
				titledPane.setContent(titledContents);

				// 재료 정보
				for(Ingredient ingredient : beverage.getIngredients()) {
					HBox ingredientPane = new HBox();
					ingredientPane.setAlignment(Pos.CENTER);
					ingredientPane.setPrefHeight(32);
					ingredientPane.setSpacing(30);

					// 재료명
					Label ingredientNameLabel = new Label();
					ingredientNameLabel.setFont(new Font("SansSerif Bold", 17));

					if(ingredient instanceof CoffeeBean) {			// 원두 원산지
						CoffeeBean bean = (CoffeeBean) ingredient;
						ingredientNameLabel.setText(ellipsize(bean.getName() + "(원두 : " + bean.getOrigin() + ")", 18));
					} else {
						ingredientNameLabel.setText(ingredient.getName());
					}

					// 재료 수량
					Label ingredientAmountLabel = new Label(String.valueOf(ingredient.getAmount()));
					ingredientAmountLabel.setFont(new Font("SansSerif Bold", 17));

					ingredientPane.getChildren().addAll(ingredientNameLabel, ingredientAmountLabel);
					titledContents.getChildren().add(ingredientPane);
				}

				// 음료 가격
				Label beveragePriceLabel = new Label(String.valueOf(beverage.getPrice()));
				beveragePriceLabel.setFont(new Font("SansSerif Bold", 16));
				HBox.setMargin(beveragePriceLabel, new Insets(3, 0, 0, 0));

				// 음료 수량
				Label beverageAmountLabel = new Label(String.valueOf(beverage.getAmount()));
				beverageAmountLabel.setFont(new Font("SansSerif Bold", 16));
				HBox.setMargin(beverageAmountLabel, new Insets(3, 0, 0, 0));

				beverageContainer.getChildren().addAll(beveragePriceLabel, beverageAmountLabel);
				resultContainer.getChildren().add(beverageContainer);
			}

			totalPriceLabel.setText("총 : " + totalPrice + "원");
		});

		titleBtn.setOnAction(event -> {
			MenuController.resetOrderedBeverages();
			SceneChanger.getInstance().backToTitle();
		});
	}

	// 텍스트가 길면 가운데 생략
	private static String ellipsize(String text, int max) {

		if (text.length() <= max)
			return text;


		return text.substring(0, max/2 - 3) + "..." + text.substring(text.length() - max/2, text.length());
	}
}
