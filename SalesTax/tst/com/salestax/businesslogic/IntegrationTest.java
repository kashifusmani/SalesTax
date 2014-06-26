package com.salestax.businesslogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.salestax.businessobjects.Currency;
import com.salestax.businessobjects.Item;
import com.salestax.businessobjects.ItemOrigin;
import com.salestax.businessobjects.ItemType;
import com.salestax.businessobjects.Price;
import com.salestax.businessobjects.Receipt;

public class IntegrationTest {
	private TaxManager taxManager = new TaxManager();
	private ReceiptCalculator receiptCalculator = new ReceiptCalculator(taxManager);
	private ReceiptPrinter receiptPrinter = new ReceiptPrinter(receiptCalculator);

	@Test
	public void testPrintReceiptCaseOne() {
		String descriptionBottleOfPerfume = "bottle of perfume";
		String medicineDescription = "medicine";
		String foodDescription = "food";
		Item itemOne = new Item(ItemOrigin.IMPORTED, ItemType.PERFUME,
				new Price(new BigDecimal("27.99"), Currency.USD), descriptionBottleOfPerfume);
		Item itemTwo = new Item(ItemOrigin.NON_IMPORTED, ItemType.PERFUME, 
				new Price(new BigDecimal("18.99"),	Currency.USD), descriptionBottleOfPerfume);
		Item itemThree = new Item(ItemOrigin.NON_IMPORTED, ItemType.MEDICINE, 
				new Price(new BigDecimal("9.75"), Currency.USD), medicineDescription);
		Item itemFour = new Item(ItemOrigin.IMPORTED, ItemType.FOOD, 
				new Price(new BigDecimal("11.25"), Currency.USD), foodDescription);

		// Total sales tax on these items should be 4.20 + 1.90 + 0 + 0.60 =
		// 6.70
		BigDecimal totalExpectedSalesTax = new BigDecimal("6.70");
		// Total cost of receipt should be 32.19 + 20.89 + 9.75 + 11.85 = 74.68
		BigDecimal itemOneTotalPrice = new BigDecimal("32.19");
		BigDecimal itemTwoTotalPrice = new BigDecimal("20.89");
		BigDecimal itemThreeTotalPrice = new BigDecimal("9.75");
		BigDecimal itemFourTotalPrice = new BigDecimal("11.85");

		BigDecimal totalExpectedReceiptPrice = new BigDecimal("74.68");
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(itemOne);
		itemList.add(itemTwo);
		itemList.add(itemThree);
		itemList.add(itemFour);

		Receipt receipt = receiptCalculator.calculateReceipt(itemList);
		assertEquals(receipt.getReceiptTotalPrice(), totalExpectedReceiptPrice);
		assertEquals(receipt.getTotalSalesTax(), totalExpectedSalesTax);

		String receiptString = receiptPrinter.printReceipt(itemList);

		assertTrue(receiptString.contains(descriptionBottleOfPerfume));
		assertTrue(receiptString.contains(medicineDescription));
		assertTrue(receiptString.contains(foodDescription));

		assertTrue(receiptString.contains(totalExpectedSalesTax.toString()));
		assertTrue(receiptString.contains(itemOneTotalPrice.toString()));
		assertTrue(receiptString.contains(itemTwoTotalPrice.toString()));
		assertTrue(receiptString.contains(itemThreeTotalPrice.toString()));
		assertTrue(receiptString.contains(itemFourTotalPrice.toString()));
		assertTrue(receiptString.contains(totalExpectedReceiptPrice.toString()));
	}

}
