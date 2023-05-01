package com.epam.webdriver.google.cloud.calculator;

import com.epam.webdriver.common.Locatable;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComputeEngineEstimation extends GoogleCloudCalculatorFrame implements Locatable {

	private static final By LOCATOR = By.id("compute");
	private static final String PROVISIONING_MODEL = "Provisioning model";
	private static final String INSTANCE_TYPE = "Instance type";
	private static final String REGION = "Region";
	private static final String LOCAL_SSD = "Local SSD";
	private static final String COMMITMENT_TERM = "Commitment term";

	@FindBy(xpath = "//*[@id='compute']/descendant::div[contains(text(), '" + PROVISIONING_MODEL + "')]")
	private WebElement provisioningModelRow;
	@FindBy(xpath = "//*[@id='compute']/descendant::div[contains(text(), '" + INSTANCE_TYPE + "')]")
	private WebElement instanceTypeRow;
	@FindBy(xpath = "//*[@id='compute']/descendant::div[contains(text(), '" + REGION + "')]")
	private WebElement regionRow;
	@FindBy(xpath = "//*[@id='compute']/descendant::div[contains(text(), '" + LOCAL_SSD + "')]")
	private WebElement localSSDRow;
	@FindBy(xpath = "//*[@id='compute']/descendant::div[contains(text(), '" + COMMITMENT_TERM + "')]")
	private WebElement commitmentTermRow;

	protected ComputeEngineEstimation(WebDriver webDriver) {
		super(webDriver);
	}

	public String getProvisioningModel() {
		return extractSubstring(buildDictionaryPattern(PROVISIONING_MODEL), getElementText(provisioningModelRow));
	}

	public String getInstanceType() {
		return extractSubstring(buildDictionaryPattern(INSTANCE_TYPE), getElementText(instanceTypeRow));
	}

	public String getRegion() {
		return extractSubstring(buildDictionaryPattern(REGION), getElementText(regionRow));
	}

	public String getLocalSSD() {
		return extractSubstring(buildDictionaryPattern(LOCAL_SSD), getElementText(localSSDRow));
	}

	public String getCommitmentTerm() {
		return extractSubstring(buildDictionaryPattern(COMMITMENT_TERM), getElementText(commitmentTermRow));
	}

	private String extractSubstring(Pattern pattern, String charSequence) {
		return pattern.matcher(charSequence)
			.results()
			.map(MatchResult::group)
			.findFirst()
			.orElse(charSequence);
	}

	private Pattern buildDictionaryPattern(String key) {
		return Pattern.compile("(?<=" + key + ":\\s)(.*?)(?=\\n|$| GiB)");
	}

	@Override
	public By getLocator() {
		return LOCATOR;
	}

	@Override
	protected void init(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
}
