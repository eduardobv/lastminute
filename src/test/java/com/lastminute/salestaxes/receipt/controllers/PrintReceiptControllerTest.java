package com.lastminute.salestaxes.receipt.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class PrintReceiptControllerTest {

	@Autowired
	PrintReceiptController receiptController;

	@ParameterizedTest
	@CsvSource({
			"'1 book at 12.49\r\n1 music CD at 14.99\r\n1 chocolate bar at 0.85' , '1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83\n'",
			"'1 imported box of chocolates at 10.00\r\n1 imported bottle of perfume at 47.50' , '1 imported box of chocolates: 10.50\n1 imported bottle of perfume: 54.65\nSales Taxes: 7.65\nTotal: 65.15\n'",
			"'1 imported bottle of perfume at 27.99\r\n1 bottle of perfume at 18.99\r\n1 packet of headache pills at 9.75\r\n1 box of imported chocolates at 11.25', '1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 imported box of chocolates: 11.85\nSales Taxes: 6.70\nTotal: 74.68\n'" })
	public void test(String input, String output) {
		
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		final PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		receiptController.printReceipt(input);
		assertThat(output, is(outContent.toString()));

		System.setOut(originalOut);
		
	}

}
