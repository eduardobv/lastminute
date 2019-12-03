package com.lastminute.salestaxes.receipt.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lastminute.salestaxes.receipt.beans.Item;
import com.lastminute.salestaxes.receipt.beans.Receipt;
import com.lastminute.salestaxes.receipt.exception.GenerateReceiptException;
import com.lastminute.salestaxes.receipt.services.IGenerateReceiptService;

@SpringBootTest 
public class GenerateReceiptServiceImplTest {

	@Autowired
	IGenerateReceiptService generateReceiptService;

	Logger log = LoggerFactory.getLogger(FormatReceiptLinesServiceImplTest.class);

	@Test
	public void generateReceiptTest() {

		String input = "1 book at 12.49\r\n1 music CD at 14.99\r\n1 chocolate bar at 0.85";

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

		try {

			Receipt receiptTest = generateReceiptService.generateReceipt(input); 
			assertThat(receipt).usingRecursiveComparison().isEqualTo(receiptTest);

		} catch (GenerateReceiptException e) {
			log.error("Testing Error GenerateReceiptException {0}", e);
		} catch (Exception e) {
			log.error("Testing Error Exception {0}", e);
		}
	}

}
