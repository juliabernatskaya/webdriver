package com.epam.webdriver;

import static org.testng.Assert.assertEquals;

import com.epam.webdriver.google.cloud.calculator.select.option.CommitedUsage;
import com.epam.webdriver.google.cloud.calculator.select.option.GraphicsCount;
import com.epam.webdriver.google.cloud.calculator.select.option.GraphicsType;
import com.epam.webdriver.google.cloud.calculator.select.option.LocalSSD;
import com.epam.webdriver.google.cloud.calculator.select.option.MachineType;
import com.epam.webdriver.google.cloud.calculator.select.option.OperatingSystem;
import com.epam.webdriver.google.cloud.calculator.select.option.ProvisioningModel;
import com.epam.webdriver.google.cloud.calculator.select.option.Region;
import com.epam.webdriver.google.cloud.calculator.select.option.Series;
import com.epam.webdriver.google.cloud.home.GoogleCloudPage;
import com.epam.webdriver.yopmail.YopmailHomePage;
import java.time.Duration;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

public class HardcoreTest extends WebDriverTest {

	private static final String GOOGLE_CLOUD_URL = "https://cloud.google.com";
	private static final String YOPMAIL_URL = "https://yopmail.com";
	private static final String TOTAL_COST_EMAIL_REGEX = "(?<=Total Estimated Monthly Cost\\s)(.*?)(?=\\n)";

	@Test
	public void run() {
		var searchQuery = "Google Cloud Platform Pricing Calculator";

		webDriver.get(GOOGLE_CLOUD_URL);
		webDriver.manage().window().maximize();
		var googleTab = webDriver.getWindowHandle();

		var calculatorPage = new GoogleCloudPage(webDriver)
			.search(searchQuery)
			.tapGoogleCloudPlatformPricingCalculatorLink();

		calculatorPage
			.switchToComputeEngineConfigurator()
			.inputNumberOfInstances(4)
			.selectOperatingSystem(OperatingSystem.FREE)
			.selectProvisioningModel(ProvisioningModel.REGULAR)
			.selectSeries(Series.N1)
			.selectMachineType(MachineType.N1_STANDARD_8)
			.addGraphics(GraphicsType.NVIDIA_TESLA_V100, GraphicsCount.COUNT_1)
			.selectLocalSSD(LocalSSD.GB_2_X_375)
			.selectDatacenterLocation(Region.FRANKFURT)
			.selectCommitedUsage(CommitedUsage.ONE_YEAR)
			.addToEstimate();

		var totalCost = calculatorPage.toEstimations().getTotalCost();
		var emailEstimateForm = calculatorPage.toEstimations().emailEstimation();

		webDriver.switchTo().newWindow(WindowType.TAB);
		webDriver.get(YOPMAIL_URL);
		var yopmailTab = webDriver.getWindowHandle();

		var yopmail = new YopmailHomePage(webDriver)
			.tapRandomEmailLink()
			.tapCopyToClipboardButton()
			.tapCheckInboxButton();

		webDriver.switchTo().window(googleTab);

		emailEstimateForm
			.enterEmailAddress(Keys.chord(Keys.COMMAND, "v"))
			.sendEmail();

		webDriver.switchTo().window(yopmailTab);

		var emailBody = yopmail
			.waitForNewEmail(Duration.ofSeconds(1000))
			.getEmailText();

		assertEquals(totalCost, extractTotalCostFromEmail(emailBody));
	}

	private String extractTotalCostFromEmail(String emailBody) {
		return Pattern.compile(TOTAL_COST_EMAIL_REGEX).matcher(emailBody)
			.results()
			.map(MatchResult::group)
			.findFirst()
			.orElse(emailBody);
	}
}
