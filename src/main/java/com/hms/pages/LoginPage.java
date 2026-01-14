package com.hms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;

	// Locators for easy maintenance
	private By userid = By.id("txtUserID");
	private By password = By.id("txtPassword");
	private By loginButton = By.id("btnLogin");
	private By errorMessage = By.id("lblMessage");
	private By userEmpty = By.xpath("//*[@id=\"vceRfvUserID_popupTable\"]/tbody/tr/td[3]");
	private By passEmpty = By.xpath("//*[@id=\"vceRfvPassword_popupTable\"]/tbody/tr/td[3]");

	public LoginPage(WebDriver driver) {
		this.driver = driver; // Constructor to pass driver instance from the test
	}

	// Perform login by entering credentials and clicking on login button
	public void login(String uid, String pass) throws InterruptedException {
		driver.findElement(userid).sendKeys(uid);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(loginButton).click();
	}

	// To check password masking test
	public String getPassType() {
		return driver.findElement(password).getAttribute("type");
	}

	// Errors created by certain tests
	public String getErrorMssg() {
		return driver.findElement(errorMessage).getText();
	}

	public String getEmptyUserError() {
		return driver.findElement(userEmpty).getText();
	}

	public String getEmptyPasswordError() {
		return driver.findElement(passEmpty).getText();
	}
}
