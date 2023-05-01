package com.epam.webdriver;

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
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HurtMePlentyTest extends WebDriverTest {

	private static final String GOOGLE_CLOUD_URL = "https://cloud.google.com";

	@Test(testName = "Hurt me Plenty Scenario")
	public void run() {
		webDriver.get(GOOGLE_CLOUD_URL);
		webDriver.manage().window().maximize();

		var searchQuery = "Google Cloud Platform Pricing Calculator";
		var commitedUsage = CommitedUsage.ONE_YEAR;
		var machineType = MachineType.N1_STANDARD_8;
		var localSSD = LocalSSD.GB_2_X_375;
		var provisioningModel = ProvisioningModel.REGULAR;
		var region = Region.FRANKFURT;
		var expectedCost = "USD 1,081.20";

		var calculatorPage = new GoogleCloudPage(webDriver)
			.search(searchQuery)
			.tapGoogleCloudPlatformPricingCalculatorLink();

		calculatorPage
			.switchToComputeEngineConfigurator()
			.inputNumberOfInstances(4)
			.selectOperatingSystem(OperatingSystem.FREE)
			.selectProvisioningModel(provisioningModel)
			.selectSeries(Series.N1)
			.selectMachineType(machineType)
			.addGraphics(GraphicsType.NVIDIA_TESLA_V100, GraphicsCount.COUNT_1)
			.selectLocalSSD(localSSD)
			.selectDatacenterLocation(region)
			.selectCommitedUsage(commitedUsage)
			.addToEstimate();

		var estimateBlock = calculatorPage.toEstimations();
		var computeEngineEstimate = estimateBlock.toComputeEngineEstimation();

		var softAssert = new SoftAssert();
		softAssert.assertEquals(computeEngineEstimate.getCommitmentTerm(), commitedUsage.value());
		softAssert.assertEquals(computeEngineEstimate.getInstanceType(), machineType.value());
		softAssert.assertEquals(computeEngineEstimate.getLocalSSD(), localSSD.value());
		softAssert.assertEquals(computeEngineEstimate.getProvisioningModel(), provisioningModel.value());
		softAssert.assertEquals(computeEngineEstimate.getRegion(), region.value());
		softAssert.assertEquals(estimateBlock.getTotalCost(), expectedCost);
		softAssert.assertAll();
	}
}