package edu.rit.swen253.page.wiki;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

/**
 * A Page Object for the Wikipedia's search results page.
 *
 * @author Jack McCarthy
 */
public class WikiResultsPage extends AbstractPage {
    //An xpath used to find the unordered list on the results page
    //Created using "copy full Xpath" from edge's devoloper tools
    private static final By RESULTS_FINDER = By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[2]/div[4]/ul");
    //The unordered list found on the search results page
    private DomElement searchResults;

    /**
     * The constructor for a WikiResultsPage
     * Uses the global xpath to find the <ul> tag and turn it into a Domelement
     */
    public WikiResultsPage(){
        super();
        try {
            searchResults = findOnPage(RESULTS_FINDER);    
        } 
        catch (TimeoutException e) {
            fail("Search results not found");      
        }
    }

    /**
     * This fucntion is used to turn all the <li> tags in the unordered list into WikiResultView objects
     * @return a list of all the <li> elements on the first page of search results, translated into WikiResultView objects
     */
    public List<WikiResultView> getSearchResults(){
        return searchResults.findChildrenBy(By.xpath("li")).stream().map(WikiResultView::new).toList();
    }
}