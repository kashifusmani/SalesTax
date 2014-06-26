package com.salestax.businessobjects;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class ItemReceiptEntryTest {
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullItem() {
		new ItemReceiptEntry(null, new BigDecimal("10.9"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullSalesTax() {
		Item item = new Item(ItemOrigin.IMPORTED, ItemType.BOOK, new Price(new BigDecimal("10"), Currency.USD), "desc");
		new ItemReceiptEntry(item, null);
	}

	@Test
	public void testGetters() {
		BigDecimal unitValue = new BigDecimal("10");
		Price price = new Price(unitValue, Currency.USD);
		String description = "some item";
		Item item = new Item(ItemOrigin.IMPORTED, ItemType.BOOK, price, description);

		BigDecimal salesTax = new BigDecimal("0.50");
		ItemReceiptEntry entry = new ItemReceiptEntry(item, salesTax);
		assertEquals(entry.getItem(), item);
		assertEquals(entry.getSalesTax(), salesTax);

		BigDecimal totalPrice = unitValue.add(salesTax);
		assertEquals(totalPrice, entry.getItemTotalPrice());
	}
}
