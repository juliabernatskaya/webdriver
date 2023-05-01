package com.epam.webdriver.google.cloud.calculator.select.option;

import org.openqa.selenium.By;

public enum OperatingSystem {
	FREE("Free");

	private final String value;

	OperatingSystem(String value) {
		this.value = value;
	}

	public By by() {
		return By.xpath("//div[@aria-hidden='false']//md-option/div[contains(text(), '" + value + "')]");
	}

	public String value() {
		return value;
	}
}
