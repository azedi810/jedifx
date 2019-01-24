package cafe.model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private static Inventory instance;
	private List<Ingredient> ingredientList;

	// Singleton
	private Inventory () {
		ingredientList = new ArrayList<>();

		// File Input => Add Loaded Ingredients
		// TODO: 파일 입력으로 재료 목록 받아오기

		/*Test Data*/
		readFromFile();
	}

	public static Inventory getInstance() {
		if(instance == null) {
			instance = new Inventory();
		}

		return instance;
	}


	// 재료 검색
	public Ingredient getIngredient(String name) {
		if(name == null) return null;

		for(Ingredient ingredient : ingredientList) {
			if(name.equals(ingredient.getName())) {
				return ingredient;
			}
		}

		return null;
	}

	// 재료 추가
	public void addIngredient(String name, int cost, int initStockAmount) {
		addIngredient(name, cost, initStockAmount, false);
	}

	public void addIngredient(String name, int cost, int initStockAmount, boolean isCoffee) {
		if(getIngredient(name) == null) {
			if(isCoffee) {
				ingredientList.add(new CoffeeBean(name, cost, initStockAmount));
			} else {
				ingredientList.add(new Ingredient(name, cost, initStockAmount));
			}
		}
	}

	// 재료 삭제
	public void removeIngredient(String name) {
		Ingredient ingredient = getIngredient(name);
		if(ingredient != null) {
			ingredientList.remove(ingredient);
		}
	}

	// Ingredient List Getter
	public List<Ingredient> getIngredientList() {
		return ingredientList;
	}

	private void insertTestData() {
		addIngredient("샷", 500, -1, true);
		addIngredient("물", 0, -1);
		addIngredient("휘핑크림", 500, -1);
		addIngredient("초코시럽", 700, -1);
		addIngredient("데운우유", 1000, -1);

		getIngredient("샷").setAmount(800);
	}

	public void readFromFile() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("data/ingredient.txt"));

			while(true) {
				String line = br.readLine();
				if (line==null) break;
				String[] tokens = line.split("\t");

				addIngredient(tokens[0], Integer.parseInt(tokens[1]), -1, Boolean.parseBoolean(tokens[2]));
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 메뉴 정보를 파일로 저장
	public void saveToFile() {
		FileOutputStream output;
		try {
			// Name	Cost	isCoffee
			output = new FileOutputStream("data/ingredient.txt");

			for(Ingredient ingredient : ingredientList) {
				if(ingredient.isDummy()) continue;

				String str = ingredient.getName() + '\t' +				// 재료명
						ingredient.getCost() + '\t' +					// 가격
						(ingredient instanceof CoffeeBean) + "\r\n";	// 커피 여부

				output.write(str.getBytes());
			}

			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
