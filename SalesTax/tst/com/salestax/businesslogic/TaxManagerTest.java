package com.salestax.businesslogic;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.salestax.businessobjects.Currency;
import com.salestax.businessobjects.Item;
import com.salestax.businessobjects.ItemOrigin;
import com.salestax.businessobjects.ItemReceiptEntry;
import com.salestax.businessobjects.ItemType;
import com.salestax.businessobjects.Price;
public class TaxManagerTest {
	private TaxManager taxManager = new TaxManager();
	
	@Test(expected = IllegalArgumentException.class) 
	public void testCalculateTaxNullItem() {
		taxManager.calculateTax(null);
	}
	
	//This test will test for a non imported and non taxable item
	@Test
	public void testCalculateTaxForNonImportedNontaxableItem() {
		BigDecimal unitValue = new BigDecimal("10.05");
		Price price = new Price(unitValue, Currency.USD);
		String description = "Harry Potter";
		Item item = new Item(ItemOrigin.NON_IMPORTED, ItemType.BOOK, price, description);
		ItemReceiptEntry receiptEntry = taxManager.calculateTax(item);
		BigDecimal expectedTax =  new BigDecimal("0.00");
		assertEquals(receiptEntry.getSalesTax(), expectedTax);
		assertEquals(receiptEntry.getItemTotalPrice(), unitValue);

	}
	
	//This test will test for a non imported and taxable item
	@Test
	public void testCalculateTaxForNonImportedTaxableItem() {
		BigDecimal unitValue = new BigDecimal("44.4");
		Price price = new Price(unitValue, Currency.USD);
		String description = "bottle of perfume";
		Item item = new Item(ItemOrigin.NON_IMPORTED, ItemType.PERFUME, price, description);
		ItemReceiptEntry receiptEntry = taxManager.calculateTax(item);
		
		BigDecimal expectedTax = new BigDecimal("4.45");
		assertEquals(receiptEntry.getSalesTax(), expectedTax);
		assertEquals(receiptEntry.getItemTotalPrice(), unitValue.add(expectedTax));		
	}
	
	//This test will test for an imported and non taxable item
	@Test
	public void testCalculateTaxForImportedNonTaxableItem() {
		BigDecimal unitValue = new BigDecimal("11.25");
		Price price = new Price(unitValue, Currency.USD);
		String description = "box of chocolates";
		Item item = new Item(ItemOrigin.IMPORTED, ItemType.FOOD, price, description);
		ItemReceiptEntry receiptEntry = taxManager.calculateTax(item);
		
		BigDecimal expectedTax = new BigDecimal("0.60"); // 5% of 11.25, then rounded up to nearest 0.05
		assertEquals(receiptEntry.getSalesTax(), expectedTax);
		assertEquals(receiptEntry.getItemTotalPrice(), unitValue.add(expectedTax));	
	}
	
	//This test will test for an imported and taxable item
	@Test
	public void testCalculateTaxForImportedTaxableItem() {
		BigDecimal unitValue = new BigDecimal("47.50");
		Price price = new Price(unitValue, Currency.USD);
		String description = "bottle of perfume";
		Item item = new Item(ItemOrigin.IMPORTED, ItemType.PERFUME, price, description);
		ItemReceiptEntry receiptEntry = taxManager.calculateTax(item);
		
		BigDecimal expectedTax = new BigDecimal("7.15"); // 15% of 47.50, then rounded up to nearest 0.05
		assertEquals(receiptEntry.getSalesTax(), expectedTax);
		assertEquals(receiptEntry.getItemTotalPrice(), unitValue.add(expectedTax));	
	}
	
	@Test
	public void testRoundUptoNearestPointFive() {
		assertEquals(taxManager.roundUptoNearestPointFive(new BigDecimal("0.125")), new BigDecimal("0.15"));
		assertEquals(taxManager.roundUptoNearestPointFive(new BigDecimal("0.19")), new BigDecimal("0.20"));
		assertEquals(taxManager.roundUptoNearestPointFive(new BigDecimal("0.99")), new BigDecimal("1.00"));
	}
}
