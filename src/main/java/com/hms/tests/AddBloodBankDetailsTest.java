package com.hms.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.hms.pages.BloodBankPage;
import com.hms.pages.HomePage;
import com.hms.pages.LoginPage;
import com.hms.utils.TestBase;

public class AddBloodBankDetailsTest extends TestBase {
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

    @DataProvider(name = "BloodBankData")
    public Object[][] BloodBankDataFeed() {
        Object[][] data = new Object[2][4];
        data[0][0] = "Infosys BloodBank";
        data[0][1] = "Mysuru";
        data[0][2] = "1234567890";
        data[0][3] = "mysuru@infosys.com";

        data[1][0] = "ABSBloodBank";
        data[1][1] = "Hyderabad";
        data[1][2] = "1010101010";
        data[1][3] = "hy@abs.com";

        return data;
    }

    @Test(priority = 1, description = "TC_ABB_001 | Functional", dataProvider = "BloodBankData")
    public void verifyValidAddition(String name, String address, String contact, String email) throws InterruptedException {
        Log.info("TC_ABB_003 | Functional | Verify successful addition of Blood Bank details");
        bloodBankPage.navigatetoAddBB();
        bloodBankPage.addBloodBank(name, address, contact, email);
        String successMessage = bloodBankPage.successMssgAdd();
        Assert.assertTrue(successMessage.contains(".")); 
        Log.info("Test: TC_ABB_001 | Blood Bank Details Successfully Added");
    }

    @Test(priority = 2, description = "TC_ABB_003 | Validation")
    public void verifyContactValidation() throws InterruptedException {
        Log.info("TC_ABB_003 | Validation | Verify Contact No. length");
        bloodBankPage.navigatetoAddBB();
        bloodBankPage.addContact("12345ewrr");
        Thread.sleep(1200);
        String error = bloodBankPage.contactError();
        Assert.assertTrue(error.contains("must"));
        Log.info("Test: TC_ABB_003 | Error Message Successfully displayed");
    }

    @Test(priority = 3, description = "TC_ABB_004 | Validation")
    public void verifyEmailValidation() throws InterruptedException {
        Log.info("TC_ABB_004 | Validation | Verify Email ID format");
        bloodBankPage.navigatetoAddBB();
        bloodBankPage.addEmail("test@com");
        Thread.sleep(1200);
        Assert.assertEquals(bloodBankPage.emailError(), "Email Id is not valid");
        Log.info("Test: TC_ABB_004 | Error Message Successfully displayed");
    }

    @Test(priority = 4, description = "TC_ABB_002 | Boundary")
    public void verifyCharacterLimit() throws InterruptedException {
        Log.info("TC_ABB_002 | Boundary | Verify Character limit for Name and Address");
        bloodBankPage.navigatetoAddBB();
        bloodBankPage.addNameAndAddress(
            "Lorem ipsum dolor sit amet consectetur adipiscing elit quisque faucibus ex sapien vitae pellentesque s",
            "Lorem ipsum dolor sit amet consectetur adipiscing elit quisque faucibus ex sapien vitae pellentesque s"
        );
        Thread.sleep(200);
        Assert.assertTrue(bloodBankPage.nameLength() < 100);
        Assert.assertTrue(bloodBankPage.addressLength() < 100);
        Log.info("Test: TC_ABB_002 | Character legnth validation Successful");
    }
}
