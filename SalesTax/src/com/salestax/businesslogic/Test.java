package com.salestax.businesslogic;

import java.math.BigDecimal;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 BigDecimal bd = new BigDecimal("300000.19");
		 BigDecimal factor = new BigDecimal("0.05");
		 BigDecimal another = new BigDecimal(0.169);
		 /*
		 System.out.println(another.setScale(2, BigDecimal.));
		// System.out.println(Math.ceil(bd.doubleValue()/0.05) * 0.05);
		 
*/		 BigDecimal ceiled = new BigDecimal(Math.ceil(bd.divide(factor).doubleValue()));
		 System.out.println(ceiled);
		 System.out.println(ceiled.multiply(new BigDecimal("0.05")));
	}

}
