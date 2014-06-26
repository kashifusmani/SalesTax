package com.salestax.businesslogic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
public class ReceiptPrinterTest {
	private ReceiptPrinter receiptPrinter;
	@Mock
	private ReceiptCalculator mockReceiptCalculator;

	@Before
	public void setUp() {
		receiptPrinter = new ReceiptPrinter(mockReceiptCalculator);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullReceiptCalculator() {
		new ReceiptPrinter(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrintReceiptNullList() {
		receiptPrinter.printReceipt(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrintReceiptNullInList() {
		BigDecimal unitValueItemOne = new BigDecimal("10");
		Price priceItemOne = new Price(unitValueItemOne, Currency.USD);
		String descriptionItemOne = "some item";
		Item itemOne = new Item(ItemOrigin.IMPORTED, ItemType.BOOK, priceItemOne, descriptionItemOne);
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(itemOne);
		itemList.add(null);
		receiptPrinter.printReceipt(itemList);
	}

	@Test
	public void testPrintReceiptHappyPath() {
		BigDecimal unitValueItemOne = new BigDecimal("10");
		Price priceItemOne = new Price(unitValueItemOne, Currency.USD);
		String descriptionItemOne = "some item";
		Item itemOne = new Item(ItemOrigin.IMPORTED, ItemType.BOOK, priceItemOne, descriptionItemOne);

		BigDecimal salesTaxItemOne = new BigDecimal("0.50");
		ItemReceiptEntry entryOne = new ItemReceiptEntry(itemOne, salesTaxItemOne);

		List<Item> itemList = new ArrayList<Item>();
		itemList.add(itemOne);

		List<ItemReceiptEntry> receiptEntryList = new ArrayList<ItemReceiptEntry>();
		receiptEntryList.add(entryOne);

		Receipt receipt = new Receipt(receiptEntryList);
		when(mockReceiptCalculator.calculateReceipt(itemList)).thenReturn(receipt);
		String receiptString = receiptPrinter.printReceipt(itemList);

		verify(mockReceiptCalculator, times(1)).calculateReceipt(itemList);
		assertNotNull(receiptString);
		
		//Let us make some basic sanity tests as to how our output string should look like
		assertFalse(StringUtils.isBlank(receiptString));
		assertTrue(receiptString.contains("1"));
		assertTrue(receiptString.contains("IMPORTED"));
		assertTrue(receiptString.contains(descriptionItemOne));
		assertTrue(receiptString.contains(unitValueItemOne.add(salesTaxItemOne).toString()));
		assertTrue(receiptString.contains("Sales Tax:"));
		assertTrue(receiptString.contains("Total: "));
	}
}
