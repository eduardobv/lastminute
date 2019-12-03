package com.lastminute.salestaxes.receipt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lastminute.salestaxes.receipt.exception.GenerateReceiptException;
import com.lastminute.salestaxes.receipt.services.IGenerateReceiptService;
import com.lastminute.salestaxes.receipt.services.IPrintReceiptService;

/**
 * The PrintReceiptController is responsible for invoking the necessary services
 * to print the payment receipt.
 */
@Controller
public class PrintReceiptController {

	@Autowired
	IPrintReceiptService printReceiptService;

	@Autowired
	IGenerateReceiptService generateReceiptService;

	Logger log = LoggerFactory.getLogger(PrintReceiptController.class);

	/**
	 * Prints the receipt invoking two services IPrintReceiptService and
	 * IGenerateReceiptService.
	 *
	 * @param input String that contents the input with all the detail of: quantity,
	 *              description, imported and price.
	 */
	public void printReceipt(String input) {
		try {
			printReceiptService.printReceipt(generateReceiptService.generateReceipt(input));
		} catch (GenerateReceiptException e) {
			log.error("GenerateReceiptException in printReceipt: {0}", e);
		} catch (Exception e) {
			log.error("Exception in Controller printReceipt: {0}", e);
		}
	}

}
