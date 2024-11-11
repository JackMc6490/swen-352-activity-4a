package edu.rit.swen253.page.baeldung;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

/**
 * A Page Object for the
 * 
 * @author Liam Weiner
 */
public class BaelResultsPage extends AbstractPage {
    // An ID selector that can be used to find the search bar on the home page
    private static final By RESULTS_FINDER = By
            .cssSelector("#main > div.archive-columns.layout-stacked.click-whole.no-more.rounded-on");

    // A dom element used to represent the list of search results
    private DomElement results;

    /**
     * The constructor for a BaelHomePage
     * Uses the ID selector to find the <input> tag and store it in the DomElement
     */
    public BaelResultsPage() {
        super();
        try {
            results = findOnPage(RESULTS_FINDER);
        } catch (TimeoutException e) {
            fail("Search results not found");
        }
    }

    /**
     * This fucntion is used to turn all the <li> tags in the unordered list into BaelResultView objects
     * @return a list of all the <li> elements on the first page of search results, translated into BaelResultView objects
     */
    public List<BaelResultView> getSearchResults(){
        return results.findChildrenBy(By.xpath("article")).stream().map(BaelResultView::new).toList();
    }

}
