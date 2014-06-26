package com.salestax.businessobjects;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ReceiptTest {
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullList() {
		new Receipt(null);
	}

	@Test
	public void testHappyPath() {
		BigDecimal unitValueItemOne = new BigDecimal("10");
		Price priceItemOne = new Price(unitValueItemOne, Currency.USD);
		String descriptionItemOne = "some item";
		Item itemOne = new Item(ItemOrigin.IMPORTED, ItemType.BOOK, priceItemOne, descriptionItemOne);

		BigDecimal salesTaxItemOne = new BigDecimal("0.50");
		ItemReceiptEntry entryOne = new ItemReceiptEntry(itemOne, salesTaxItemOne);

		BigDecimal unitValueItemTwo = new BigDecimal("27.99");
		Price priceItemTwo = new Price(unitValueItemTwo, Currency.USD);
		String descriptionItemTwo = "prefume";
		Item itemTwo = new Item(ItemOrigin.IMPORTED, ItemType.PERFUME, priceItemTwo, descriptionItemTwo);

		BigDecimal salesTaxItemTwo = new BigDecimal("4.2");
		ItemReceiptEntry entryTwo = new ItemReceiptEntry(itemTwo, salesTaxItemTwo);

		List<ItemReceiptEntry> entryList = new ArrayList<ItemReceiptEntry>();
		entryList.add(entryOne);
		entryList.add(entryTwo);

		Receipt receipt = new Receipt(entryList);
		assertEquals(receipt.getReceiptItems(), entryList);

		assertEquals(receipt.getTotalSalesTax(), salesTaxItemOne.add(salesTaxItemTwo));
		assertEquals(receipt.getReceiptTotalPrice(), entryOne.getItemTotalPrice().add(entryTwo.getItemTotalPrice()));
	}
}
