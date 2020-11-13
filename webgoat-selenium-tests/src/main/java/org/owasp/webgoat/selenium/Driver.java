package org.owasp.webgoat.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Driver {

    static private final long DefaultTimeoutInSeconds = 2;
    static private Driver __onlyInstance = null;

    private ChromeDriver driver = null;
    private String url = null;


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

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        if (verbose) {
            options.addArguments("--verbose");
        }

        this.driver = new ChromeDriver(options);

        try {
            this.url = url;

            login(user, password);

        } finally {
            driver.quit();
        }
    }

    private void login(String user, String password) {
        // Login
        driver.get(getBaseUrl() + "/login");
        driver.manage().timeouts().implicitlyWait(DefaultTimeoutInSeconds, TimeUnit.SECONDS);


        driver.findElement(By.name("username")).sendKeys(user);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.className("btn")).click();
    }

    private String getBaseUrl() {
        return this.url;
    }
}
