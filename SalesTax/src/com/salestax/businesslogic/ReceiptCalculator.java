package com.salestax.businesslogic;

import java.util.ArrayList;
import java.util.List;

import com.salestax.businessobjects.Item;
import com.salestax.businessobjects.ItemReceiptEntry;
import com.salestax.businessobjects.Receipt;
import com.salestax.util.ValidationHelper;

/**
 * This class is responsible for calculating a 'Receipt'.
 * It provides utility methods to take a list of Item as input and return a Receipt object.
 * It does this by talking to TaxManager 
 * @author kashifu
 *
 */
public class ReceiptCalculator {
	private TaxManager taxManager;
	public ReceiptCalculator(TaxManager taxManager) {
		ValidationHelper.validateForNull(taxManager, "taxManager");
		this.taxManager = taxManager;
	}
	
	public Receipt calculateReceipt(List<Item> itemList) {
		ValidationHelper.validateForNull(itemList, "itemList");
		ValidationHelper.validateIterableForNullEntries(itemList, "itemList"); //another option would be to ignore null items
		List<ItemReceiptEntry> receiptEntries = new ArrayList<ItemReceiptEntry>();
		for (Item item: itemList) {
			receiptEntries.add(taxManager.calculateTax(item));
		}
		return new Receipt(receiptEntries);
	}
}
