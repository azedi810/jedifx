package cafe.controller;

import cafe.SceneChanger;
import cafe.model.Ingredient;
import cafe.model.Inventory;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle ;

public class AdminIngreController implements Initializable {

	@FXML private Button btnAdd;
	@FXML private Button btnRes;
	@FXML private Button btnDel;
	@FXML private Button btnEdit;
    @FXML private Button btnCan;
    @FXML private TableView<Ingredient> ingredientTable;
    @FXML private TableColumn<Ingredient, String> nameColumn;
    @FXML private TableColumn<Ingredient, Integer> priceColumn;
    @FXML private TextField addNameTextField;
    @FXML private TextField addCostTextField;
    @FXML private TextField delNameTextField;
    @FXML private TextField delCostTextField;
    
    private ObservableList<Ingredient> adminIngredientList = FXCollections.observableArrayList();;
    
    public AdminIngreController() {
    	adminIngredientList.addAll(Inventory.getInstance().getIngredientList());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    	nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	priceColumn.setCellValueFactory(cellData -> cellData.getValue().costProperty().asObject());
    	ingredientTable.setItems(adminIngredientList);
    	
    	ingredientTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    		delNameTextField.setText(newSelection.getName());
    		delCostTextField.setText(String.valueOf(newSelection.getCost()));
    	});
    	
    	btnAdd.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			boolean isExitIngredient = false;
    			for (Ingredient addIngredient : adminIngredientList)
    			{   			
    				if (addIngredient.getName().equals(addNameTextField.getText()))
    				{
    					isExitIngredient = true;
    					break;
    				}
    			}
    			if (isExitIngredient == false)
    			{
    				Ingredient newIngredient = new Ingredient(addNameTextField.getText(),
    							Integer.parseInt(addCostTextField.getText()), -1);
    				adminIngredientList.add(newIngredient);
    				Inventory.getInstance().addIngredient(addNameTextField.getText(),
    							Integer.parseInt(addCostTextField.getText()), -1);
    				Inventory.getInstance().saveToFile();
    				addNameTextField.setText("");
        			addCostTextField.setText("");
    			}
    		}
    	});
    	
    	btnRes.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			addNameTextField.setText("");
    			addCostTextField.setText("");
    		}
    	});
    	
    	btnDel.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			for (Ingredient delIngredient : adminIngredientList)
    			{
    				if (delIngredient.getName().equals(delNameTextField.getText()))
    				{
    					adminIngredientList.remove(delIngredient);
    					Inventory.getInstance().removeIngredient(delIngredient.getName());
    					break;
    				}
    			}
				Inventory.getInstance().saveToFile();
    			delNameTextField.setText("");
    			delCostTextField.setText("");
    		}
    	});
    	
    	btnEdit.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			for (Ingredient delIngredient : adminIngredientList)
    			{
    				if (delIngredient.getName().equals(delNameTextField.getText()))
    				{
    					delIngredient.setName(delNameTextField.getText());
    					delIngredient.setCost(Integer.parseInt(delCostTextField.getText()));
    					break;
    				}
    			}
				Inventory.getInstance().saveToFile();
    			delNameTextField.setText("");
    			delCostTextField.setText("");
    		}
    	});
  
    	btnCan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleCan(event);
            }
        });
    }

    public void handleCan(ActionEvent event) {
        SceneChanger.getInstance().back();
    }
}