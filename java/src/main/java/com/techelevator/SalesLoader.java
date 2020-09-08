package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesLoader {

	private BigDecimal priorRevenue;
	private BigDecimal totalRevenue;
	private int priorSalesCount = 0;
	private Map<String, Integer> salesMap;
	DecimalFormat df = new DecimalFormat("###0.00");

	public Map<String, Integer> salesTracker() throws FileNotFoundException {
		int blankLineCount = 0;
		File salesFile = new File("c:\\data\\salesLedger.csv");
		salesMap = new LinkedHashMap<>();

		if (salesFile.exists()) {
			Scanner salesLoader = new Scanner(salesFile);
			while (salesLoader.hasNextLine()) {
				String lineInput = salesLoader.nextLine();
				if (blankLineCount == 1) {
					priorRevenue = new BigDecimal(lineInput);
				}

				else if (lineInput.equals("")) {
					blankLineCount += 1;
				} else {

					String[] salesArray = lineInput.split("[|]");
					salesMap.put(salesArray[0], Integer.parseInt(salesArray[1]));
					priorSalesCount = getPriorSalesCount() + Integer.parseInt(salesArray[1]);
				}
			}

			salesLoader.close();
		}
		return salesMap;
	}

	public void updateSalesReport(String name, int count, BigDecimal revenue) {
		if (salesMap.containsKey(name)) {
			salesMap.put(name, salesMap.get(name) + count);
		} else {
			salesMap.put(name, count);
		}
		this.totalRevenue = totalRevenue.add(revenue);
	}

	public void printSalesReport() {
		try {
			SalesLog slog = new SalesLog("c:\\data\\salesLedger.csv");
			for (Map.Entry<String, Integer> temp : salesMap.entrySet()) {
				String entry = temp.getKey() + "|" + temp.getValue();
				slog.write(entry);

			}
			slog.write("");
			slog.write(this.totalRevenue.toString());
			slog.close();
		} catch (

		IOException ex) {
			System.out.println("IO Exception at:" + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("General Exception at:" + ex.getMessage());
		}

	}

	public BigDecimal getPriorRevenue() {
		return priorRevenue;
	}

	public int getPriorSalesCount() {
		return priorSalesCount;
	}

}
