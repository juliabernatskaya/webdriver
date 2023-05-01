package com.epam.webdriver;

import com.epam.webdriver.pastebin.home.PastebinHomePage;
import com.epam.webdriver.pastebin.home.select.option.Expiration;
import org.testng.annotations.Test;

public class ICanWinTest extends WebDriverTest {
	private static final String PASTEBIN_URL = "https://pastebin.com";

	@Test(testName = "I Can Win Scenario")
	public void run() {
		webDriver.get(PASTEBIN_URL);
		webDriver.manage().window().maximize();

		var paste = "Hello from WebDriver";
		var title = "helloweb";

		new PastebinHomePage(webDriver)
			.enterPaste(paste)
			.selectExpiration(Expiration.TEN_MINUTES)
			.enterTitle(title);
	}
}
