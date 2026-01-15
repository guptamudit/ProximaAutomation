package com.hms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BloodBankPage {
	WebDriver driver;
	WebDriverWait wait; // For explicit wait

	private By addBBD = By.id("__tab_cphMainContent_tcBloodBank_tpAddBloodBank"); // Add Blood Bank Details Page tab

	// Locators for the Form Fields
	private By bbName = By.id("cphMainContent_tcBloodBank_tpAddBloodBank_txtBloodBankName");
	private By bbAddress = By.id("cphMainContent_tcBloodBank_tpAddBloodBank_txtAddress");
	private By bbContact = By.id("cphMainContent_tcBloodBank_tpAddBloodBank_txtContactNo");
	private By bbEmail = By.id("cphMainContent_tcBloodBank_tpAddBloodBank_txtEmailID");

	// Locator for the Add Bank Details Button
	private By addButton = By.id("cphMainContent_tcBloodBank_tpAddBloodBank_btnAddDetails");

	// Locators for Error and Success Message
	private By contactErr = By.xpath(
			"//*[@id=\"cphMainContent_tcBloodBank_tpAddBloodBank_revContactNo_ValidatorCalloutExtender_popupTable\"]/tbody/tr/td[3]");
	private By emailErr = By.xpath(
			"//*[@id=\"cphMainContent_tcBloodBank_tpAddBloodBank_revEmailID_ValidatorCalloutExtender_popupTable\"]/tbody/tr/td[3]");
	private By successAddMessage = By.id("cphMainContent_tcBloodBank_tpAddBloodBank_lblMessages");

	public BloodBankPage(WebDriver driver) {
		this.driver = driver;
	}

	// Function to navigate to Add Blood Bank Details Tab
	public void navigatetoAddBB() throws InterruptedException {
		driver.findElement(addBBD).click();
		Thread.sleep(1000);
	}

	// Function to Fill the Add Blood Bank Form Fields
	public void addBloodBank(String name, String address, String contact, String email) throws InterruptedException {
		driver.findElement(bbName).clear();
		driver.findElement(bbName).sendKeys(name);
		Thread.sleep(300);
		driver.findElement(bbAddress).clear();
		driver.findElement(bbAddress).sendKeys(address);
		Thread.sleep(300);
		driver.findElement(bbContact).clear();
		driver.findElement(bbContact).sendKeys(contact);
		Thread.sleep(300);
		driver.findElement(bbEmail).clear();
		driver.findElement(bbEmail).sendKeys(email);
		Thread.sleep(300);
		driver.findElement(addButton).click();
		Thread.sleep(1000);
	}

	// Function to fetch success message after adding Bank Details
	public String successMssgAdd() {
		return driver.findElement(successAddMessage).getText();
	}

	// Function to check for Contact Verification
	public void addContact(String contact) {
		driver.findElement(bbContact).clear();
		driver.findElement(bbContact).sendKeys(contact);
		driver.findElement(bbEmail).click();
	}

	// Function to fetch Contact Verification Error
	public String contactError() {
		return driver.findElement(contactErr).getText();
	}

	// Function to check for Email Verification
	public void addEmail(String email) {
		driver.findElement(bbEmail).clear();
		driver.findElement(bbEmail).sendKeys(email);
		driver.findElement(bbContact).click();
	}

	// Function to fetch Email Verification Error
	public String emailError() {
		return driver.findElement(emailErr).getText();
	}

	// Function to check for Name and Address Boundary Verification
	public void addNameAndAddress(String name, String add) throws InterruptedException {
		driver.findElement(bbName).clear();
		driver.findElement(bbName).sendKeys(name);
		Thread.sleep(300);
		driver.findElement(bbAddress).clear();
		driver.findElement(bbAddress).sendKeys(add);
	}

	// Function to fetch Name Field length
	public int nameLength() {
		return driver.findElement(bbName).getText().length();
	}

	// Function to fetch Address Field Length
	public int addressLength() {
		return driver.findElement(bbAddress).getText().length();
	}

	private By viewBBD = By.id("__tab_cphMainContent_tcBloodBank_tpViewBloodBank"); // View Blood Bank Details Page tab

	// Locator for Blood Bank Name input Field
	private By bankNameInput = By.id("cphMainContent_tcBloodBank_tpViewBloodBank_txtBloodBankNameV");

	// Locator to Fetch Details Button in View Banks Tab
	private By fetchButton = By.id("cphMainContent_tcBloodBank_tpViewBloodBank_btnSearchBloodBank");

	// Locator for auto suggest list
	private By suggestionList = By
			.id("cphMainContent_tcBloodBank_tpViewBloodBank_txtBloodBankNameV_AutoCompleteExtender_completionListElem");

	// Locator for Details Table for Specific Bank Name
	private By detailsTable = By.id("cphMainContent_tcBloodBank_tpViewBloodBank_gvViewBloodBanks");

	// Locator to get No Data Found Error
	private By noDataFoundInView = By
			.xpath("//*[@id=\"cphMainContent_tcBloodBank_tpViewBloodBank_gvViewBloodBanks\"]/tbody/tr/td");

	// Function to navigate to View Blood Bank Details Tab
	public void navigatetoViewBB() throws InterruptedException {
		driver.findElement(viewBBD).click();
		Thread.sleep(1000);
	}

	// Function to check auto suggest feature
	public void partialName(String partialName) {
		driver.findElement(bankNameInput).sendKeys(partialName);
	}

	// Function to verify the auto suggest availability
	public boolean isAutoSuggestListVisible() {
		return driver.findElement(suggestionList).isDisplayed();
	}

	// Function to select a specific bank name
	public void selectFromSuggestions(String fullName) throws InterruptedException {
		driver.findElement(bankNameInput).sendKeys(fullName);
	}

	// Function to click on Fetch Button Details
	public void clickFetchButton() {
		driver.findElement(fetchButton).click();
	}

	// Function to check if Details for Specific Bank Name is displayed
	public boolean isTableDisplayed() {
		return driver.findElement(detailsTable).isDisplayed();
	}

	// Function to check for invalid blood bank name
	public String isNoDataFoundErrorInView() {
		return driver.findElement(noDataFoundInView).getText();
	}

	// Update Blood Bank Details Page
	private By updateBBD = By.id("__tab_cphMainContent_tcBloodBank_tpUpdateBloodBank");

	// Locator to Bank Name Field
	private By UpdatebankNameInput = By.id("cphMainContent_tcBloodBank_tpUpdateBloodBank_txtBloodBankNameU1");

	// Buttons on the page to Fetch, Update and Final Update
	private By fetchDetailsButton = By.id("cphMainContent_tcBloodBank_tpUpdateBloodBank_btnSearchBloodBankU");
	private By updateButton = By
			.xpath("//*[@id=\"cphMainContent_tcBloodBank_tpUpdateBloodBank_gvBloodBank\"]/tbody/tr[2]/td[6]/input");
	private By finalUpdateButton = By.id("cphMainContent_tcBloodBank_tpUpdateBloodBank_btnUpdateDetails");

	// Locator for Details of Bank stated in Bank Name Field
	private By dataTable = By.id("cphMainContent_tcBloodBank_tpUpdateBloodBank_gvBloodBank");
	private By firstRowBankName = By
			.xpath("//*[@id=\"cphMainContent_tcBloodBank_tpUpdateBloodBank_gvBloodBank\"]/tbody/tr[2]/td[1]");

	// Locator for the Update Form
	private By updateDetailsForm = By.id("cphMainContent_tcBloodBank_tpUpdateBloodBank_plBloodBankUpdate");

	// Locator for Bank and Address Name fields in update form
	private By editBankNameFormField = By.id("cphMainContent_tcBloodBank_tpUpdateBloodBank_txtBloodBankNameU");
	private By editAddressNameFormField = By.id("cphMainContent_tcBloodBank_tpUpdateBloodBank_txtAddressU");

	// Radio Button in update form
	private By radioBisOperational = By.id("cphMainContent_tcBloodBank_tpUpdateBloodBank_rblStatusU_1");

	// Error and Success Messages
	private By errorForBankNameField = By.xpath(
			"//*[@id=\"cphMainContent_tcBloodBank_tpUpdateBloodBank_rfvBloodBankNameU_ValidatorCalloutExtender_popupTable\"]/tbody/tr/td[3]");
	private By successMessageForUpdateBB = By.id("cphMainContent_tcBloodBank_tpUpdateBloodBank_lblMessagesU");

	// Locator to get No Data Found Error
	private By noDataFoundInUpdate = By
			.xpath("//*[@id=\"cphMainContent_tcBloodBank_tpUpdateBloodBank_gvBloodBank\"]/tbody/tr/td");

	// Function to navigate to Update Blood Bank Details Tab
	public void navigatetoUpdateBB() throws InterruptedException {
		driver.findElement(updateBBD).click();
		Thread.sleep(1000);
	}

	// Function to add Bank Name to fetch details of that bank
	public void enterBankNameForUpdate(String name) {
		driver.findElement(UpdatebankNameInput).clear();
		driver.findElement(UpdatebankNameInput).sendKeys(name);
	}

	// Click to fetch Bank Details to update
	public void clickFetchDetailsButton() {
		driver.findElement(fetchDetailsButton).click();
	}

	// Function for Test: TC_UBB_002
	public String getFirstRowName() {
		return driver.findElement(firstRowBankName).getText();
	}

	// Click Update button from the Bank Name Table
	public void clickUpdateButton() {
		driver.findElement(updateButton).click();
	}

	// Function for Test: TC_UBB_001
	public boolean isDataTableVisible() {
		return driver.findElement(dataTable).isDisplayed();
	}

	// Function to Check for the Update Form for the selected Blood Bank Name
	public boolean isUpdateFormVisible() {
		return driver.findElement(updateDetailsForm).isDisplayed();
	}

	// Function for Test: TC_UBB_004
	public void clearBankNameInForm() {
		driver.findElement(editBankNameFormField).clear();
	}

	// Function to add name and address for Test: TC_UBB_006
	public void enterAddressAndNameForUpdateInForm(String address, String name) {
		driver.findElement(editAddressNameFormField).clear();
		driver.findElement(editAddressNameFormField).sendKeys(address);
		driver.findElement(editBankNameFormField).clear();
		driver.findElement(editBankNameFormField).sendKeys(name);
	}

	// Radio Button Clicking Function
	public void selectOperationalStatus(String status) {
		if (status.equalsIgnoreCase("No")) {
			driver.findElement(radioBisOperational).click();
		}
	}

	// Function to click the Update Details Button
	public void clickFinalUpdateButton() {
		driver.findElement(finalUpdateButton).click();
	}

	// Functions to fetch Error/Success messages
	public String errorMessageForNameField() {
		return driver.findElement(errorForBankNameField).getText();
	}

	public String succesMessageForUpdateBBD() {
		return driver.findElement(successMessageForUpdateBB).getText();
	}

	// Function to check for invalid blood bank name
	public String isNoDataFoundErrorInUpdate() {
		return driver.findElement(noDataFoundInUpdate).getText();
	}

	// Functions to verify Name and Address Boundary check
	public int uptadeFormNameLength() {
		return driver.findElement(editBankNameFormField).getText().length();
	}

	public int updateFormAddressLength() {
		return driver.findElement(editAddressNameFormField).getText().length();
	}
}
