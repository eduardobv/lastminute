package com.lastminute.salestaxes.receipt.services;

import java.util.List;

import com.lastminute.salestaxes.receipt.beans.Receipt;
import com.lastminute.salestaxes.receipt.exception.FormatReceiptException;

public interface IFormatReceiptLinesService {

	public List<String> formatReceiptLines(Receipt receipt) throws FormatReceiptException;

}
