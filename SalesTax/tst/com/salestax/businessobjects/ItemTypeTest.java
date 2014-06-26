package com.salestax.businessobjects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ItemTypeTest {

	@Test
	public void testFoodTaxable() {
		assertFalse(ItemType.FOOD.getIsSalesTaxable());
	}

	@Test
	public void testMedicineTaxable() {
		assertFalse(ItemType.MEDICINE.getIsSalesTaxable());
	}

	@Test
	public void testBookTaxable() {
		assertFalse(ItemType.BOOK.getIsSalesTaxable());
	}

	@Test
	public void testPerfumeTaxable() {
		assertTrue(ItemType.PERFUME.getIsSalesTaxable());
	}

	@Test
	public void testOtherItemsTaxable() {
		assertTrue(ItemType.OTHER.getIsSalesTaxable());
	}
}
