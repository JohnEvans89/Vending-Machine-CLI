package com.techelevator;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

	private BigDecimal balance;
	private Map<String, List<VendingItems>> inventory = new LinkedHashMap<>();
	private String format = "%-4s%-20s$ %-8s%-2s%-20s%n";
	DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
	LocalDateTime time = LocalDateTime.now();
	DecimalFormat df = new DecimalFormat("$ #,##0.00");

	public VendingMachine(Map<String, List<VendingItems>> inventory) {
		this.inventory = inventory;
		this.balance = new BigDecimal(0);
	}

	private void setBalance(BigDecimal balance) {
		this.balance = balance.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public void displayInventory() {
		for (Map.Entry<String, List<VendingItems>> temp : inventory.entrySet()) {
			List<VendingItems> list = temp.getValue();

			for (VendingItems v : list) {
				if (v.getQuantity() == 0) {
					System.out.printf(format, temp.getKey(), v.getName(), v.getPrice().toString(), v.getQuantity(),
							"SOLD OUT");

				} else {
					System.out.printf(format, temp.getKey(), v.getName(), v.getPrice().toString(), v.getQuantity(),
							"Available");
				}
			}

		}

	}

	public Integer[] dispenseChange() {
		Integer[] coins = new Integer[4];
		try (MachineLog log = new MachineLog("c:\\data\\log.txt");) {

			BigDecimal startingBalance = this.balance;
			BigDecimal quarter = new BigDecimal(.25).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal dime = new BigDecimal(.10).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal nickel = new BigDecimal(.05).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal penny = new BigDecimal(.01).setScale(2, BigDecimal.ROUND_HALF_UP);
			int quarterCount = 0;
			int dimeCount = 0;
			int nickelCount = 0;
			int pennyCount = 0;

			while (this.balance.compareTo(quarter) >= 0) {
				quarterCount += 1;
				setBalance(this.balance.subtract(quarter));
			}
			while (this.balance.compareTo(dime) >= 0) {
				dimeCount += 1;
				setBalance(this.balance.subtract(dime));
			}
			while (this.balance.compareTo(nickel) >= 0) {
				nickelCount += 1;
				setBalance(this.balance.subtract(nickel));
			}
			while (this.balance.compareTo(penny) >= 0) {
				pennyCount += 1;
				setBalance(this.balance.subtract(penny));
			}

			
			coins[0] = quarterCount;
			coins[1] = dimeCount;
			coins[2] = nickelCount;
			coins[3] = pennyCount;

			log.write(String.format("%s GIVE CHANGE: %8.8s %8.8s", FORMATTER.format(time), df.format(startingBalance),
					df.format(this.balance)));
			
		} catch (

		IOException ex) {
			System.out.println("IO Exception at:" + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("General Exception at:" + ex.getMessage());
		}
		return coins;

	}

	public String printChange(Integer[] coins) {
		String output = "Your change is: %n";
		if (coins[0] == 0 && coins[1] == 0 && coins[2] == 0 && coins[3] == 0) {
			output = "Thank You for Your Business, Have a Great Day";
		} else {	
	
		// more than 1 quarter
		if (coins[0] > 1) {
			output += coins[0] + " quarters%n";
		}
		// 1 quarter
		else if (coins[0] > 0) {
			output += coins[0] + " quarter%n";
		}
		// more than 1 dime
		if (coins[1] > 1) {
			output += coins[1] + " dimes%n";
		}
		// 1 dime
		else if (coins[1] > 0) {
			output += coins[1] + " dime%n";
		}
		// 1 nickel - 2 nickels not possible
		if (coins[2] > 0) {
			output += coins[2] + " nickel%n";
		}
		// pennies
		if (coins[3] > 1) {
			output += 
					"We have no pennies to return, your " + coins[3] + " pennies will be donated to a \"Good Cause\". %n";
		}
		// penny
		else if (coins[3] > 0) {
			output += "We have no pennies to return, your penny will be donated to a \"Good Cause\". %n";
		}
		}
		
		return output;

	}
	public String getUserInputforItemSelection() {
			boolean loop = false;
			String userInput = "";

			displayInventory();
			System.out.println();
			System.out.println("Please Select An Item (For Example A1) or Enter E to Exit: ");

			while (loop == false) {

				Scanner input = new Scanner(System.in);
				userInput = input.nextLine().toUpperCase();
				if (userInput.contentEquals("E") || userInput.contentEquals("e")) {
					loop = true;
				} else {
					for (Map.Entry<String, List<VendingItems>> temp : inventory.entrySet()) {
						if (userInput.contentEquals(temp.getKey())) {
							if (inventory.get(userInput).get(0).getQuantity() > 0
									&& this.balance.subtract(inventory.get(userInput).get(0).getPrice())
											.compareTo(BigDecimal.ZERO) >= 0) {
								loop = true;
							}
						}
					}
				}
			}
				if (loop == false) {
					displayInventory();
					System.out.println("\n*** \nSorry " + userInput + " is sold out or is not a valid option\n***");
					System.out.println("Please make another selection (For Example A1) or Enter E to Exit");

			}
			return userInput;
		}
	
	public void dispenseItem(String userInput) {
		try (MachineLog log = new MachineLog("c:\\data\\log.txt");) {
			BigDecimal startingBalance = this.balance;
			SalesLoader sloader = new SalesLoader();
			boolean loop = false;
			displayInventory();
			System.out.println();
			System.out.println("Please Select An Item (For Example A1) or Enter E to Exit: ");
			while (loop == false) {

				if (userInput.contentEquals("E") || userInput.contentEquals("e")) {
					loop = true;
				} else {
					//Update Machine Money Balance			
					setBalance(this.balance.subtract(inventory.get(userInput).get(0).getPrice()));
								
					//Update Machine Inventory			
					inventory.get(userInput).get(0).dispenseVendingItem();;

								System.out.printf(
										"Now Dispensing %s which cost $ %s. Your remaining balance is $ %s.%n",
										inventory.get(userInput).get(0).getName(),
										inventory.get(userInput).get(0).getPrice(), this.balance.toString());
								System.out.println(inventory.get(userInput).get(0).getSlogan());
								log.write(String.format("%s %-9.8s %s %8.8s %8.8s", FORMATTER.format(time),
										inventory.get(userInput).get(0).getName(), userInput,
										df.format(startingBalance), df.format(this.balance)));
								sloader.updateSalesReport(inventory.get(userInput).get(0).getName(), 1, inventory.get(userInput).get(0).getPrice());
								
								loop = true;
						}
					}
		} catch (

		IOException ex) {
			System.out.println("IO Exception at:" + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("General Exception at:" + ex.getMessage());
		}
		return;
	}

	public void feedMoney(BigDecimal bill) {
		try (MachineLog log = new MachineLog("c:\\data\\log.txt");) {
			if (bill.compareTo(BigDecimal.ZERO) > 0) {
				setBalance(balance.add(bill));
				System.out.printf("%nYou inserted a $%s bill.%n", bill.toString());
				System.out.printf("You have inserted a total of $%s.%n", balance.toString());
				log.write(String.format("%s FEED MONEY:  %8.8s %8.8s", FORMATTER.format(time), df.format(bill),
						df.format(this.balance)));
			} else {
				System.out.println("Invalid bill inserted");
			}

		} catch (

		IOException ex) {
			System.out.println("IO Exception at:" + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("General Exception at:" + ex.getMessage());
		}
	}

	public BigDecimal getBalance() {
		return balance;
	}

}
