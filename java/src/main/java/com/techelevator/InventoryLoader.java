package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InventoryLoader {

	public Map<String, List<VendingItems>> fileImporter() throws FileNotFoundException {
		File inventoryFile = new File("vendingmachine.csv");
		Map<String, List<VendingItems>> itemMap = new LinkedHashMap<>();

		if (inventoryFile.exists()) {
			Scanner inventoryLoader = new Scanner(inventoryFile);
			while (inventoryLoader.hasNextLine()) {
				String lineInput = inventoryLoader.nextLine();
				String[] itemsArray = lineInput.split("[|]");
				switch (itemsArray[3]) {
				case "Chip": {
					List<VendingItems> itemArray = new ArrayList<>();
					Chip chipItem = new Chip(itemsArray[1], new BigDecimal(itemsArray[2]));
					itemArray.add(chipItem);
					itemMap.put(itemsArray[0], itemArray);
					break;
				}
				case "Candy": {
					List<VendingItems> itemArray = new ArrayList<>();
					Candy candyItem = new Candy(itemsArray[1], new BigDecimal(itemsArray[2]));
					itemArray.add(candyItem);
					itemMap.put(itemsArray[0], itemArray);
					break;
				}
				case "Drink": {
					List<VendingItems> itemArray = new ArrayList<>();
					Drink drinkItem = new Drink(itemsArray[1], new BigDecimal(itemsArray[2]));
					itemArray.add(drinkItem);
					itemMap.put(itemsArray[0], itemArray);
					break;
				}
				case "Gum": {
					List<VendingItems> itemArray = new ArrayList<>();
					Gum gumItem = new Gum(itemsArray[1], new BigDecimal(itemsArray[2]));
					itemArray.add(gumItem);
					itemMap.put(itemsArray[0], itemArray);
					break;
				}

				}
			}
			inventoryLoader.close();
		}
		return itemMap;

	}
}
