package autoCourse2.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Loggingg.ActionType;
import Loggingg.StepAction;
import Loggingg.TestLog;

public class WebDriverWrapper {

	RemoteWebDriver remoteWebDriver;

	TestLog testLog;

	public void setTestLog(TestLog testLog) {
		this.testLog = testLog;
	}

	public void init(String remoteUrl) throws MalformedURLException {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		remoteWebDriver = new RemoteWebDriver(new URL(remoteUrl), capabilities);

		remoteWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public LogEntries getConsoleLogs() {
		return remoteWebDriver.manage().logs().get(LogType.BROWSER);
	}

	public void printConsoleLogs(String filter) {
		// list of the log entries (רשומות)
		List<LogEntry> consoleLogentries = getConsoleLogs().getAll();

		for (int i = 0; i < consoleLogentries.size(); i++) {

			LogEntry entry = consoleLogentries.get(i);

			if (entry.getMessage().contains(filter)) {
				System.out.println(entry.getMessage() + ": " + entry.getTimestamp());
			}

		}
	}

	public void clearLocalStorage() {
		// cleaning the local storage from the browser

		runJavascript(String.format("window.localStorage.clear();"));
	}

	public void runJavascript(String javascript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) remoteWebDriver;

			js.executeScript(javascript);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteCookies() {
		// Deleting all cookies from the browser
		remoteWebDriver.manage().deleteAllCookies();
	}

	public void addCookies(String cookieName, String value) {

		testLog.addAction("Deleting cookies");
		Cookie cookie = new Cookie(cookieName, value);

		remoteWebDriver.manage().addCookie(cookie);
	}

	public void init() {

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		LoggingPreferences loggingPreferences = new LoggingPreferences();
		loggingPreferences.enable(LogType.BROWSER, Level.ALL);
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);

		remoteWebDriver = new RemoteWebDriver(capabilities);
	}

	public void openUrl(String url) {
		remoteWebDriver.get(url);
	}

	public WebElement getElementByType(String value, String type) {
		return getElementByType(value, type, "visible");
	}

	public WebElement getElementByType(String value, String type, String condition) {

		testLog.addAction("finding element: "+value);
		
		WebElement element = null;

		By by = null;

		if (type.equals("xpath")) {
			by = By.xpath(value);
		} else if (type.equals("id")) {
			by = By.id(value);
		}

		try {
			WebDriverWait driverWait = new WebDriverWait(remoteWebDriver, 20, 1000);

			if (condition.equals("clickable")) {
				element = driverWait.until(ExpectedConditions.elementToBeClickable(by));
			} else if (condition.equals("visible")) {
				element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
			} else if (condition.equals("notVisible")) {
				driverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			}

		} catch (TimeoutException e) {
			testLog.addActionToLastStep(
					new StepAction("Element with value:" + value + " not found", ActionType.failedAction, false));
			printScreen();
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (element == null && condition.equals("notVisible") == false) {
			testLog.addAfailedAction("Failed to find element: "+value +" by type: "+type);
			Assert.fail();
		}

		return element;

	}

	public WebElement get_Element(By by) {
		WebElement element = remoteWebDriver.findElement(by);

		if (element != null) {
			return element;
		} else
			return null;

	}

	public void quit() {
		remoteWebDriver.quit();
	}

	public void init2() {

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		remoteWebDriver = new RemoteWebDriver(capabilities);
	}

	public void hoverAndClick(String xpathInitial, String xpathToClick) {
		WebElement element = getElementByType(xpathInitial, "xpath");

		Actions actions = new Actions(remoteWebDriver);

		actions.moveToElement(element).pause(Duration.ofSeconds(2))
				.moveToElement(remoteWebDriver.findElement(By.xpath(xpathToClick))).pause(Duration.ofSeconds(2)).click()
				.perform();
		;
	}

	public void dragAndDrop(String xpathFrom, String xpathTo) {
		Actions actions = new Actions(remoteWebDriver);

		WebElement elementFrom = get_Element(By.xpath(xpathFrom));

		actions.click(elementFrom).moveToElement(get_Element(By.xpath(xpathTo))).release().perform();
	}

	public void scroll(String script) {

		int heigt = remoteWebDriver.manage().window().getSize().getHeight();

		script = "window.scroll(0,'" + heigt + "')";

		remoteWebDriver.executeScript(script);

	}

	public List<WebElement> findListOfElements(String xpath) {
		WebDriverWait driverWait = new WebDriverWait(remoteWebDriver, 20, 1000);
		List<WebElement> elementsList = null;

		try {
			elementsList = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			printScreen();
			e.printStackTrace();

		}

		return elementsList;

	}

	public int getElemenetsByXpath(String xpath) {
		WebDriverWait driverWait = new WebDriverWait(remoteWebDriver, 20, 1000);

		List<WebElement> elementsList = driverWait
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(xpath), 1));

		if (elementsList != null) {
			return elementsList.size();
		} else
			return 0;
	}

	public void printScreen() {

		WebDriver augmentedDriver = new Augmenter().augment(remoteWebDriver);
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

		copyFile(screenshot, System.getProperty("user.dir") + "\\files\\scr\\" + screenshot.getName());
	}

	public static void copyFile(File source, String destinationPath) {
		try {
			InputStream in = new FileInputStream(source);
			try {
				OutputStream out = new FileOutputStream(new File(destinationPath));
				try {
					// Transfer bytes from in to out
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
				} finally {
					out.close();
				}
			} finally {
				in.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
