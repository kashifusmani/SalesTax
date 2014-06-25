package com.salestax.businesslogic;

import java.math.BigDecimal;

import com.salestax.businessobjects.Item;
import com.salestax.util.ValidationHelper;

/**
 * This class is responsible for maintaining tax percentage amounts. 
 * It provides API to calculate tax on an Item.
 * @author kashifu
 *
 */
public class TaxManager {
	private final BigDecimal SALES_TAX_RATE = new BigDecimal(".10");
	private final BigDecimal IMPORT_DUTY_RATE = new BigDecimal("0.05");
	private final BigDecimal TAX_ROUND_UP_FACTOR = new BigDecimal("0.05");
	
	// The value above can be inject via constructor for cases , say , different province have different tax rate
	// So each can have its own TaxManager
	
	public BigDecimal calculateTax(Item item) {
		ValidationHelper.validateForNull(item, "item");
		BigDecimal totalTax = new BigDecimal("0.0");
		
		if (item.getItemType().getIsSalesTaxable()) {
			totalTax.add(calculateSalesTax(item));
		}
		
		if (item.getItemOrigin().getIsImportDutyTaxable()) {
			totalTax.add(calculateImportDuty(item));
		}
		
		return roundUptoNearestPointFive(totalTax);
	}
	
	private BigDecimal calculateSalesTax(Item item) {
		return item.getItemPrice().getUnitValue().multiply(SALES_TAX_RATE);
	}
	
	private BigDecimal calculateImportDuty(Item item) {
		return item.getItemPrice().getUnitValue().multiply(IMPORT_DUTY_RATE);
	}
	
	private BigDecimal roundUptoNearestPointFive(BigDecimal baseValue) {
		 BigDecimal ceiledValue = new BigDecimal(Math.ceil(baseValue.divide(TAX_ROUND_UP_FACTOR).doubleValue()));
		 return ceiledValue.multiply(TAX_ROUND_UP_FACTOR);
	}
	
}
