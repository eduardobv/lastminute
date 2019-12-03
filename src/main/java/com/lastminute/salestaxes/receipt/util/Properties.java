package com.lastminute.salestaxes.receipt.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sales.properties")
public class Properties {

	@Value("${salestaxes.receipt.itemline}")
	private String itemline;

	@Value("${salestaxes.receipt.taxesline}")
	private String taxesline;

	@Value("${salestaxes.receipt.totalline}")
	private String totalline;

	@Value("${salestaxes.tax.exempt.products}")
	private String exemptProducts;

	@Value("${salestaxes.tax.percent.product}")
	private String taxItem;

	@Value("${salestaxes.tax.percent.imported}")
	private String taxImported;

	@Value("${salestaxes.rounding.rules}")
	private String rounding;

	public String getItemline() {
		return itemline;
	}

	public String getTaxesline() {
		return taxesline;
	}

	public void setTaxesline(String taxesline) {
		this.taxesline = taxesline;
	}

	public String getTotalline() {
		return totalline;
	}

	public void setTotalline(String totalline) {
		this.totalline = totalline;
	}

	public String getExemptProducts() {
		return exemptProducts;
	}

	public void setExemptProducts(String exemptProducts) {
		this.exemptProducts = exemptProducts;
	}

	public String getTaxItem() {
		return taxItem;
	}

	public void setTaxItem(String taxItem) {
		this.taxItem = taxItem;
	}

	public String getTaxImported() {
		return taxImported;
	}

	public void setTaxImported(String taxImported) {
		this.taxImported = taxImported;
	}

	public String getRounding() {
		return rounding;
	}

	public void setRounding(String rounding) {
		this.rounding = rounding;
	}

	public void setItemline(String itemline) {
		this.itemline = itemline;
	}

}
