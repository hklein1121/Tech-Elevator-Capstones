package com.techelevator;

import java.util.List;

import com.techelevator.inventory.CsvInventoryReader;
import com.techelevator.inventory.InventoryPopulator;
import com.techelevator.items.Item;
import com.techelevator.items.ProductInfo;
import com.techelevator.view.CateringItemDisplay;
import com.techelevator.view.InputOutputMaster;

public class CateringSystemCLI {
	
	private final static String MAIN_MENU_DISPLAY_CATERING_ITEMS_OPTION = "Display Catering Items";
	private final static String MAIN_MENU_ORDER_OPTION = "Order";
	private final static String MAIN_MENU_QUIT_OPTION = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_DISPLAY_CATERING_ITEMS_OPTION, MAIN_MENU_ORDER_OPTION, MAIN_MENU_QUIT_OPTION};
	
	private final static String DISPLAY_ACCOUNT_BALANCE = "Current account balance: ";
	private final static String PURCHASING_MENU_ADD_MONEY_OPTION = "Add Money";
	private final static String PURCHASING_MENU_SELECT_PRODUCTS_OPTION = "Select Products";
	private final static String PURCHASING_MENU_COMPLETE_TRANSACTION_OPTION = "Complete Transaction";
	private static final String[] PURCHASING_MENU_OPTIONS = {PURCHASING_MENU_ADD_MONEY_OPTION, PURCHASING_MENU_SELECT_PRODUCTS_OPTION, PURCHASING_MENU_COMPLETE_TRANSACTION_OPTION};

	private InputOutputMaster inputOutputMaster; 
	private LogFiler logFiler;

	public CateringSystemCLI(InputOutputMaster ioMaster) {
		logFiler = new LogFiler();
		this.inputOutputMaster = ioMaster;
	}

	public void run() {
		String fileName = inputOutputMaster.getFileName();
		CsvInventoryReader inventoryReader = new CsvInventoryReader(fileName);
		InventoryPopulator inventoryPopulator = new InventoryPopulator(inventoryReader);
		InventoryTracker inventoryTracker = new InventoryTracker(inventoryPopulator);
		CateringSystem cateringSystem = new CateringSystem(inventoryTracker);
		CateringItemDisplay ciDisplay = new CateringItemDisplay();
		List<ProductInfo> productInfos = inventoryTracker.getProductInfos();
		CustomerBalance customerBalance = cateringSystem.getCustomerBalance();
		Cart cart = cateringSystem.getCart();
		
		while (true) {
			inputOutputMaster.displayUserOptions(MAIN_MENU_OPTIONS);
			String choice = (String) inputOutputMaster.getSelectionFromUser(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_DISPLAY_CATERING_ITEMS_OPTION)) {
				ciDisplay.displayAllCateringMenuItems(productInfos);
			} else if (choice.equals(MAIN_MENU_ORDER_OPTION)) {
				while (true)
				{
					inputOutputMaster.displayMoneyMessage(DISPLAY_ACCOUNT_BALANCE , customerBalance.getBalance());
					inputOutputMaster.displayUserOptions(PURCHASING_MENU_OPTIONS);
					String purchaseMenuChoice = (String) inputOutputMaster.getSelectionFromUser(PURCHASING_MENU_OPTIONS);
					
					if (purchaseMenuChoice.equals(PURCHASING_MENU_ADD_MONEY_OPTION)){
						inputOutputMaster.displayUserMessage("Input Amount To Add: ");
						int amountOfMoneyToAdd = inputOutputMaster.readIntegerAmount();
						boolean moneyAddedSuccessfully = customerBalance.addMoney(amountOfMoneyToAdd);
						if (moneyAddedSuccessfully == true) {
							inputOutputMaster.displayUserMessage("Money added successfully\n");
						} else {
							inputOutputMaster.displayUserMessage("Invalid amount of money\n");
						}
					}
					else if (purchaseMenuChoice.equals(PURCHASING_MENU_SELECT_PRODUCTS_OPTION)) {
						ciDisplay.displayAllCateringMenuItems(productInfos);
						inputOutputMaster.displayUserMessage("Input product code to add to cart:");
						String stringProductCode = inputOutputMaster.readString();
						ProductInfo productInfo = cart.isValidProductCode(stringProductCode, productInfos);
						if (productInfo == null) {
							inputOutputMaster.displayUserMessage("Invalid Product Code\n");
							continue;
						}
						else {
							inputOutputMaster.displayUserMessage("Input amount to add to cart:");
							int numberOfObjectsToAdd = inputOutputMaster.readIntegerAmount();
							Item item = productInfo.getItem();
							boolean successfullyAddedToCart = cart.addToCart(numberOfObjectsToAdd, productInfo);
							double totalTransactionCost = (item.getPrice() * numberOfObjectsToAdd);
							if (successfullyAddedToCart && (totalTransactionCost < customerBalance.getBalance())) {
								productInfo.decrementStock(numberOfObjectsToAdd);
								inputOutputMaster.displayUserMessage("Item has been successsfully added to cart\n");
								customerBalance.subtractMoney(totalTransactionCost);
								logFiler.purchaseLogger(numberOfObjectsToAdd, productInfo, totalTransactionCost, customerBalance.getBalance());
								continue;
							}
							else if (productInfo.isSoldOut()) {
								inputOutputMaster.displayUserMessage("SOLD OUT\n");
								continue;
							}
							else {
								inputOutputMaster.displayUserMessage("Could not complete transaction.\n");
								continue;
							}
						}
					}
					else if (purchaseMenuChoice.equals(PURCHASING_MENU_COMPLETE_TRANSACTION_OPTION)) {
						cart.displayReceipt();
						customerBalance.makeChange(customerBalance.getBalance());
						customerBalance.emptyBalance();
						cart.clearCart();
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_QUIT_OPTION)) {
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) {
		InputOutputMaster inputOutputMaster = new InputOutputMaster();
		CateringSystemCLI cli = new CateringSystemCLI(inputOutputMaster);
		cli.run();
	}
}
