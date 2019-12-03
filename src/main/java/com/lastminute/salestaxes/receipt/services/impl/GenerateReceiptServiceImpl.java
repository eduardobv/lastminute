package com.lastminute.salestaxes.receipt.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lastminute.salestaxes.receipt.beans.Item;
import com.lastminute.salestaxes.receipt.beans.Receipt;
import com.lastminute.salestaxes.receipt.exception.GenerateReceiptException;
import com.lastminute.salestaxes.receipt.services.IGenerateReceiptService;
import com.lastminute.salestaxes.receipt.util.Constants;
import com.lastminute.salestaxes.receipt.util.Properties;

/**
 * The Class GenerateReceiptServiceImpl implements the interface
 * IGenerateReceiptService. This Service generate the object Receipt after
 * processing the string input.
 */
@Service
public class GenerateReceiptServiceImpl implements IGenerateReceiptService {

	@Autowired
	Properties properties;

	private BigDecimal taxTotal = new BigDecimal(0);
	private BigDecimal sumPrices = new BigDecimal(0);

	/**
	 * Generate receipt according to the given format 
	 * '%quantity %description|%imported%description %price' and generate object Receipt.
	 *
	 * @param input the String with the data.
	 * @return the receipt object.
	 * @throws GenerateReceiptException the generate receipt exception
	 */
	@Override
	public Receipt generateReceipt(String input) throws GenerateReceiptException {

		List<String> sale = input.lines().collect(Collectors.toList());

		List<Item> items = new ArrayList<>();
		Receipt receipt = new Receipt();

		sale.forEach(s -> {
			Item item = new Item();
			item.setAmount(s.split(Constants.BLANK_STRING, 2)[0]);
			item.setPrice(s.split(Constants.TOKEN_STRING_AT)[1]);
			item.setImported(s.contains(Constants.IMPORTED_BLANK));
			item.setDescription(
					s.replace(Constants.IMPORTED_BLANK, Constants.EMPTY_STRING).split(Constants.TOKEN_STRING_AT)[0]
							.split(Constants.BLANK_STRING, 2)[1]);
			items.add(item);
		});

		items.forEach(item -> {
			BigDecimal price = new BigDecimal(item.getPrice());
			BigDecimal taxAmout = percent(price, getProductTax(item.getDescription()));
			BigDecimal taxAmoutRounded = round(taxAmout, RoundingMode.UP);
			BigDecimal taxImported;
			BigDecimal taxImportedRounded;
			if (item.isImported()) {
				taxImported = percent(price, properties.getTaxImported());
				taxImportedRounded = round(taxImported, RoundingMode.UP);
				taxAmoutRounded = taxAmoutRounded.add(taxImportedRounded);
			}

			BigDecimal newPrice = price.add(taxAmoutRounded);
			item.setPrice(newPrice.toString()); 
			taxTotal = taxTotal.add(taxAmoutRounded);	 
			sumPrices = sumPrices.add(newPrice); 
			
			
		});

		receipt.setItems(items);
		receipt.setSalestaxes(taxTotal.toString());
		receipt.setTotal(sumPrices.toString());
		taxTotal = new BigDecimal(0);
		sumPrices = new BigDecimal(0);
		return receipt;
	}

	public String getProductTax(String desc) {
		List<String> products = Arrays.asList(properties.getExemptProducts().split(","));
		Optional<String> product = products.stream().filter(s -> desc.contains(s)).findFirst();
		if (!product.isPresent()) {
			return properties.getTaxItem();
		} else {
			return Constants.CERO;
		}
	}

	public BigDecimal percent(BigDecimal price, String percent) {
		BigDecimal pct = new BigDecimal(percent);
		return price.multiply(pct.divide(Constants.ONE_HUNDRED));
	}

	public BigDecimal round(BigDecimal value, RoundingMode roundingMode) {
		BigDecimal increment = new BigDecimal(properties.getRounding());
		if (increment.signum() == 0) {
			return value;
		} else {
			BigDecimal divided = value.divide(increment, 0, roundingMode);
			return divided.multiply(increment);
		}
	}

}
