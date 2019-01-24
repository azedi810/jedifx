package cafe.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ingredient {

	private StringProperty name;
	private IntegerProperty cost;
	private IntegerProperty amount;

	// Constructors
	public Ingredient() {			// Dummy
		this.name = null;
		this.cost = null;
		this.amount = null;
	}

	public Ingredient(String name, int cost, int amount) {
		this.name = new SimpleStringProperty(name);
		this.cost = new SimpleIntegerProperty(cost);
		this.amount = new SimpleIntegerProperty(amount);
	}

	public Ingredient(Ingredient ingredient, int amount) {			// 이미 만들어진 재료를 기반으로 생성(수량 따로 지정)
		this.name = new SimpleStringProperty(ingredient.getName());
		this.cost = new SimpleIntegerProperty(ingredient.getCost());
		this.amount = new SimpleIntegerProperty(amount);
	}

	// Getter / Setter / Property Getter
	public String getName()
	{
		return name.get();
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return this.name;
	}

	public int getCost()
	{
		return cost.get();
	}

	public void setCost(int cost)
	{
		this.cost.set(cost);
	}

	public IntegerProperty costProperty() {
		return this.cost;
	}

	public int getAmount()
	{
		return amount.get();
	}

	public void setAmount(int amount)
	{
		this.amount.set(amount);
	}

	public IntegerProperty amountProperty() {
		return this.amount;
	}

	public boolean isDummy() {
		return this.name == null;
	}


}
