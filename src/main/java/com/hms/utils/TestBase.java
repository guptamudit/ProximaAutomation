package com.hms.utils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static WebDriver driver;
    public static Logger Log = LogManager.getLogger(TestBase.class);

    @BeforeMethod
    public void initialization() {
        Log.info("Initializing the web browser");
        // Setting path for chromedriver executable
        System.setProperty("webdriver.chrome.driver", 
            "C:\\Users\\mudit.gupta06\\Downloads\\Selenium\\Drivers\\Chrome Driver 143\\chromedriver.exe");
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        // Implicit wait ensures driver waits for elements to appear before failing
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url = "http://10.81.69.74:81/Automation/HMS/LoginPage.aspx";
        driver.get(url);
        Log.info("Navigated to URL: " + url);
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser session after every test method to free up memory
        if (driver != null) {
            driver.quit();
            Log.info("Browser closed and session ended");
        }
    }
}
