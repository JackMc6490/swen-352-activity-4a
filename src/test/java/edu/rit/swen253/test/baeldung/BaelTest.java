package edu.rit.swen253.test.baeldung;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.baeldung.BaelHomePage;
import edu.rit.swen253.page.baeldung.BaelResultView;
import edu.rit.swen253.page.baeldung.BaelResultsPage;
import edu.rit.swen253.test.AbstractWebTest;

/**
 * A test class that searches Baeldung for "javascript" and explores the
 * results. Inspired by Jack McCarthy's WikiTest class.
 * 
 * @author Liam Weiner
 */
public class BaelTest extends AbstractWebTest {
    // Logger object is used to display the title and URL of every search result
    private static final Logger logger = Logger.getLogger(BaelTest.class.getName());

    /**
     * Single test that performs two actions
     * First, naviagetes to Baeldung's homepage and searches for "javascript"
     * The title and URL of every search result is then logged
     * Then, we click on the first search result and validate we navigate to the
     * expected page
     */
    @Test
    public void testSearch() {
        // Navigate to Baeldung's homepage
        BaelHomePage homePage = navigateToPage("https://www.baeldung.com/", BaelHomePage::new);

        // Dismiss the privacy popup if it is present
        homePage.dismissPrivacyPopup();
        // Search for "javascript"
        homePage.clickOnMagnifyingGlass();
        homePage.searchFor("javascript");


        // Validate we have navigated to the search results page
        homePage.waitUntilGone();
        BaelResultsPage resultsPage = assertNewPage(BaelResultsPage::new);

        // For each result view on the results page, log it's title and URL
        for (BaelResultView result : resultsPage.getSearchResults()) {
            logger.info("{Title: " + result.getName() + ", Link: " + result.getLink() +
                    "}");
        }

        // Click the first result
        resultsPage.getSearchResults().get(0).clickLink();
        resultsPage.waitUntilGone();

        // Validate that we navigated to the expected page
        final SimplePage contentPage = assertNewPage(SimplePage::new);
        assertAll("group assertions", () -> assertEquals("Reading a JSP Variable From JavaScript | Baeldung", contentPage.getTitle().trim()),
                () -> assertEquals("https://www.baeldung.com/java-jsp-read-variable-js",
                        contentPage.getURL()));

    }
}
