package com.salestax.businesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.salestax.businessobjects.Currency;
import com.salestax.businessobjects.Item;
import com.salestax.businessobjects.ItemOrigin;
import com.salestax.businessobjects.ItemType;
import com.salestax.businessobjects.Price;

/** Utility class with some test data
 * 
 */
public class ReceiptPrintTest {
	public static void main(String[] args) {
		TaxManager taxManager = new TaxManager();
		ReceiptCalculator receiptCalculator = new ReceiptCalculator(taxManager);
		ReceiptPrinter receiptPrinter = new ReceiptPrinter(receiptCalculator);
		printReceiptOne(receiptPrinter);
		System.out.println("--------------------------------");
		printReceiptTwo(receiptPrinter);
		System.out.println("--------------------------------");
		printReceiptThree(receiptPrinter);
	}
	
	private static void printReceiptOne(ReceiptPrinter receiptPrinter) {
		Item itemOne = new Item(ItemOrigin.NON_IMPORTED, ItemType.BOOK,
				new Price(new BigDecimal("12.49"), Currency.USD), "book");
		Item itemTwo = new Item(ItemOrigin.NON_IMPORTED, ItemType.MUSIC_CD, 
				new Price(new BigDecimal("14.99"),	Currency.USD), "music CD");
		Item itemThree = new Item(ItemOrigin.NON_IMPORTED, ItemType.FOOD, 
				new Price(new BigDecimal("0.85"), Currency.USD), "chocolate bar");
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(itemOne);
		itemList.add(itemTwo);
		itemList.add(itemThree);
		
		String receipt = receiptPrinter.printReceipt(itemList);
		System.out.println(receipt);
	}
	
	private static void printReceiptTwo(ReceiptPrinter receiptPrinter) {
		Item itemOne = new Item(ItemOrigin.IMPORTED, ItemType.FOOD,
				new Price(new BigDecimal("10.00"), Currency.USD), "box of chocolate");
		Item itemTwo = new Item(ItemOrigin.IMPORTED, ItemType.PERFUME, 
				new Price(new BigDecimal("47.50"),	Currency.USD), "bottle of perfume");

		List<Item> itemList = new ArrayList<Item>();
		itemList.add(itemOne);
		itemList.add(itemTwo);
		
		String receipt = receiptPrinter.printReceipt(itemList);
		System.out.println(receipt);
	}
	
	private static void printReceiptThree(ReceiptPrinter receiptPrinter) {
		Item itemOne = new Item(ItemOrigin.IMPORTED, ItemType.PERFUME,
				new Price(new BigDecimal("27.99"), Currency.USD), "bottle of perfume");
		Item itemTwo = new Item(ItemOrigin.NON_IMPORTED, ItemType.PERFUME, 
				new Price(new BigDecimal("18.99"),	Currency.USD), "bottle of perfume");
		Item itemThree = new Item(ItemOrigin.NON_IMPORTED, ItemType.MEDICINE, 
				new Price(new BigDecimal("9.75"), Currency.USD), "packet of headache pills");
		Item itemFour = new Item(ItemOrigin.IMPORTED, ItemType.FOOD, 
				new Price(new BigDecimal("11.25"), Currency.USD), "box of chocolates");
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(itemOne);
		itemList.add(itemTwo);
		itemList.add(itemThree);
		itemList.add(itemFour);
		
		String receipt = receiptPrinter.printReceipt(itemList);
		System.out.println(receipt);
	}
	

	
	
}