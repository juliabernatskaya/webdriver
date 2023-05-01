package com.epam.webdriver.google.cloud.calculator.select.option;

import org.openqa.selenium.By;

public enum MachineType {
	N1_STANDARD_8("n1-standard-8");

	private final String value;

	MachineType(String value) {
		this.value = value;
	}

	public By by() {
		return By.xpath("//div[@aria-hidden='false']//md-option/div[contains(text(), '" + value + "')]");
	}

	public String value() {
		return value;
	}
}
