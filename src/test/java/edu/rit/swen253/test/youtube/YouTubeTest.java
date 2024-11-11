package edu.rit.swen253.test.youtube;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.youtube.*;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class YouTubeTest extends AbstractWebTest {
    // Logger object to display titles and urls of search results
    private static final Logger logger = Logger.getLogger(YouTubeTest.class.getName());

    private YouTubeHomePage homepage;
    private YouTubeResultsPage resultsPage;

    @Test
    @Order(1)
    public void navToHome() {
        // Nav to home page
        homepage = navigateToPage("https://www.youtube.com/", YouTubeHomePage::new); 
    }

    @Test
    @Order(2)
    public void search() {
        // Search for "page object model"
        homepage.search("page object model");
        homepage.waitUntilGone();
    }

    @Test
    @Order(3)
    public void getResults() {
        resultsPage = assertNewPage(YouTubeResultsPage::new);

        // Get each result and print it out
        for (YouTubeResultView result : resultsPage.getResults()) {
            logger.info(String.format("{Title: %s; Link: %s}", result.getTitle(), result.getLink()));
        }
    }

    @Test
    @Order(4)
    public void navToResult() {
        // Transition to result
        resultsPage.getResults().get(0).followResult();
        resultsPage.waitUntilGone();

        // Validate we navigated to the right page
        // By: Kurzgesagt. Title: The Egg - A Short Story
        final SimplePage resultPage = assertNewPage(SimplePage::new);
        assertAll("Page Assertions",
            () -> assertEquals("YouTube", resultPage.getTitle()),
            () -> assertEquals("https://www.youtube.com/watch?v=GKDJk4s_T-s", resultPage.getURL())
        );
    }
}
