package com.epam.webdriver.google.cloud.calculator.select.option;

import org.openqa.selenium.By;

public enum GraphicsCount {
	COUNT_1("1");

	private final String value;

	GraphicsCount(String value) {
		this.value = value;
	}

	public By by() {
		return By.xpath(
			"//div[@class='md-select-menu-container md-active md-clickable']/descendant::md-option[@value='" + value
				+ "']");
	}

	public String value() {
		return value;
	}
}
