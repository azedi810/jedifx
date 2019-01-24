package cafe.controller;

import cafe.SceneChanger;
import cafe.controller.ui.BaseChoiceControl;
import cafe.controller.ui.IngredientControlFactory;
import cafe.controller.ui.MenuControl;
import cafe.model.Ingredient;
import cafe.model.Inventory;
import cafe.model.Menu;
import cafe.model.MenuBoard;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle ;

public class AdminMenuController implements Initializable {

    @FXML private ListView<Ingredient> ingreListView;
    @FXML private AnchorPane root;

    @FXML private Button btnCan, btnSave, btnDel;
    @FXML private Button btnOpt;
    @FXML private TilePane menuPane;
    @FXML private TextField editMenuPrice;
    @FXML private TextField editBasicPrice;
    @FXML private TextField txtMenuField;

    @FXML private RadioButton radioBasic;
    @FXML private RadioButton radioCustom;
    @FXML private CheckBox checkBox;

    private List<Menu> menuList = MenuBoard.getInstance().getMenuList();
    private ObservableList<Ingredient> baseIngredientList, extraIngredientList;
    private Menu currentMenu;          //현재 접근하고 있는 메뉴

    private boolean isIngredientValid;
    private boolean isNameValid;

    private int basePrice = Menu.getBasePrice();

    private StringProperty menuNameProperty, menuPriceProperty;

