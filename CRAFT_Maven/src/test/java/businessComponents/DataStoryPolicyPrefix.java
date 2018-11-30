package businessComponents;

import org.openqa.selenium.Platform;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.cognizant.framework.IterationOptions;
import com.cognizant.framework.selenium.Browser;
import com.cognizant.framework.selenium.ExecutionMode;
import com.cognizant.framework.selenium.SeleniumTestParameters;
import com.cognizant.Craft.*;


/**
 * Test for New Contact Creation
 * 
 * @author Cognizant
 */
public class DataStoryPolicyPrefix extends CRAFTTestCase {

	@Test(dataProvider = "DataStoryPolicyPrefix")
	public void testRunner(String testInstance, ExecutionMode executionMode,
			Browser browser, Platform platform) {
		SeleniumTestParameters testParameters = new SeleniumTestParameters(
				currentScenario, currentTestcase);
		testParameters
				.setCurrentTestDescription("Data Story to search Policy with FLD/000 Prefix");
		testParameters
				.setIterationMode(IterationOptions.RUN_ONE_ITERATION_ONLY);

		testParameters.setCurrentTestInstance(testInstance);
		testParameters.setExecutionMode(executionMode);
		testParameters.setBrowser(browser);
		testParameters.setPlatform(platform);

		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();

		tearDownTestRunner(testParameters, driverScript);
	}

	@DataProvider(name = "DataStoryPolicyPrefix", parallel = true)
	public Object[][] dataTC2() {
		return new Object[][] {
			{ "Instance1", ExecutionMode.LOCAL, Browser.CHROME, 1, 1 }
		//{ "Instance2", ExecutionMode.LOCAL, Browser.FIREFOX, 1, 1 }, 
			};
	}
}