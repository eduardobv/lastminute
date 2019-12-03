package com.lastminute.salestaxes.receipt.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lastminute.salestaxes.receipt.beans.Item;
import com.lastminute.salestaxes.receipt.beans.Receipt;
import com.lastminute.salestaxes.receipt.services.IPrintReceiptService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class PrintReceiptServiceImplTest {

	@Autowired
	IPrintReceiptService printReceiptService;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@Test
	public void printReceipt() {

		Receipt receipt = new Receipt();
		List<Item> items = new ArrayList<>();

		Item item1 = new Item();
		item1.setAmount("1");
		item1.setImported(false);
		item1.setDescription("book");
		item1.setPrice("12.49");
		items.add(item1);

		Item item2 = new Item();
		item2.setAmount("1");
		item2.setImported(false);
		item2.setDescription("music CD");
		item2.setPrice("16.49");
		items.add(item2);

		Item item3 = new Item();
		item3.setAmount("1");
		item3.setImported(false);
		item3.setDescription("chocolate bar");
		item3.setPrice("0.85");
		items.add(item3);

		receipt.setItems(items);
		receipt.setSalestaxes("1.50");
		receipt.setTotal("29.83");

		String output = "1 book: 12.49\r\n" + "1 music CD: 16.49\r\n" + "1 chocolate bar: 0.85\r\n"
				+ "Sales Taxes: 1.50\r\n" + "Total: 29.83\r\n";

		printReceiptService.printReceipt(receipt);
		assertEquals(output, outContent.toString());
		System.setOut(originalOut);
	}

	@BeforeAll
	public void init() {
		System.setOut(new PrintStream(outContent));
	}

}
