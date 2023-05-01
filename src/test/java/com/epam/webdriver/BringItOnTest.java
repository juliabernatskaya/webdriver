package com.epam.webdriver;

import com.epam.webdriver.pastebin.home.PastebinHomePage;
import com.epam.webdriver.pastebin.home.select.option.Expiration;
import com.epam.webdriver.pastebin.home.select.option.Syntax;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BringItOnTest extends WebDriverTest {

	private static final String PASTEBIN_URL = "https://pastebin.com";

	@Test(testName = "Bring It On Scenario")
	public void run() {
		webDriver.get(PASTEBIN_URL);
		webDriver.manage().window().maximize();

		var paste = "git config --global user.name \"New Sheriff in Town\"\n"
			+ "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n"
			+ "git push origin master --force";
		var title = "how to gain dominance among developers";
		var syntax = Syntax.BASH;

		var pastePage = new PastebinHomePage(webDriver)
			.enterPaste(paste)
			.selectSyntax(syntax)
			.selectExpiration(Expiration.TEN_MINUTES)
			.enterTitle(title)
			.submitPaste();

		var softAssert = new SoftAssert();
		softAssert.assertEquals(pastePage.getTitle(), title);
		softAssert.assertEquals(pastePage.getSyntax(), syntax.value());
		softAssert.assertEquals(pastePage.getPaste(), paste);
		softAssert.assertAll();
	}
}
