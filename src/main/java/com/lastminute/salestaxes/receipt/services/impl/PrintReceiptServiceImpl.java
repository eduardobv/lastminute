package com.lastminute.salestaxes.receipt.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lastminute.salestaxes.receipt.beans.Receipt;
import com.lastminute.salestaxes.receipt.exception.FormatReceiptException;
import com.lastminute.salestaxes.receipt.services.IFormatReceiptLinesService;
import com.lastminute.salestaxes.receipt.services.IPrintReceiptService;

@Service
public class PrintReceiptServiceImpl implements IPrintReceiptService {

	@Autowired
	IFormatReceiptLinesService formatReceiptLinesService;

	Logger log = LoggerFactory.getLogger(PrintReceiptServiceImpl.class);

	@Override
	public void printReceipt(Receipt receipt) {
		try {
			var lines = formatReceiptLinesService.formatReceiptLines(receipt);
			lines.forEach(System.out::println);
		} catch (FormatReceiptException e) {
			log.error("FormatReceiptException in printReceipt: {0}", e);
		} catch (Exception e) {
			log.error("Exception in Service printReceipt: {0}", e);
		}
	}

}
