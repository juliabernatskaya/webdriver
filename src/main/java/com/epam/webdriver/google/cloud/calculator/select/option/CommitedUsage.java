package com.epam.webdriver.google.cloud.calculator.select.option;

import org.openqa.selenium.By;

public enum CommitedUsage {
	ONE_YEAR("1 Year");

	private final String value;

	CommitedUsage(String value) {
		this.value = value;
	}

	public By by() {
		return By.xpath("//div[@aria-hidden='false']//md-option/div[contains(text(), '" + value + "')]");
	}

	public String value() {
		return value;
	}
}
