package autoCourse2.automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import Loggingg.TestLog;
import autoCourse2.automation.Utils.fileUtils;
import autoCourse2.automation.Utils.logUtils;

public class BasicTest {

	WebDriverWrapper driverWrapper;

	TestLog testlog;

	@Rule
	public TestName testName = new TestName();

	@Before
	public void before() {
		testlog = new TestLog(testName.getMethodName(), testName.getClass().getSimpleName());

		driverWrapper = new WebDriverWrapper();
		driverWrapper.init();

		driverWrapper.setTestLog(testlog);

		GenericPageObject.setWebDriver(driverWrapper);

	}

	@After
	public void quit() throws Exception {

		driverWrapper.printConsoleLogs("404");

		driverWrapper.quit();

		logUtils.saveTestLog(testlog);
	}

}
