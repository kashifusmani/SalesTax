package com.salestax.businessobjects;

import java.math.BigDecimal;

import com.salestax.util.ValidationHelper;

/**
 * This class is responsible to represent an Price Object. For example 10 USD or
 * 20 Euro
 * 
 * @author kashifu
 * 
 */
public class Price {
	private BigDecimal unitValue;
	private Currency currency;

	public Price(BigDecimal unitValue, Currency currency) {
		ValidationHelper.validateForNull(unitValue, "unitValue");
		ValidationHelper.validateForNull(currency, "currency");
		this.unitValue = unitValue;
		this.currency = currency;
	}

	public BigDecimal getUnitValue() {
		return this.unitValue;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Price [unitValue=");
		builder.append(unitValue);
		builder.append(", currency=");
		builder.append(currency);
		builder.append("]");
		return builder.toString();
	}

}
