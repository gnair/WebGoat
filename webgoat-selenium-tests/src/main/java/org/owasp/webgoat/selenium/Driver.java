package org.owasp.webgoat.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Driver {

    static private final long DefaultTimeoutInSeconds = 2;
    static private Driver __onlyInstance = null;


    // Singleton class factory
    static synchronized Driver getOnlyInstance(String driver) {
        __onlyInstance = new Driver(driver);

        return __onlyInstance;
    }

    // Private constructor
    private Driver(String driver) {
        System.setProperty("webdriver.chrome.driver", driver);
    }

    // The Selenium invocation.
    public void invoke(String url, String user, String password, boolean verbose) {
        // TODO: For now we rely on Chrome Driver.

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        if (verbose) {
            options.addArguments("--verbose");
        }

        ChromeDriver driver = new ChromeDriver(options);

        try {
            // Login
            driver.get(url + "/login");
            driver.manage().timeouts().implicitlyWait(DefaultTimeoutInSeconds, TimeUnit.SECONDS);


            driver.findElement(By.name("username")).sendKeys(user);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.className("btn")).click();

        } finally {
            driver.quit();
        }
    }
}
