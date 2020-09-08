package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import com.techelevator.view.Menu;
 
public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALESREPORT = "Secret Sales Report";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALESREPORT };

	private static final String PURCHASE_SUBMENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_SUBMENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_SUBMENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] PURCHASE_SUBMENU_OPTIONS = { PURCHASE_SUBMENU_OPTION_FEED_MONEY,
			PURCHASE_SUBMENU_OPTION_SELECT_PRODUCT, PURCHASE_SUBMENU_OPTION_FINISH_TRANSACTION };

	private static final String FEED_MONEY_SUBMENU_OPTION_FEED_1 = "Feed $ 1.00";
	private static final String FEED_MONEY_SUBMENU_OPTION_FEED_2 = "Feed $ 2.00";
	private static final String FEED_MONEY_SUBMENU_OPTION_FEED_5 = "Feed $ 5.00";
	private static final String FEED_MONEY_SUBMENU_OPTION_FEED_10 = "Feed $10.00";
	private static final String FEED_MONEY_SUBMENU_OPTION_DONE_FEEDING = "Done Inserting Money";

	private static final String[] FEED_MONEY_SUBMENU_OPTIONS = { FEED_MONEY_SUBMENU_OPTION_FEED_1,
			FEED_MONEY_SUBMENU_OPTION_FEED_2, FEED_MONEY_SUBMENU_OPTION_FEED_5, FEED_MONEY_SUBMENU_OPTION_FEED_10,
			FEED_MONEY_SUBMENU_OPTION_DONE_FEEDING };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws FileNotFoundException {
			while (true) {
				InventoryLoader loader = new InventoryLoader();
				SalesLoader sloader = new SalesLoader();
				sloader.salesTracker();
				VendingMachine machine = new VendingMachine(loader.fileImporter());
				
				
				String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

				if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
					machine.displayInventory();

				} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					// PURCHASE SUB-MENU CODE
					boolean purchaseLoop = false;
					while (purchaseLoop == false) {
						choice = (String) menu.getChoiceFromOptions(PURCHASE_SUBMENU_OPTIONS);
						if (choice.equals(PURCHASE_SUBMENU_OPTION_FEED_MONEY)) {
							// FEED_MONEY SUB-MENU CODE
							boolean feedLoop = false;
							while (feedLoop == false) {
								choice = (String) menu.getChoiceFromOptions(FEED_MONEY_SUBMENU_OPTIONS);
								if (choice.equals(FEED_MONEY_SUBMENU_OPTION_FEED_1)) {
									machine.feedMoney(new BigDecimal(1.00));
								} else if (choice.equals(FEED_MONEY_SUBMENU_OPTION_FEED_2)) {
									machine.feedMoney(new BigDecimal(2.00));
								} else if (choice.equals(FEED_MONEY_SUBMENU_OPTION_FEED_5)) {
									machine.feedMoney(new BigDecimal(5.00));
								} else if (choice.equals(FEED_MONEY_SUBMENU_OPTION_FEED_10)) {
									machine.feedMoney(new BigDecimal(10.00));
								} else if (choice.equals(FEED_MONEY_SUBMENU_OPTION_DONE_FEEDING)) {
									System.out.printf("You have inserted a total of $%s.%n",
											machine.getBalance().toString());
									feedLoop = true;
									// exit
								}
							}
						} else if (choice.equals(PURCHASE_SUBMENU_OPTION_SELECT_PRODUCT)) {
//						boolean selectLoop = false;
//						while (selectLoop == false) {
							machine.dispenseItem(machine.getUserInputforItemSelection());;
//						}

						} else if (choice.equals(PURCHASE_SUBMENU_OPTION_FINISH_TRANSACTION)) {
							System.out.printf(machine.printChange(machine.dispenseChange()));
							try {
								sloader.printSalesReport();
							
									} catch (Exception ex) {
										System.out.println("General Exception at:" + ex.getMessage());
									}

							purchaseLoop = true;
						}
					}
				} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
					// exit
					System.out.println();
					System.out.println();
					System.out.println("Thank you, have a nice day.");
					break;
				} else if (choice.equals(MAIN_MENU_OPTION_SALESREPORT)) {
					System.out.println();
					System.out.println();
					System.out.println(">>>>>Your Sales Report has been updated.");
					
				}
			}
	
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
