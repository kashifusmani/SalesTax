package com.salestax.businessobjects;

import java.math.BigDecimal;

import com.salestax.util.ValidationHelper;

/**
 * This class represent an item in Receipt. For example "1 Box of imported Chocolate, 10.50".
 * It maintains how much sales tax applies on an item and how much total cost of an item is.
 * @author kashifu
 *
 */
public class ItemReceiptEntry {
	private Item item;
	private BigDecimal salesTax;

	public ItemReceiptEntry(Item item, BigDecimal salesTax) {
		ValidationHelper.validateForNull(item, "item");
		ValidationHelper.validateForNull(salesTax, "salesTax");
		this.item = item;
		this.salesTax = salesTax;
	}

	public Item getItem() {
		return this.item;
	}

	public BigDecimal getSalesTax() {
		return this.salesTax;
	}

	public BigDecimal getItemTotalPrice() {
		return item.getItemPrice().getUnitValue().add(salesTax);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemReceiptEntry [item=");
		builder.append(item);
		builder.append(", salesTax=");
		builder.append(salesTax);
		builder.append("]");
		return builder.toString();
	}

}
