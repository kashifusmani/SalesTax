package com.salestax.businessobjects;

import com.salestax.util.ValidationHelper;

/**
 * This class represents an Item. for example
 * "A box of imported chocolate, Price: 10"
 * 
 * @author kashifu
 * 
 */
public class Item {
	private ItemOrigin itemOrigin;
	private ItemType itemType;
	private Price itemPrice;
	private String itemDescription;

	public Item(ItemOrigin itemOrigin, ItemType itemType, Price price, String itemDescription) {
		ValidationHelper.validateForNull(itemOrigin, "itemOrigin");
		ValidationHelper.validateForNull(itemType, "itemType");
		ValidationHelper.validateForNull(price, "itemPrice");
		ValidationHelper.validateNotBlank(itemDescription, "itemDescription");
		this.itemOrigin = itemOrigin;
		this.itemType = itemType;
		this.itemPrice = price;
		this.itemDescription = itemDescription;
	}

	public ItemOrigin getItemOrigin() {
		return itemOrigin;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public Price getItemPrice() {
		return itemPrice;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [itemOrigin=");
		builder.append(itemOrigin);
		builder.append(", itemType=");
		builder.append(itemType);
		builder.append(", itemPrice=");
		builder.append(itemPrice);
		builder.append(", itemDescription=");
		builder.append(itemDescription);
		builder.append("]");
		return builder.toString();
	}
	
	
}
