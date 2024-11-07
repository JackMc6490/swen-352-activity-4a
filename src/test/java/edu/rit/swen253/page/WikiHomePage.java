package edu.rit.swen253.page;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;

import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

public class WikiHomePage extends AbstractPage {

    private static final By SEARCH_FINDER = By.cssSelector("main > div.search-container > form.pure-form > fieldset > div.search-input > input");
    private DomElement searchinput;

    public WikiHomePage(){
        super();
        try {
            searchinput = findOnPage(SEARCH_FINDER);    
        } 
        catch (TimeoutException e) {
            fail("Input field not found");      
        }
    }

    public void searchFor(String search){
        searchinput.enterText(search);
        Actions action = SeleniumUtils.makeAction().sendKeys(Keys.ENTER);
        action.perform();
    }


}
