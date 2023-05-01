package com.epam.webdriver.common;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageObject {
	protected static final int DEFAULT_WAIT_DURATION_SEC = 15;

	protected final WebDriver webDriver;
	protected final WebDriverWait wait;

	protected PageObject(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_WAIT_DURATION_SEC));
		init(webDriver);
	}

	protected void clickElement(WebElement webElement) {
		wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
	}

	protected void clickElement(By by) {
		wait.until(ExpectedConditions.elementToBeClickable(by)).click();
	}

	protected void fillForm(WebElement form, String text) {
		wait.until(ExpectedConditions.visibilityOf(form)).sendKeys(text);
	}

	protected void selectOption(WebElement select, By option) {
		clickElement(select);
		clickElement(option);
	}

	protected abstract void init(WebDriver webDriver);
}
