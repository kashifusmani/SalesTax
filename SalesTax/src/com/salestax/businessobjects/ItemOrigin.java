package com.salestax.businessobjects;

/** 
 * This class is responsible for maintaining an Item's Origin and if it is duty taxable or not.
 * @author kashifu
 *
 */
public enum ItemOrigin {
	IMPORTED(true), NON_IMPORTED(false);
	private boolean isImportDutyTaxable;

	private ItemOrigin(boolean isImportDutyTaxable) {
		this.isImportDutyTaxable = isImportDutyTaxable;
	}

	public boolean getIsImportDutyTaxable() {
		return this.isImportDutyTaxable;
	}

}
