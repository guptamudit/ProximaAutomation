package com.hms.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hms.pages.LoginPage;
import com.hms.utils.TestBase;

public class LoginTest extends TestBase {
	LoginPage loginPage;

	@BeforeMethod
	public void setup() {
		loginPage = new LoginPage(driver);
	}

	@Test(priority = 1, description = "TC_LGN_002 | Mandatory")
	public void verifyMandatoryFields() throws InterruptedException {
		Log.info("TC_LGN_002 | Mandatory | Verify mandatory fields violation");
		Thread.sleep(1000);
		loginPage.login("", "");
		Thread.sleep(1000);
		Assert.assertEquals(loginPage.getEmptyUserError(), "Please enter the User ID");
		Log.info("Test: TC_LGN_002 | Error message received");
	}

	@Test(priority = 2, description = "TC_LGN_003 | Security")
	public void verifyPasswordMasking() throws InterruptedException {
		Log.info("TC_LGN_003 | Security | Verify Password Masking");
		Thread.sleep(1000);
		Assert.assertEquals(loginPage.getPassType(), "password");
		Log.info("Test: TC_LGN_003 | Password Masking");
	}

	@Test(priority = 3, description = "TC_LGN_004 | Functional")
	public void verifyValidLogin() throws InterruptedException {
		Log.info("TC_LGN_004 | Functional | Verify successful login with valid credentials");
		Thread.sleep(2000);
		loginPage.login("127", "admin");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getCurrentUrl(), "http://10.81.69.74:81/Automation/HMS/Home.aspx");
		Log.info("Test: TC_LGN_004 | Login Successful");
	}

	@Test(priority = 4, description = "TC_LGN_005 | Functional")
	public void verifyInvalidCredentials() throws InterruptedException {
		Log.info("TC_LGN_005 | Functional | Verify invalid credentials");
		Thread.sleep(1000);
		loginPage.login("123", "wert");
		Thread.sleep(1200);
		Assert.assertEquals(loginPage.getErrorMssg(), "Invalid Credentials");
		Log.info("Test: TC_LGN_005 | Error message received");
	}
}
