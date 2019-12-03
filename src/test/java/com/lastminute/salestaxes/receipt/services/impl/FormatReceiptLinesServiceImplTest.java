package com.lastminute.salestaxes.receipt.services.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lastminute.salestaxes.receipt.beans.Item;
import com.lastminute.salestaxes.receipt.beans.Receipt;
import com.lastminute.salestaxes.receipt.exception.FormatReceiptException;
import com.lastminute.salestaxes.receipt.services.IFormatReceiptLinesService;

@SpringBootTest 
public class FormatReceiptLinesServiceImplTest {

	@Autowired
	IFormatReceiptLinesService formatReceiptLinesService;

	Logger log = LoggerFactory.getLogger(FormatReceiptLinesServiceImplTest.class);

	@Test
	public void formatReceiptLinesTest() {

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
		List<String> sale = output.lines().collect(Collectors.toList());

		try {

			List<String> list = formatReceiptLinesService.formatReceiptLines(receipt);
			assertThat(list, is(sale));

		} catch (FormatReceiptException e) {
			log.error("Testing Error FormatReceiptException {0}", e);
		} catch (Exception e) {
			log.error("Testing Error Exception {0}", e);
		}

	}

}
