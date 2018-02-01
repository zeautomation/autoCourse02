package autoCourse2.automation;

import java.net.MalformedURLException;

import org.junit.Before;

public class BasicTest2 {

	public WebDriverWrapper webDriverWrapper;

	@Before
	public void setup() throws MalformedURLException {
		webDriverWrapper = new WebDriverWrapper();

//		webDriverWrapper.init("http://localhost:4444/wd/hub");
		
		webDriverWrapper.init();
	}

}
