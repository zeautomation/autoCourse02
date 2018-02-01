package autoCourse2.automation;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import autoCourse2.automation.PageObjects.herokuappPage;
import autoCourse2.automation.PageObjects.startPage;
import autoCourse2.automation.PageObjects.vroomHomePage;

public class LoginTests extends BasicTest {

	// private static final String SUTURL =
	// "http://autocourse.tmtjk6qchm.us-east-1.elasticbeanstalk.com/loginPage.jsp";

	private static final String SUTURL = "http://www.mysupermarket.co.il/";

	
	@Test
	public void testHover(){
		
//		herokuappPage.clickOnProfile();
		
		testlog.addStep("Login");
		
		testlog.addAction("Action 1");
		
		herokuappPage.openUrl();
		
		herokuappPage.clickOnProfile();
		
		System.out.println();
		
		
		driverWrapper.printScreen();
	}
	
	@Test
	public void testVroomSearch(){
		vroomHomePage.openHomePage();
		
		
		vroomHomePage.search("BMW");
		
		vroomHomePage.getNumberOfCarsFromSearch();
		
		driverWrapper.printScreen();
	}
	
	
	
	@Test
	public void login() throws Exception {

		driverWrapper.openUrl(SUTURL);

		startPage.clickOnStart();

		startPage.clickOnX();

		String textForSearch = "לחם";

		startPage.searchForText(textForSearch);
		
//		startPage.waitForAutoComplete();

//		startPage.clickOnSearch();

		int searchResults = startPage.getNumberOfSearchItems(textForSearch);

		Assert.assertTrue(searchResults > 0);

	}

	private void getNumberOfSearchItems() {
		// TODO Auto-generated method stub

	}

}
