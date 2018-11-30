package testscripts.CaseManagement;

import org.openqa.selenium.Platform;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cognizant.framework.IterationOptions;
import com.cognizant.framework.selenium.Browser;
import com.cognizant.framework.selenium.ExecutionMode;
import com.cognizant.framework.selenium.SeleniumTestParameters;

import com.cognizant.Craft.CRAFTTestCase;
import com.cognizant.Craft.DriverScript;

/**
 * Test for Policy Search By Location Address
 * 
 * @author Cognizant
 */
public class CreationOfCase extends CRAFTTestCase {

	@Test(dataProvider = "CreationOfCase")
	public void testRunner(String testInstance, ExecutionMode executionMode,
			Browser browser, Platform platform) {
		SeleniumTestParameters testParameters = new SeleniumTestParameters(
				currentScenario, currentTestcase);
		testParameters
				.setCurrentTestDescription("Create new Case with FLE user");
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

	@DataProvider(name = "CreationOfCase", parallel = true)
	public Object[][] dataTC2() {
		return new Object[][] {
			{ "Instance1", ExecutionMode.LOCAL, Browser.CHROME, 1, 1 }
		//{ "Instance2", ExecutionMode.LOCAL, Browser.FIREFOX, 1, 1 }, 
			};
	}
}



