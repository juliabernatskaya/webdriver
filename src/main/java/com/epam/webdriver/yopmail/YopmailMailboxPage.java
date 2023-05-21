package com.epam.webdriver.yopmail;

import com.epam.webdriver.common.PageObject;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@SuppressWarnings("unused")
public class YopmailMailboxPage extends PageObject {

	private static final String MAIL_FRAME = "ifmail";

	@FindBy(id = "nbmail")
	private WebElement emailCountTag;
	@FindBy(id = "refresh")
	private WebElement refreshButton;
	@FindBy(id = "mail")
	private WebElement emailBody;

	protected YopmailMailboxPage(WebDriver webDriver) {
		super(webDriver);
	}

	public YopmailMailboxPage tapRefreshButton() {
		clickElement(refreshButton);
		return this;
	}

	public String getEmailText() {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(MAIL_FRAME));
		String emailText = getElementText(emailBody);
		webDriver.switchTo().defaultContent();
		return emailText;
	}

	public YopmailMailboxPage waitForNewEmail(Duration duration) {
		var initialEmailCount = getEmailCount();
		wait
			.withTimeout(duration)
			.pollingEvery(Duration.ofSeconds(5))
			.until(webDriver -> {
				clickElement(refreshButton);
				return getEmailCount() > initialEmailCount;
			});
		return this;
	}

	private int getEmailCount() {
		return Integer.parseInt(getElementText(emailCountTag).replaceAll("\\D*", ""));
	}

	@Override
	protected void init(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
}
