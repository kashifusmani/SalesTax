package com.salestax.businessobjects;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class PriceTest {

	@Test(expected = IllegalArgumentException.class)
	public void testPriceContructorNullValue() {
		new Price(null, Currency.USD);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPriceConstructorNullCurrency() {
		new Price(new BigDecimal("10"), null);
	}

	@Test
	public void testPriceGetters() {
		BigDecimal unitValue = new BigDecimal("10");
		Price price = new Price(unitValue, Currency.USD);
		assertEquals(price.getUnitValue(), unitValue);
		assertEquals(price.getCurrency(), Currency.USD);
	}
}
