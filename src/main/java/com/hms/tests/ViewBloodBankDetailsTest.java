package com.hms.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hms.pages.BloodBankPage;
import com.hms.pages.HomePage;
import com.hms.pages.LoginPage;
import com.hms.utils.TestBase;

public class ViewBloodBankDetailsTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	BloodBankPage bloodBankPage;

	@BeforeMethod
	public void setup() throws InterruptedException {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		bloodBankPage = new BloodBankPage(driver);
		Thread.sleep(1000);
		loginPage.login("127", "admin");
		Log.info("Navigating to Blood Bank Page");
		Thread.sleep(1000);
		homePage.navigateToBloodBanks();
		Thread.sleep(1000);
	}

	@Test(priority = 1, description = "TC_VBB_002 | Functional")
	public void verifyAutoSuggest() throws InterruptedException {
		Log.info("TC_VBB_002 | Functional | Verify auto-suggestion functionality");
		bloodBankPage.navigatetoViewBB();
		bloodBankPage.partialName("12345");
		Thread.sleep(5000);
		Assert.assertTrue(bloodBankPage.isAutoSuggestListVisible(), "Auto Suggest dropdown did not appear");
		Log.info("Test: TC_VBB_002 | Auto Suggest Dropdown Appeared");
	}

	@Test(priority = 2, description = "TC_VBB_003 | Functional")
	public void verifyFetchDetails() throws InterruptedException {
		Log.info("TC_VBB_003 | Functional | Verify successful data retrieval");
		bloodBankPage.navigatetoViewBB();
		bloodBankPage.selectFromSuggestions("12345");
		Thread.sleep(1000);
		bloodBankPage.clickFetchButton();
		bloodBankPage.clickFetchButton();
		Thread.sleep(2000);
		Assert.assertTrue(bloodBankPage.isTableDisplayed(), "Table details were not displayed");
		Log.info("Test: TC_VBB_003 | Table Data displayed successfully");
	}

	@Test(priority = 3, description = "TC_VBB_004 | Validation")
	public void verifyWrongBBName() throws InterruptedException {
		Log.info("TC_VBB_004 | Validation | Verify Invalid Blood Bank Name");
		bloodBankPage.navigatetoViewBB();
		Thread.sleep(1000);
		bloodBankPage.selectFromSuggestions("sdygewih1241");
		Thread.sleep(1000);
		bloodBankPage.clickFetchButton();
		Thread.sleep(1000);
		bloodBankPage.clickFetchButton();
		Thread.sleep(2000);
		Assert.assertEquals(bloodBankPage.isNoDataFoundErrorInView(), "No Data Found");
		Log.info("Test: TC_VBB_004 | Error Displayed for No Data Found");
	}
}
