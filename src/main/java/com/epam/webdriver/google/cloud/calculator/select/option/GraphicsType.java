package com.epam.webdriver.google.cloud.calculator.select.option;

import org.openqa.selenium.By;

public enum GraphicsType {
	NVIDIA_TESLA_V100("NVIDIA Tesla V100");

	private final String value;

	GraphicsType(String value) {
		this.value = value;
	}

	public By by() {
		return By.xpath("//md-option/div[contains(text(), '" + value + "')]");
	}

	public String value() {
		return value;
	}
}
