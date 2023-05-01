package com.epam.webdriver.pastebin.home.select.option;

import org.openqa.selenium.By;

public enum Expiration {
	TEN_MINUTES("10 Minutes");

	private final String value;

	Expiration(String value) {
		this.value = value;
	}

	public By by() {
		return By.xpath("//li[@role='option' and text()='" + value + "']");
	}

	public String value() {
		return value;
	}
}
