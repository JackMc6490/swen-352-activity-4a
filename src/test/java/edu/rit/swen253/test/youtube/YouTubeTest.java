package edu.rit.swen253.test.youtube;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.youtube.*;
import edu.rit.swen253.test.AbstractWebTest;


public class YouTubeTest extends AbstractWebTest {
    // Logger object to display titles and urls of search results
    private static final Logger logger = Logger.getLogger(YouTubeTest.class.getName());


 
    @Test
    public void testSearch() {
        // Nav to home page
        YouTubeHomePage homepage = navigateToPage("https://www.youtube.com/", YouTubeHomePage::new); 

        // Search for "The Egg - A Short Story"
        homepage.search("\"The Egg - A Short Story\" kurzgesagt");

        // Transition to ResultsPage
        homepage.waitUntilGone();
        YouTubeResultsPage resultsPage = assertNewPage(YouTubeResultsPage::new);

        // Get each result and print it out
        for (YouTubeResultView result : resultsPage.getResults()) {
            logger.info(String.format("{Title: %s; Link: %s}", result.getTitle(), result.getLink()));
        }

        // Transition to result
        resultsPage.getResults().get(0).followResult();
        resultsPage.waitUntilGone();

        // Validate we navigated to the right page
        // By: Kurzgesagt. Title: The Egg - A Short Story
        final SimplePage resultPage = assertNewPage(SimplePage::new);
        assertAll("Page Assertions",
            () -> assertEquals("The Egg - A Short Story - Youtube", resultPage.getTitle()),
            () -> assertEquals("https://www.youtube.com/watch?v=h6fcK_fRYaI", resultPage.getURL())
        );
    }

}
