package com.lastminute.salestaxes.receipt.util;

import java.math.BigDecimal;

public class Constants {

	private Constants() {

	}

	// Key constants for used in the receipt ticket
	public static final String KEY_QUANTITY = "quantity";
	public static final String KEY_IMPORTED = "imported";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_PRICE = "price";
	public static final String KEY_TAXES = "taxes";
	public static final String KEY_TOTAL = "total";

	// Common constants used in the module
	public static final String EMPTY_STRING = "";
	public static final String BLANK_STRING = " ";
	public static final String TOKEN_STRING_AT = " at ";
	public static final String BLANK_IMPORTED_BLANK = " imported ";
	public static final String IMPORTED_BLANK = "imported ";
	
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	public static final String CERO = "0";

}
