package com.salestax.businessobjects;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class ItemTest {
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullItemOrigin() {
		new Item(null, ItemType.BOOK, new Price(new BigDecimal("10"), Currency.USD), "Some item");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullItemType() {
		new Item(ItemOrigin.IMPORTED, null, new Price(new BigDecimal("10"), Currency.USD), "Some item");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullPrice() {
		new Item(ItemOrigin.IMPORTED, ItemType.BOOK, null, "Some item");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorBlankDescription() {
		new Item(ItemOrigin.IMPORTED, ItemType.BOOK, new Price(new BigDecimal("10"), Currency.USD), " ");
	}

	@Test
	public void testGetters() {
		BigDecimal unitValue = new BigDecimal("10");
		Price price = new Price(unitValue, Currency.USD);
		String description = "some item";
		Item item = new Item(ItemOrigin.IMPORTED, ItemType.BOOK, price, description);
		assertEquals(item.getItemDescription(), description);
		assertEquals(item.getItemOrigin(), ItemOrigin.IMPORTED);
		assertEquals(item.getItemPrice().getUnitValue(), unitValue);
		assertEquals(item.getItemPrice().getCurrency(), Currency.USD);
		assertEquals(item.getItemType(), ItemType.BOOK);
	}
}
