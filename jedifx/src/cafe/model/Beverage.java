package cafe.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Beverage {
	
	private String name;
	private List<Ingredient> ingredients;
	private boolean isExtra;
	private boolean isHot;
	private int price;
	private IntegerProperty amount;
	
	public Beverage(Menu menu) {
		Menu m = new Menu(menu);

		this.name = m.getName();
		this.isExtra = false;
		this.isHot = false;
		this.price = m.getPrice();
		this.ingredients = new ArrayList<>();
		this.amount = new SimpleIntegerProperty(1);

		ingredients.addAll(m.getBaseIngredients());
		
		for(Ingredient ingredient : m.getExtraIngredients())
		{
			if(!ingredients.contains(ingredient)) {
				ingredients.add(ingredient);
			}
		}
	}

	// Getter / Setter
	public boolean isExtra() {
		return isExtra;
	}

	public void setExtra(boolean isExtra) {
		this.isExtra = isExtra;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	public int getIngredientAmount(String ingredientName)
	{
		Ingredient ingredient = findIngredient(ingredientName);

		if(ingredient != null) {
			return ingredient.getAmount();
		}

		return -1;
	}

	public void setIngredientAmount(String ingredientName, int amount)
	{
		if(amount < 0) return;

		Ingredient ingredient = findIngredient(ingredientName);

		if(ingredient != null) {
			ingredient.setAmount(amount);
		}
	}

	private Ingredient findIngredient(String ingredientName) {
		if(ingredientName == null) return null;

		for (Ingredient ingredient : ingredients) {
			if (ingredientName.equals(ingredient.getName())) {
				return ingredient;
			}
		}
		return null;
	}

	// 사이즈 고려 X
	public int getOriginalPrice() {
		return price;
	}

	// 사이즈 고려
	public int getPrice() {
		return this.isExtra ? price + Menu.getSizePrice() : price;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount.get();
	}

	public IntegerProperty amountProperty() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount.set(amount);
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
