package com.epam.webdriver.google.cloud.calculator.select.option;

import org.openqa.selenium.By;

public enum Series {
	N1("N1");

	private final String value;

	Series(String value) {
		this.value = value;
	}

	public By by() {
		return By.xpath("//div[@aria-hidden='false']//md-option/div[contains(text(), '" + value + "')]");
	}

	public String value() {
		return value;
	}
}
