package com.salestax.businesslogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.salestax.businessobjects.Currency;
import com.salestax.businessobjects.Item;
import com.salestax.businessobjects.ItemOrigin;
import com.salestax.businessobjects.ItemReceiptEntry;
import com.salestax.businessobjects.ItemType;
import com.salestax.businessobjects.Price;
import com.salestax.businessobjects.Receipt;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptCalculatorTest {
	private ReceiptCalculator receiptCalculator;
	@Mock
	private TaxManager mockTaxManager;

	@Before
	public void setUp() {
		receiptCalculator = new ReceiptCalculator(mockTaxManager);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullTaxManager() {
		new ReceiptCalculator(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateReceiptNullInput() {
		receiptCalculator.calculateReceipt(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateReceiptNullInInputList() {
		BigDecimal unitValueItemOne = new BigDecimal("10");
		Price priceItemOne = new Price(unitValueItemOne, Currency.USD);
		String descriptionItemOne = "some item";
		Item itemOne = new Item(ItemOrigin.IMPORTED, ItemType.BOOK, priceItemOne, descriptionItemOne);
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(itemOne);
		itemList.add(null);
		receiptCalculator.calculateReceipt(itemList);
	}

	@Test
	public void testCalculateReceiptHappyPath() {
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

		List<Item> itemList = new ArrayList<Item>();
		itemList.add(itemOne);
		itemList.add(itemTwo);

		when(mockTaxManager.calculateTax(itemOne)).thenReturn(entryOne);
		when(mockTaxManager.calculateTax(itemTwo)).thenReturn(entryTwo);

		Receipt result = receiptCalculator.calculateReceipt(itemList);

		assertNotNull(result);
		assertEquals(result.getReceiptItems().size(), 2);
		assertTrue(result.getReceiptItems().contains(entryOne));
		assertTrue(result.getReceiptItems().contains(entryTwo));

		verify(mockTaxManager, times(2)).calculateTax(any(Item.class));
	}
}
