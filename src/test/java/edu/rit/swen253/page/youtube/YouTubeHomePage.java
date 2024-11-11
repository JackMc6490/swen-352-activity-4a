package edu.rit.swen253.page.youtube;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

/**
 * Page object for the YouTube Home page.
 * 
 * @author Swapnil Acharjee
 */

public class YouTubeHomePage extends AbstractPage {

    private static final By SEARCH_FINDER = By.name("search_query");

    private DomElement searchElement;


    /**
     * Constructor for YouTubeHomePage
     * Finds the searchElement by using its unique name.
     */
    public YouTubeHomePage(){
        super();
        try {
            searchElement = findOnPage(SEARCH_FINDER);
        } catch (TimeoutException e) {
            fail("Input field not found");
        }
    }

    /**
     * Search the YouTube site using the input field found
     * @param search: A string to search for
     */
    public void search(String search) {
        searchElement.enterText(search);
        Actions action = SeleniumUtils.makeAction().sendKeys(Keys.ENTER);
        action.perform();
    }

    /**
     * Overrides waitUntilGone because YouTube has persistence of all elements
     * Waits on <ytd-browse> element to become invisible
     */
    @Override
    public void waitUntilGone() {
        FluentWait<WebDriver> wait = SeleniumUtils.getShortWait();
        DomElement browseElement = findOnPage(By.tagName("ytd-browse"));
        wait.until(ExpectedConditions.invisibilityOf(browseElement.getWebElement()));
    }

}