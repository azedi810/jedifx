package cafe.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CoffeeBean extends Ingredient{

	// 원산지 Enumeration
	public enum Origin {
		BRAZIL("브라질"),
		CHILE("칠레"),
		KOREA("한국"),
		CHINA("중국"),
		ETHIOPIA("에티오피아"),
		LUWAK("코피 루왁");

		final private String name;

		Origin(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private StringProperty origin = new SimpleStringProperty();

	public CoffeeBean(String name, int cost, int amount) {
		super(name, cost, amount);
	}

	public CoffeeBean(Ingredient ingredient, int amount) {
		super(ingredient, amount);
	}

	public void setOrigin(String origin)
	{
		this.origin.set(origin);
	}

	public StringProperty stringProperty() {
		return this.origin;
	}
	
	public String getOrigin()
	{
		return (origin.get());
	}
}
