package main.java.com.onefootball.webapp.Functions.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

/**
 * Created by ekantor on 04/04/17.
 */
public class SpNaHomePage extends AbstractPage {
    public SpNaHomePage(RemoteWebDriver driver){
        super(driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FindBy(css = "#query")
    private WebElement searchField;

    @FindBy(css = "#search")
    private WebElement searchSubmitButton;

    @FindBy(css = "#global-search-new")
    private WebElement newSearchButton;

    @FindBy(css = ".number-of-search-results-and-search-terms")
    private WebElement searchResultsNumber;

    public static final int SHORT_SLEPP_TIME = 500;
    public static final int LONG_SLEPP_TIME = 1000;

    /**
     * Returns a random number within a specific range
     */
    private int getRandomInt(int max, int min) {
        Random random = new Random();
        int num = random.nextInt((max - min) + 1) + min;
        return num;
    }

    /**
     * Returns a random character of a-z in lowercase and uppercase, using the ASCII table. Skipping the irrelevant characters between 90 - 97
     */
    public char getRandomChar() {
        int num = getRandomInt(122, 65); //
        if ((num > 90) && (num < 97)) {
            num += 7;
        }
        char cha = (char) num;

        return cha;
    }

    /**
     * Returns a random non number, non alphabet character, using the ASCII table. Commonly considered as invalid for search filed
     */
    public char getRandomInvalidChar() {
        return (char) getRandomInt(49,33);
    }

    /**
     * Returns a random special alphabet character. Commonly considered valid for search filed
     */
    public String getRandomSpecialChar() {
        String[] special = {"ä","ù","œ","ç","ž"};
        return special[(char) getRandomInt(special.length,0)];
    }

    /**
     * Returns a string at a defined length and char
     */
    public String createString(int howManyChars, char c){
        String s = "";
        for (int i = 0; i < howManyChars; i++) {
            s = s + c;
        }
        return s;
    }

    /**
     * Returns a random string of valid characters at a defined length
     */
    public String getRandomValidString(int howManyChars) {
        return createString(howManyChars, getRandomChar());
    }

    /**
     * Returns a random string of invalid (for search) characters at a defined length
     */
    public String getRandomInvalidString(int howManyChars) {
        return createString(howManyChars, getRandomInvalidChar());
    }

    /**
     * Perform a search action of a given String. Adding a sleep to assure page loading
     */
    public SpNaHomePage searchFor(String s) throws InterruptedException {
        searchField.sendKeys(s);
        Thread.sleep(SHORT_SLEPP_TIME);
        searchSubmitButton.click();
        return this;
    }

    /**
     * Perform the action of clearing the search filed from the searched value. Adding a sleep to assure page loading
     */
    public SpNaHomePage clearResults() throws InterruptedException {
        Thread.sleep(SHORT_SLEPP_TIME);
        newSearchButton.click();
        return this;
    }

    /**
     * Return a Boolean verification to the existence of search results. Adding a longer sleep to assure page loading
     */
    public Boolean verifySearchResultOutcome() throws InterruptedException {
        Thread.sleep(LONG_SLEPP_TIME);
        try {
            driver.findElementByCssSelector(".number-of-search-results-and-search-terms");
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Return a Boolean verification to the existence of an invalid search result, presented by an error. Adding a longer sleep to assure page loading
     */
    public Boolean verifySorryNoResultsMessage() throws InterruptedException {
        Thread.sleep(LONG_SLEPP_TIME);
        try {
            driver.findElementByCssSelector("#no-results-message>h2");
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
