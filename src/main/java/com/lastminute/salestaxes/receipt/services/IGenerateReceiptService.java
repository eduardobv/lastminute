package com.lastminute.salestaxes.receipt.services;

import com.lastminute.salestaxes.receipt.beans.Receipt;
import com.lastminute.salestaxes.receipt.exception.GenerateReceiptException;

public interface IGenerateReceiptService {

	public Receipt generateReceipt(String input) throws GenerateReceiptException;

}
