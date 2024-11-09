package edu.rit.swen253.page.wiki;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;
/**
 * A Page Object for the Wikipedia home page.
 *
 * @author Jack McCarthy
 */
public class WikiHomePage extends AbstractPage {
    //A css selector that can be used to find the input field on the home page
    private static final By SEARCH_FINDER = By.cssSelector("main > div.search-container > form.pure-form > fieldset > div.search-input > input");
    //A dom element used to represent the <input> tag
    private DomElement searchinput;

    /**
     * The constructor for a WikiHomePage 
     * Uses the css selector to find the <input> tag are store it in the DomElement
     */
    public WikiHomePage(){
        super();
        try {
            searchinput = findOnPage(SEARCH_FINDER);    
        } 
        catch (TimeoutException e) {
            fail("Input field not found");      
        }
    }
    /**
     * Makes a search on the Wikipedia input field 
     * @param search: The string to search for
     */
    public void searchFor(String search){
        searchinput.enterText(search);
        Actions action = SeleniumUtils.makeAction().sendKeys(Keys.ENTER);
        action.perform();
    }


}
