package com.lastminute.salestaxes.receipt.beans;

import java.util.List;

public class Receipt {

	private List<Item> items;
	private String salestaxes;
	private String total;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getSalestaxes() {
		return salestaxes;
	}

	public void setSalestaxes(String salestaxes) {
		this.salestaxes = salestaxes;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
