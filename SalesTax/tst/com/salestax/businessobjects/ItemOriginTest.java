package com.salestax.businessobjects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ItemOriginTest {

	@Test
	public void testImportedItemOriginIstaxable() {
		assertTrue(ItemOrigin.IMPORTED.getIsImportDutyTaxable());
	}
	
	@Test
	public void testNonImportedItemOriginIsNottaxable() {
		assertFalse(ItemOrigin.NON_IMPORTED.getIsImportDutyTaxable());
	}
}
