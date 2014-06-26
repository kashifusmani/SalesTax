package com.salestax.businessobjects;

/**
 * This class is responsible for maintaining an Item type and if it is sales taxable or not.
 * @author kashifu
 *
 */
public enum ItemType {
	FOOD(false), MEDICINE(false), BOOK(false), PERFUME(true), MUSIC_CD(true), OTHER(true);

	private boolean isSalesTaxable;

	private ItemType(boolean isSalesTaxable) {
		this.isSalesTaxable = isSalesTaxable;
	}

	public boolean getIsSalesTaxable() {
		return isSalesTaxable;
	}
}
