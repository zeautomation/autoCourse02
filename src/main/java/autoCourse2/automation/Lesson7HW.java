package autoCourse2.automation;

import org.junit.Assert;
import org.junit.Test;

import autoCourse2.automation.PageObjects.vroomCarsCatalog;
import autoCourse2.automation.PageObjects.vroomHomePage;

public class Lesson7HW extends BasicTest {

	@Test
	public void testQ1() {

		vroomHomePage.openHomePage();

		vroomHomePage.clickOnBuy();

		vroomCarsCatalog.clickOnSortBy("Highest Price");

		vroomCarsCatalog.waitUntilLoadingEnds();

		String price = vroomCarsCatalog.getCarPriceByIndex(1);

		price = price.replace("$", "");

		price = price.replace(",", "");

		double convertedPrice = Double.parseDouble(price);

		System.out.println("price is: " + price);
		
		Assert.assertTrue("Test failed  - price was below",convertedPrice>300000);
	}

	@Test
	public void testQ2() {
		vroomHomePage.openHomePage();

		vroomHomePage.clickOnBuy();

		vroomCarsCatalog.clickOnMake("BMW");

	}

}
