package edu.rit.swen253.page.youtube;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

public class YouTubeResultsPage extends AbstractPage {

    // This is the xpath leading to the content of the ytd-item-section-renderer
    // Which contains all the results in a list
    // Generated using Firefox Developer Tools
    private static final By RESULTS_FINDER = By.tagName("ytd-item-section-renderer");
    //private static final By RESULTS_FINDER = By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]");

    private DomElement results;

    /**
     * Constructor for YouTubeResultsPage
     * Uses the xpath to get the ytd-item-section-renderer
     * and save it in results
     */
    public YouTubeResultsPage() {
        super();
        try {
            results = findOnPage(RESULTS_FINDER);
        } catch (TimeoutException e) {
            fail("Results not found :(");
        }
    }

    /**
     * Function that returns all <ytd-video-renderer> elements
     * within the 
     * @return
     */
    public List<YouTubeResultView> getResults() {
        return results.findChildrenBy(By.tagName("ytd-video-renderer")).stream().map(YouTubeResultView::new).toList();
    }

    /**
     * Overrides waitUntilGone because YouTube has persistence of all elements
     * Waits on <ytd-search> element to become invisible
     */
    @Override
    public void waitUntilGone() {
        FluentWait<WebDriver> wait = SeleniumUtils.getShortWait();
        DomElement browseElement = findOnPage(By.tagName("ytd-search"));
        wait.until(ExpectedConditions.invisibilityOf(browseElement.getWebElement()));
    }
}
