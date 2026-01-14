# Hospital Management System (Proxima Health) - Selenium Automation Framework

## 📌 Project Concept
This project is an end-to-end automation suite for a **Hospital Management System (HMS)**. The primary goal is to validate critical business workflows including user authentication, security protocols, and the management of blood bank data.

The framework is built using the **Page Object Model (POM)** design pattern, which separates the application's UI locators from the test scripts to ensure the code is modular and easy to maintain.

---

## Code Structure
The project follows a standard Maven directory structure for organized development:

### 1. `com.hms.utils` (Utility Layer)
* **TestBase.java**: The heart of the framework. It handles the browser setup (Chrome in Incognito mode), sets global timeouts (Implicit Waits), and configures Log4j2 for auditing.

### 2. `com.hms.pages` (Object Repository)
* **LoginPage.java**: Contains private `By` locators and methods for login interactions and credential validation.
* **HomePage.java**: Manages global navigation, including the mouse-hover actions for the Blood Bank menus and the Logout functionality.
* **BloodBankPage.java**: Handles all three sub-modules: **Add**, **View**, and **Update** Blood Bank details.

### 3. `com.hms.tests` (Test Script Layer)
* **LoginTest.java**: Executes test cases for valid/invalid logins and mandatory field checks.
* **BloodBankTest.java**: Includes data-driven tests for adding records and verifying numeric constraints for Donor IDs and Contact Numbers.
* **LogoutTest.java**: Validates critical security requirements like session clearing and browser back-button restrictions.

---

## Program Flow
1. **Initialization**: `TestBase` starts the Chrome driver using `ChromeOptions` for an incognito session.
2. **Setup**: The Test class (e.g., `LoginTest`) initializes the required Page Objects, passing the `driver` instance to them via constructors.
3. **Action**: The test script calls high-level methods from the Page classes (e.g., `loginPage.login("user", "pass")`).
4. **Validation**: TestNG assertions compare actual results (like URLs or error messages) against the expected results defined in the SRS.
5. **Logging**: Throughout the process, Log4j2 records every step into `app.log` for a complete audit trail.
6. **Teardown**: The `@AfterMethod` in `TestBase` closes the browser session to release resources.



---

## Requirement Traceability
This framework ensures 100% coverage of the following requirements:
* **Requirement 1 (Login)**: Mandatory field validation and password masking.
* **Requirement 2 (Logout)**: Redirection to login page and disabling back-button access.
* **Requirement 3 (Add Bank)**: Constraints of 0-10 digits for Donor ID and exactly 10 digits for Contact No.
* **Requirement 4 (View/Update)**: AJAX auto-suggest dropdown handling and operational status updates.

---

## Setup Instructions
1. Install **JDK 11+** and **Eclipse IDE**.
2. Install the **TestNG plugin** for Eclipse.
3. Import this project as an **Existing Maven Project**.
4. Update the `chromedriver.exe` path in `TestBase.java`.
5. Right-click `testng.xml` and select **Run As > TestNG Suite**.
