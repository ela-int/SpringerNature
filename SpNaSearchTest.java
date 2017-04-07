package test.java.com.onefootball.webapp;

import main.java.com.onefootball.webapp.Functions.PageObjects.SpNaHomePage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SpNaSearchTest {

    RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        try {
            String exePath = "/Users/ekantor/QA_Web_Automation/web_automation/WebDrivers/geckodriver"; // possible to change the driver (browser), just edit the path and add the driver
            System.setProperty("webdriver.gecko.driver", exePath);
            driver = new FirefoxDriver();
            driver.get("https://link.springer.com/");
        }
        catch (Exception e){
            throw e;
        }
    }

    @AfterTest
    public void kill(){
        driver.close();
        driver.quit();
    }

    public static final int MIN_SEARCH_LENGTH = 1;
    public static final int MAX_SEARCH_LENGTH = 10000;

    @Test
    public void minLengthValidSearch() throws InterruptedException {
        System.out.println("[minLengthValidSearch] - Running on Firefox");
        SpNaHomePage homePage = new SpNaHomePage(driver);
        PageFactory.initElements(driver, homePage);

        String minLengthString = homePage.getRandomValidString(MIN_SEARCH_LENGTH);
        homePage.searchFor(minLengthString);
        Boolean outcome = homePage.verifySearchResultOutcome();

        Assert.assertTrue(outcome);

    }

    @Test
    public void maxLengthValidSearch() throws InterruptedException {
        System.out.println("[maxLengthValidSearch] - Running on Firefox");
        SpNaHomePage homePage = new SpNaHomePage(driver);
        PageFactory.initElements(driver, homePage);

        String maxLengthString = homePage.getRandomValidString(MAX_SEARCH_LENGTH);
        homePage.searchFor(maxLengthString);
        Assert.assertFalse(homePage.verifySearchResultOutcome());

        /*Maximum search length is undefined, might be different.
        I wished to demonstrate the type of test I would perform knowing the boundary.*/
    }

    @Test
    public void invalidCharSearch() throws InterruptedException {
        System.out.println("[invalidCharSearch] - Running on Firefox");
        SpNaHomePage homePage = new SpNaHomePage(driver);
        PageFactory.initElements(driver, homePage);

        String invalidString = homePage.getRandomInvalidString(MIN_SEARCH_LENGTH);
        homePage.searchFor(invalidString);
        Boolean errorOutcome = homePage.verifySorryNoResultsMessage();

        Assert.assertTrue(errorOutcome);
    }

    @Test
    public void specialCharSearch() throws InterruptedException {
        System.out.println("[specialCharSearch] - Running on Firefox");
        SpNaHomePage homePage = new SpNaHomePage(driver);
        PageFactory.initElements(driver, homePage);

        String special = homePage.getRandomSpecialChar();

        homePage.searchFor(special);
        Boolean error = homePage.verifySorryNoResultsMessage();
        Boolean outcome = homePage.verifySearchResultOutcome();

        Assert.assertTrue(!error && outcome);
    }
}