    public AdminMenuController() {
        baseIngredientList = FXCollections.observableArrayList();
        extraIngredientList = FXCollections.observableArrayList();

        menuNameProperty = new SimpleStringProperty(null);
        menuPriceProperty = new SimpleStringProperty(null);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* 재료 리스트 관련 이벤트 */

        // 메뉴 이름,가격 텍스트필드 바인딩
        menuNameProperty.bindBidirectional(txtMenuField.textProperty());
        menuPriceProperty.bindBidirectional(editMenuPrice.textProperty());

        /* 메뉴 리스트 관련 초기화 */
        listingMenu(menuList, false);

        /* 버튼 이벤트 관련 초기화 */
        btnCan.setOnAction(this::handleCan);
        btnSave.setOnAction(this::handleSave);
        btnDel.setOnAction(this::handleDel);
        btnOpt.setOnAction(this::handleOpt);
        radioBasic.setOnAction(this::handleBasic);
        radioCustom.setOnAction(this::handleCustom);

        // Event Handling
        baseIngredientList.addListener((ListChangeListener<Ingredient>) c -> {			// 재료 변경 이벤트
            // 첫 번째 Null(추가 버튼) 뺀 subList
            if(baseIngredientList.size() > 1) {
                List<Ingredient> withoutNull = baseIngredientList.subList(1, baseIngredientList.size());

                Menu existingMenu = MenuBoard.getInstance().getMenu(withoutNull);

                if (existingMenu == null) {                     // 새로운 조합
                    isIngredientValid = true;
                } else {
                    isIngredientValid = existingMenu == currentMenu;
                }
            } else {
                isIngredientValid = false;
            }

            btnSave.setDisable(!isIngredientValid || !isNameValid);

            if(checkBox.isSelected()) {
                // 가격 갱신
                editMenuPrice.setText(String.valueOf(calcCurrentPrice()));
            }
        });

        menuNameProperty.addListener((observable, oldValue, newValue) -> {
            if("".equals(newValue)) {
                isNameValid = false;
            } else {
                Menu nameMenu = MenuBoard.getInstance().getMenu(newValue);

                isNameValid = nameMenu == null         // 다른 메뉴의 이름
                        || (!currentMenu.isDummy() && nameMenu.getName().equals(currentMenu.getName()));
            }
            btnSave.setDisable(!isIngredientValid || !isNameValid);
        });

        //체크박스 리스너
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                editMenuPrice.setDisable(true);

                int price = calcCurrentPrice();

                menuPriceProperty.setValue("" + price);
            } else {
                editMenuPrice.setDisable(false);
            }
        });

        //Basic Price 입력창 리스너
        editBasicPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
				int inputBasePrice = Integer.parseInt(editBasicPrice.getText());

				if(inputBasePrice >= 0) {
					basePrice = inputBasePrice;
				} else {
					editBasicPrice.setText(oldValue);
				}

				Menu.setBasePrice(basePrice);

				// 현재 표시된 가격 변경
                if (checkBox.isSelected()) {
                    if (currentMenu != null && !currentMenu.isDummy() && !currentMenu.isPriceFixed()) {
						menuPriceProperty.setValue("" + calcCurrentPrice());
					}
                }

				MenuBoard.getInstance().saveToFile();
            } catch (NumberFormatException e) {
            	e.printStackTrace();
			}
        });

        /* 기타 옵션들 관련 초기화 */
        final ToggleGroup group = new ToggleGroup();
        radioBasic.setToggleGroup(group);
        radioCustom.setToggleGroup(group);
        radioBasic.setSelected(true);

        btnOpt.setDisable(true);
        checkBox.setSelected(true);
        editMenuPrice.setDisable(true);
        editBasicPrice.setText(String.valueOf(Menu.getBasePrice()));

    }
    private void handleOpt(ActionEvent event) {
        extraIngredientList = FXCollections.observableArrayList();

        if(currentMenu != null && !currentMenu.isDummy()) {
            for(Ingredient ingredient : currentMenu.getExtraIngredients()) {
                extraIngredientList.add(Inventory.getInstance().getIngredient(ingredient.getName()));
            }
        }

        AddBaseDialogController control = ((AddBaseDialogController) SceneChanger.getInstance()
                .newDialog(SceneChanger.Location.ADD_BASE));

        List<Ingredient> dialogIngredients = new ArrayList<>();
        dialogIngredients.add(Inventory.getInstance().getIngredient("샷"));

        control.initDialog(dialogIngredients);

        BaseChoiceControl control2;
        for(Node node : control.ingredientPane.getChildren()) {
            for (Ingredient ingredient : extraIngredientList) {
                control2 = (BaseChoiceControl) node;
                if(ingredient.getName().equals(control2.nameLabel.getText())) {
                    control2.choiceCircle.setFill(Color.rgb(243, 156, 18));
                    control2.setClicked(true);
                }
            }
        }

        control.addBtn.setOnAction(event2 -> {
            BaseChoiceControl control3;

            for (Node node : control.ingredientPane.getChildren()) {

                control3 = (BaseChoiceControl) node;
                if (control3.isClicked())
                    extraIngredientList.add(Inventory.getInstance().getIngredient(control3.nameLabel.getText()));
                else
                    extraIngredientList.remove(Inventory.getInstance().getIngredient(control3.nameLabel.getText()));
            }

            control.addBtn.getScene().getWindow().hide();
        });
    }

    private void handleCan(ActionEvent event) {

        SceneChanger.getInstance().back();
    }

    private void handleSave(ActionEvent event) {
        // 기본 가격
        int basicPrice = Integer.parseInt(editBasicPrice.getText());
        Menu.setBasePrice(basicPrice);

        Menu menu;
        if(!currentMenu.isDummy()) {                        // 기존 데이터 수정
            menu = currentMenu;
            // 메뉴 이름
            currentMenu.setName(txtMenuField.getText());

            // 메뉴 가격
            currentMenu.setPrice(Integer.parseInt(editMenuPrice.getText()));
            currentMenu.setPriceFixed(!checkBox.isSelected());

        } else {                                            // 새 메뉴 추가
            String name = menuNameProperty.getValue();
            boolean isPriceFixed = !checkBox.isSelected();
            int price = Integer.parseInt(editMenuPrice.getText());
            boolean isCustom = radioCustom.isSelected();


            if(isPriceFixed) {
                menu = new Menu(name, price, isCustom);
            } else {
                menu = new Menu(name, isCustom);
            }
            MenuBoard.getInstance().addMenu(menu);
            menu = MenuBoard.getInstance().getMenu(menu.getName());
            currentMenu = menu;
        }

        menu.getBaseIngredients().clear();
        menu.getExtraIngredients().clear();

        for(Ingredient ingredient : baseIngredientList) {
            if(ingredient.isDummy()) continue;
            if(ingredient.getName().equals("샷"))
                currentMenu.addExtraIngredient("샷");
            menu.addBaseIngredient(ingredient.getName());
        }

        for(Ingredient ingredient : extraIngredientList) {
            if(ingredient.isDummy()) continue;
            if(ingredient.getName().equals("샷")) continue;

            menu.addExtraIngredient(ingredient.getName());
        }

        listingMenu(menuList, radioCustom.isSelected());
        MenuBoard.getInstance().saveToFile();
    }

    private void handleDel(ActionEvent event) {
        if(!currentMenu.isDummy()) {
            MenuBoard.getInstance().removeMenu(currentMenu.getName());

            this.currentMenu = menuList.get(0);     // current = dummy
            listingMenu(menuList, radioCustom.isSelected());
            listingIngre(currentMenu);
        }

        MenuBoard.getInstance().saveToFile();
    }

    private void handleBasic(ActionEvent event) {
        listingMenu(menuList, false);
    }
    private void handleCustom(ActionEvent event) {
        listingMenu(menuList, true);
    }

    private void listingMenu(List<Menu> menuList, boolean isCustom) {     //메뉴를 나열해주는 함수
        menuPane.getChildren().clear();
        for (Menu menu : menuList) {
            if (isCustom == menu.isCustom() || menu.isDummy()) {

                MenuControl control = new MenuControl(menu);
                menuPane.getChildren().add(control);
                control.rectangle.setOnMouseClicked(event -> {
                    listingIngre(menu);
                });
            }
        }
    }
    private void listingIngre(Menu menu) {
        this.currentMenu = menu;

        btnOpt.setDisable(false);
        btnDel.setDisable(menu.isDummy());

        // 값 초기화
        Platform.runLater(() -> {
            ingreListView.getItems().clear();
            baseIngredientList.add(0, new Ingredient());

            if(!menu.isDummy()) {
                menuNameProperty.setValue(menu.getName());
                menuPriceProperty.setValue(String.valueOf(menu.getPrice()));
                checkBox.setSelected(!menu.isPriceFixed());
                baseIngredientList.addAll(menu.getBaseIngredients());
            } else {
                menuNameProperty.setValue("");
                menuPriceProperty.setValue(String.valueOf(0));
                checkBox.setSelected(true);
            }
        });

        ingreListView.setItems(baseIngredientList);
        ingreListView.setPlaceholder(new Label("재료가 없습니다."));
        ingreListView.setCellFactory(new IngredientControlFactory());
    }

    // 현재 조합에 따른 가격 계산
    private int calcCurrentPrice() {
        int price = Menu.getBasePrice();

        for(Ingredient ingredient : baseIngredientList) {
            if(ingredient.isDummy()) continue;

            price += ingredient.getCost();
        }

        return price;
    }
}
