package com.hms.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hms.pages.HomePage;
import com.hms.pages.LoginPage;
import com.hms.utils.TestBase;

public class LogoutTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;

	@BeforeMethod
	public void setup() {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@Test(priority = 1, description = "TC_LGO_001 | Functional")
	public void verifyLogout() throws InterruptedException {
		Log.info("TC_LGO_001 | Functional | Verify successful logout");
		Thread.sleep(1000);
		loginPage.login("127", "admin");
		Thread.sleep(1200);
		homePage.clickLogout();
		Thread.sleep(1200);
		Assert.assertEquals(driver.getCurrentUrl(), "http://10.81.69.74:81/Automation/HMS/LoginPage.aspx");
		Log.info("Test: TC_LGO_001 | Logout Successful");
	}

	@Test(priority = 2, description = "TC_LGO_003 | Security")
	public void verifyBrowserBackButton() throws InterruptedException {
		Log.info("TC_LGO_003 | Security | Verify session data clearing");
		Thread.sleep(1000);
		loginPage.login("127", "admin");
		Thread.sleep(1200);
		homePage.clickLogout();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		Assert.assertEquals(driver.getCurrentUrl(), "http://10.81.69.74:81/Automation/HMS/LoginPage.aspx");
		Log.info("Test: TC_LGO_003 | Browser back button restriction Successful");
	}
}
