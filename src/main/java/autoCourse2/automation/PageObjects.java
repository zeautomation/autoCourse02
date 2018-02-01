package autoCourse2.automation;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PageObjects {

	public static class herokuappPage extends GenericPageObject {

		public static void openUrl() {
			driverWrapper.openUrl("http://the-internet.herokuapp.com/hovers");
		}

		public static void clickOnProfile() {
			driverWrapper.hoverAndClick("//h5[contains(text(),'user1')]//parent::div//preceding-sibling::img",
					"//a[@href='/users/1']");
		}

		public static void isPageLoaded() {
			// TODO Auto-generated method stub

		}

	}

	public static class vroomCarsCatalog extends GenericPageObject {

		public static void clickOnMake(String make) {
			WebElement element = driverWrapper
					.getElementByType("//ul[@class='all-makes-list']//li//a[contains(text(),'" + make + "')]", "xpath");

			if (element != null) {
				element.click();
			} else {
				Assert.fail("Cannot click on make");
			}
		}

		public static void clickOnSortBy(String sortBy) {
		
			driverWrapper.hoverAndClick("//div[@class='catalog-sort-dropdown']//span[@class='dropdown-heading']",
					"//a[text()='" + sortBy + "']");
	
			
		
		}

		public static void waitUntilLoadingEnds() {
			driverWrapper.getElementByType("//div[@class='top-section']//div[@class='middle']//h3[text()='Loading...']",
					"xpath", "notVisible");
		}

		public static String getCarPriceByIndex(int index) {
			WebElement element = driverWrapper.getElementByType("//ul[contains(@class,'car-list')]//li[" + index
					+ "]//a[contains(@href,'inventory')]//div[@class='price']", "xpath");

			if (element != null) {
				return element.getText();
			} else
				return null;
		}
	}

	public static class vroomHomePage extends GenericPageObject {

		public void enterUser() {

		}

		public void enterPassword() {

		}

		public void clickSubmit() {

		}

		public void login(String username, String password) {
			enterUser();
			enterPassword();
			clickSubmit();
		}

		/**
		 * for opening the home page
		 */
		public static void openHomePage() {

			driverWrapper.openUrl("http://www.vroom.com");
		}

		public static int getNumberOfCarsFromSearch() {
			int size = driverWrapper.getElemenetsByXpath("//div[@class='car-image-container']");
			return size;
		}

		public static void search(String text) {
			WebElement search = driverWrapper.getElementByType("hero-search-input", "id");

			if (search != null) {
				search.sendKeys(text);
				// search.sendKeys(Keys.ENTER);

				driverWrapper.getElementByType("//button[@class='search-button']", "xpath").click();
				;
			} else {
				System.out.println("Element not found!");
			}
		}

		public static void clickOnBuy() {
			driverWrapper.get_Element(By.xpath("//div[@class='main-header-inner']//a[text()='Buy']")).click();
			;

		}
	}

	public static class startPage extends GenericPageObject {

		public static void clickOnStart() {
			WebElement element = driverWrapper
					.getElementByType("//div[@class='Centered']//div[@class='StartButton']//a", "xpath");

			if (element != null) {
				element.click();
			}

		}

		public static void clickOnX() {
			WebElement element = driverWrapper.getElementByType("//span[contains(@class,'closethick')]", "xpath");

			if (element != null) {
				element.click();
			}
		}

		public static void searchForText(String textForSearch) {
			WebElement element = driverWrapper.getElementByType("txtfind", "id", "visible");

			if (element != null) {
				element.sendKeys(textForSearch);

				element.sendKeys(Keys.ENTER);
			}

		}

		public static void clickOnSearch() {
			WebElement element = driverWrapper.getElementByType("//div[@class='MagnifyIcon']", "xpath", "clickable");

			if (element != null) {
				element.click();
			}

		}

		public static int getNumberOfSearchItems(String searchTerm) {
			return driverWrapper
					.getElemenetsByXpath("//li[@id='NgMspProductCell']//span[contains(text(),'" + searchTerm + "')]");
		}

		public static void waitForAutoComplete() {
			driverWrapper.getElemenetsByXpath("//ul[contains(@class,'ui-autocomplete')]//li");

		}

	}

}
