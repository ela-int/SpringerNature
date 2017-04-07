package main.java.com.onefootball.webapp.Functions.PageObjects;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Purpose of this page is to construct the driver.
 * While doing so it ensures the same driver is inherited to all pages .
 */

public class AbstractPage {

    protected RemoteWebDriver driver;

    public AbstractPage(RemoteWebDriver driver){
        this.driver = driver;
    }
}
