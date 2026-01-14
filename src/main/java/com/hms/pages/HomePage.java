package com.hms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
	WebDriver driver;

	// Locators for Menu Items
	private By logoutLink = By.id("lbtnLogOut");
	private By bloodsMenu = By.xpath("//*[@id=\"NavigationMenu\"]/ul/li[2]/a");
	private By banksOption = By.xpath("//*[@id=\"NavigationMenu:submenu:3\"]/li[1]/a");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickLogout() {
		driver.findElement(logoutLink).click();
	}

	// Perform Hover over blood and then click the banks option
	public void navigateToBloodBanks() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(bloodsMenu)).perform();
		Thread.sleep(1000);
		driver.findElement(banksOption).click();
	}
}
