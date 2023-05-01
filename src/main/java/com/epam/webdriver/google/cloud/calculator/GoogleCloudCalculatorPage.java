package com.epam.webdriver.google.cloud.calculator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudCalculatorPage extends GoogleCloudCalculatorFrame {

	@FindBy(id = "tab-item-1")
	private WebElement computeEngineTab;

	private final ComputeEngineConfigurator computeEngineConfigurator;
	private final Estimations estimations;

	public GoogleCloudCalculatorPage(WebDriver webDriver) {
		super(webDriver);
		computeEngineConfigurator = new ComputeEngineConfigurator(webDriver);
		estimations = new Estimations(webDriver);
	}

	public ComputeEngineConfigurator switchToComputeEngineConfigurator() {
		clickElement(computeEngineTab);
		waitForElement(computeEngineConfigurator.getLocator());
		return computeEngineConfigurator;
	}

	public Estimations toEstimations() {
		waitForElement(estimations.getLocator());
		return estimations;
	}

	@Override
	protected void init(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
}
