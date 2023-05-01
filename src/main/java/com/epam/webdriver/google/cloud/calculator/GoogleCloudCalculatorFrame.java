package com.epam.webdriver.google.cloud.calculator;

import com.epam.webdriver.common.PageObject;
import java.util.List;
import java.util.function.Supplier;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Provides overridden {@link PageObject} methods to be called on an objects of Google Cloud Pricing Calculator page.
 * All Google Cloud Pricing Calculator related page objects should extend this abstract class.
 */
public abstract class GoogleCloudCalculatorFrame extends PageObject {

	private static final String PARENT_FRAME_XPATH = "//article[@id='cloud-site']/descendant::iframe";
	private static final String NESTED_FRAME_ID = "myFrame";

	protected GoogleCloudCalculatorFrame(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	protected void clickElement(WebElement webElement) {
		performInFrame(() -> super.clickElement(webElement));
	}

	@Override
	protected void clickElement(By by) {
		performInFrame(() -> super.clickElement(by));
	}

	@Override
	protected void fillForm(WebElement form, String text) {
		performInFrame(() -> super.fillForm(form, text));
	}

	@Override
	protected void selectOption(WebElement select, By option) {
		super.selectOption(select, option);
	}

	@Override
	protected String getElementText(WebElement webElement) {
		return getFromFrame(() -> super.getElementText(webElement));
	}

	@Override
	protected String getElementsText(List<WebElement> webElements) {
		return getFromFrame(() -> super.getElementsText(webElements));
	}

	@Override
	protected void waitForElement(By by) {
		performInFrame(() -> super.waitForElement(by));
	}

	protected void performInFrame(Runnable runnable) {
		switchToFrame();
		runnable.run();
		webDriver.switchTo().defaultContent();
	}

	protected <V> V getFromFrame(Supplier<V> supplier) {
		switchToFrame();
		V result = supplier.get();
		webDriver.switchTo().defaultContent();
		return result;
	}

	private void switchToFrame() {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(PARENT_FRAME_XPATH)))
			.switchTo().frame(NESTED_FRAME_ID);
	}
}
