package edu.rit.swen253.page.youtube;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

public class YouTubeResultsPage extends AbstractPage {

    // This is the xpath leading to the content of the ytd-item-section-renderer
    // Which contains all the results in a list
    // Generated using Firefox Developer Tools
    private static final By RESULTS_FINDER = By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]");

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
        return results.findChildrenBy(By.xpath("ytd-video-renderer")).stream().map(YouTubeResultView::new).toList();
    }
}
