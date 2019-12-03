package com.lastminute.salestaxes.receipt.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lastminute.salestaxes.receipt.beans.Receipt;
import com.lastminute.salestaxes.receipt.exception.FormatReceiptException;
import com.lastminute.salestaxes.receipt.services.IFormatReceiptLinesService;
import com.lastminute.salestaxes.receipt.util.Constants;
import com.lastminute.salestaxes.receipt.util.Properties;

/**
 * The Class FormatReceiptLinesServiceImpl implements the interface
 * IFormatReceiptLinesService. This Service convert a Receipt object into a List
 * of lines
 */
@Service
public class FormatReceiptLinesServiceImpl implements IFormatReceiptLinesService {

	@Autowired
	Properties properties;

	/**
	 * This method read from a properties (It could be obtained from xml, database
	 * or another way) the format of each line of the Receipt and create a list of
	 * lines formatted with the given pattern.
	 *
	 * @param receipt the receipt
	 * @return List with each line of the receipt ready to be printed.
	 * @throws FormatReceiptException the format receipt exception
	 */
	@Override
	public List<String> formatReceiptLines(Receipt receipt) throws FormatReceiptException {

		List<String> lines = new ArrayList<>();

		receipt.getItems().forEach(item -> {
			var itemData = new HashMap<String, String>();
			itemData.put(Constants.KEY_QUANTITY, item.getAmount());
			if (item.isImported()) {
				itemData.put(Constants.KEY_IMPORTED, Constants.BLANK_IMPORTED_BLANK);
			} else {
				itemData.put(Constants.KEY_IMPORTED, Constants.BLANK_STRING);
			}
			itemData.put(Constants.KEY_DESCRIPTION, item.getDescription());
			itemData.put(Constants.KEY_PRICE, item.getPrice());
			String itemline = StrSubstitutor.replace(properties.getItemline(), itemData); 
			lines.add(itemline);
		});

		var taxesData = new HashMap<String, String>();
		taxesData.put(Constants.KEY_TAXES, receipt.getSalestaxes());
		var salesTaxes = StrSubstitutor.replace(properties.getTaxesline(), taxesData); 
		lines.add(salesTaxes);

		var totalData = new HashMap<String, String>();
		totalData.put(Constants.KEY_TOTAL, receipt.getTotal());
		var total = StrSubstitutor.replace(properties.getTotalline(), totalData); 
		lines.add(total);
 
		return lines;

	}

}
