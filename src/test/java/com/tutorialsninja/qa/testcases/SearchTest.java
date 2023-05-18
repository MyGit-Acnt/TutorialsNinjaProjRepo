package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

//added updated comment-tree
//lead comment updated--

public class SearchTest extends Base {
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;

	
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifysearchWithValidProduct() {
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
		searchPage = homePage.clickOnSearchButton();
		
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(), "Valid Product HP is not displayed in search results");
		
	}
	
	@Test(priority=2)
	public void verifysearchWithInvalidProduct() {
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		searchPage = homePage.clickOnSearchButton();

		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage,"abcd", "No Product message in search results is not displayed");
		
	}

	@Test(priority=3, dependsOnMethods= {"verifysearchWithValidProduct", "verifysearchWithInvalidProduct"})
	public void verifysearchWithoutAnyProduct() {
		searchPage = homePage.clickOnSearchButton();
		
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty("NoProductTextInSearchResults"), "No Product message in search results is not displayed");
		
		
	}
}
