package com.hms.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hms.pages.BloodBankPage;
import com.hms.pages.HomePage;
import com.hms.pages.LoginPage;
import com.hms.utils.TestBase;

public class UpdateBloodBankTDetailsest extends TestBase {
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

	@Test(priority = 1, description = "TC_UBB_001 | Functional")
	public void verifyFetchAllBanks() throws InterruptedException {
		Log.info("TC_UBB_001 | Functional | Verify Fetching blood bank list");
		bloodBankPage.navigatetoUpdateBB();
		Thread.sleep(1000);
		bloodBankPage.enterBankNameForUpdate("");
		Thread.sleep(500);
		bloodBankPage.clickFetchDetailsButton();
		Thread.sleep(1200);
		Assert.assertTrue(bloodBankPage.isDataTableVisible(), "Table failed to Display all banks");
		Log.info("Test: TC_UBB_001 | Table is successfully displayed with all bank details");
	}

	@Test(priority = 2, description = "TC_UBB_002 | Functional")
	public void verifyTargetedFetch() throws InterruptedException {
		Log.info("TC_UBB_002 | Functional | Verify fetching a specific bank");
		bloodBankPage.navigatetoUpdateBB();
		Thread.sleep(1000);
		bloodBankPage.enterBankNameForUpdate("112");
		Thread.sleep(500);
		bloodBankPage.clickFetchDetailsButton();
		Thread.sleep(1200);
		Assert.assertEquals(bloodBankPage.getFirstRowName(), "112", "Record Mismatch");
		Log.info("Test: TC_UBB_002 | Table is successfully displayed with specific bank details");
	}

	@Test(priority = 3, description = "TC_UBB_003 | Functional")
	public void verifyNaviagateToEdit() throws InterruptedException {
		Log.info("TC_UBB_003 | Functional | Verify Clicking the Update Button");
		bloodBankPage.navigatetoUpdateBB();
		Thread.sleep(1000);
		bloodBankPage.enterBankNameForUpdate("112");
		Thread.sleep(500);
		bloodBankPage.clickFetchDetailsButton();
		Thread.sleep(1400);
		bloodBankPage.clickUpdateButton();
		Thread.sleep(1200);
		Assert.assertTrue(bloodBankPage.isUpdateFormVisible(), "Update Form Missing");
		Log.info("Test: TC_UBB_003 | User is navigated to the detailed update page for that specific blood bank ");
	}

	@Test(priority = 4, description = "TC_UBB_004 | Validation")
	public void verifyUpdateMandatoryFields() throws InterruptedException {
		Log.info("TC_UBB_004 | Validation | Verify Mandatory field check");
		bloodBankPage.navigatetoUpdateBB();
		Thread.sleep(1000);
		bloodBankPage.enterBankNameForUpdate("112");
		Thread.sleep(500);
		bloodBankPage.clickFetchDetailsButton();
		Thread.sleep(1400);
		bloodBankPage.clickUpdateButton();
		Thread.sleep(1200);
		bloodBankPage.clearBankNameInForm();
		Thread.sleep(300);
		bloodBankPage.clickFinalUpdateButton();
		Thread.sleep(1000);
		Assert.assertEquals(bloodBankPage.errorMessageForNameField(), "Please enter the Blood Bank Name");
		Log.info("Test: TC_UBB_004 | Error message: [Please enter the Blood Bank Name] successfully displayed");
	}

	@Test(priority = 5, description = "TC_UBB_005 | Validation")
	public void verifyOperationalStatusUpdate() throws InterruptedException {
		Log.info("TC_UBB_005 | Validation | Verify updating operational status");
		bloodBankPage.navigatetoUpdateBB();
		Thread.sleep(1000);
		bloodBankPage.enterBankNameForUpdate("112");
		Thread.sleep(500);
		bloodBankPage.clickFetchDetailsButton();
		Thread.sleep(1400);
		bloodBankPage.clickUpdateButton();
		Thread.sleep(1200);
		bloodBankPage.selectOperationalStatus("No");
		Thread.sleep(200);
		bloodBankPage.clickFinalUpdateButton();
		Thread.sleep(1000);
		Assert.assertTrue(bloodBankPage.succesMessageForUpdateBBD().contains("error"));
		Log.info("Test: TC_UBB_005 | Operational Status Changed Successfully");
	}

	@Test(priority = 6, description = "TC_UBB_006 | Boundary")
	public void verifyBoundaryLimits() throws InterruptedException {
		Log.info("TC_UBB_006 | Boundary | Verify Character limit for Name and Address");
		bloodBankPage.navigatetoUpdateBB();
		Thread.sleep(1000);
		bloodBankPage.enterBankNameForUpdate("112");
		Thread.sleep(500);
		bloodBankPage.clickFetchDetailsButton();
		Thread.sleep(1400);
		bloodBankPage.clickUpdateButton();
		Thread.sleep(1200);
		bloodBankPage.enterAddressAndNameForUpdateInForm(
				"Lorem ipsum dolor sit amet consectetur adipiscing elit quisque faucibus ex sapien vitae pellentesque s",
				"Lorem ipsum dolor sit amet consectetur adipiscing elit quisque faucibus ex sapien vitae pellentesque s");
		Thread.sleep(200);
		Assert.assertTrue(bloodBankPage.updateFormAddressLength() < 100);
		Assert.assertTrue(bloodBankPage.uptadeFormNameLength() < 100);
		Log.info("Test: TC_UBB_006 | Boundary Check Complete");
	}

	@Test(priority = 7, description = "TC_UBB_007 | Validation")
	public void verifyWrongBBName() throws InterruptedException {
		Log.info("TC_UBB_007 | Validation | Verify Invalid Blood Bank Name");
		bloodBankPage.navigatetoUpdateBB();
		Thread.sleep(1000);
		bloodBankPage.enterBankNameForUpdate("jsfwwh36428");
		Thread.sleep(500);
		bloodBankPage.clickFetchDetailsButton();
		Thread.sleep(1000);
		Assert.assertEquals(bloodBankPage.isNoDataFoundErrorInUpdate(), "No Data Found");
		Log.info("Test: TC_UBB_007 | Error Displayed for No Data Found");
	}
}
