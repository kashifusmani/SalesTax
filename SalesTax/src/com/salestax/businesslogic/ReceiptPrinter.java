package com.salestax.businesslogic;

import java.util.List;

import com.salestax.businessobjects.Item;
import com.salestax.businessobjects.ItemOrigin;
import com.salestax.businessobjects.ItemReceiptEntry;
import com.salestax.businessobjects.Receipt;
import com.salestax.util.ValidationHelper;

/**
 * This class is responsible for 'Printing a Receipt'.
 * It is meant to be the entry point for the Sales Tax program.
 * @author kashifu
 *
 */
public class ReceiptPrinter {
	private ReceiptCalculator receiptCalculator;
	
	public ReceiptPrinter(ReceiptCalculator receiptCalculator) {
		ValidationHelper.validateForNull(receiptCalculator, "receiptCalculator");
		this.receiptCalculator = receiptCalculator;
	}
	
	public String printReceipt(List<Item> itemList) {
		ValidationHelper.validateForNull(itemList, "itemList");
		ValidationHelper.validateIterableForNullEntries(itemList, "itemList"); //another option could be to ignore null items
		String receiptString = "";
		Receipt receipt = receiptCalculator.calculateReceipt(itemList);
		for(ItemReceiptEntry receiptItem: receipt.getReceiptItems()) {
			receiptString += "1 ";
			if (receiptItem.getItem().getItemOrigin().equals(ItemOrigin.IMPORTED)) {
				receiptString += receiptItem.getItem().getItemOrigin().toString();
				receiptString += " ";
			}
			receiptString += receiptItem.getItem().getItemDescription() + " ";
			receiptString += receiptItem.getItemTotalPrice().toString() + "\n";			
		}
		receiptString += "Sales Tax: " + receipt.getTotalSalesTax() + "\n";
		receiptString += "Total: " + receipt.getReceiptTotalPrice();
		return receiptString;
	}
}
