package com.salestax.businessobjects;

import java.math.BigDecimal;
import java.util.List;

import com.salestax.util.ValidationHelper;

/**
 * This class represents a Receipt. It is a collection of ItemReceiptEntry object.
 * It provides methods to calculate total cost of a "Receipt" and total applied sales tax.
 * @author kashifu
 *
 */
public class Receipt {
	private List<ItemReceiptEntry> receiptItems;

	public Receipt(List<ItemReceiptEntry> receiptItems) {
		ValidationHelper.validateForNull(receiptItems, "receiptItems");
		this.receiptItems = receiptItems;
	}

	public List<ItemReceiptEntry> getReceiptItems() {
		return this.receiptItems;
	}

	public BigDecimal getTotalSalesTax() {
		BigDecimal totalSalesTax = new BigDecimal("0.0");
		for (ItemReceiptEntry entry : receiptItems) {
			totalSalesTax = totalSalesTax.add(entry.getSalesTax());
		}
		return totalSalesTax;
	}

	public BigDecimal getReceiptTotalPrice() {
		BigDecimal totalReceiptPrice = new BigDecimal("0.0");
		for (ItemReceiptEntry entry : receiptItems) {
			totalReceiptPrice = totalReceiptPrice.add(entry.getItemTotalPrice());
		}
		return totalReceiptPrice;
	}
}
