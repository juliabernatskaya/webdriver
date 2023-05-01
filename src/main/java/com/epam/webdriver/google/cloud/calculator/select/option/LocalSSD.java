package com.epam.webdriver.google.cloud.calculator.select.option;

import org.openqa.selenium.By;

public enum LocalSSD {
	GB_2_X_375("2x375");

	private final String value;

	LocalSSD(String value) {
		this.value = value;
	}

	public By by() {
		return By.xpath("//div[@aria-hidden='false']//md-option/div[contains(text(), '" + value + "')]");
	}

	public String value() {
		return value;
	}
}
