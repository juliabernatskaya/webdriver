package com.epam.webdriver.pastebin.home.select.option;

import org.openqa.selenium.By;

public enum Syntax {
	BASH("Bash");

	private final String value;

	Syntax(String value) {
		this.value = value;
	}

	public By by() {
		return By.xpath("//li[@role='option' and text()='" + value + "']");
	}

	public String value() {
		return value;
	}
}
