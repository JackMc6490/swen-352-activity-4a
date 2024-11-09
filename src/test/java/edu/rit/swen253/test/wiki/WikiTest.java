package edu.rit.swen253.test.wiki;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.wiki.WikiHomePage;
import edu.rit.swen253.page.wiki.WikiResultsPage;
import edu.rit.swen253.page.wiki.WikiResultView;
import edu.rit.swen253.test.AbstractWebTest;



/**
 * A test class that searches Wikipedia for "page object model" and explores the results.
 *
 * @author Jack McCarthy
 */
public class WikiTest extends AbstractWebTest{
    //Logger object is used to display the title and URL of every search result
    private static final Logger logger = Logger.getLogger(WikiTest.class.getName());



    /**
     * Single test that performs two actions
     * First, naviagetes to Wikipedia's homepage and searches for "page object model"
     * The title and URL of every search result is then logged
     * Then, we click on the first search result and validate we navigate to the expected page
     */
    @Test
    public void testSearch(){
       //Navigate to Wikipedia's homepage 
       WikiHomePage homePage = navigateToPage("https://www.wikipedia.org/",WikiHomePage::new);

       //Search for "page object model"
       homePage.searchFor("page object model");
       
       //Validate we have navigated to the search results page
       homePage.waitUntilGone();
       WikiResultsPage resultsPage = assertNewPage(WikiResultsPage::new);
       
       //For each result view on the results page, log it's title and URL 
       for (WikiResultView result : resultsPage.getSearchResults()) {
            logger.info("{Title: " + result.getName() + ", Link: " + result.getLink() + "}");
       }

       //Click the first result
       resultsPage.getSearchResults().get(0).clickLink();
       resultsPage.waitUntilGone();

       //Validate that we navigated to the expected page
       final SimplePage contentPage = assertNewPage(SimplePage::new);
       assertAll("group assertions"
          , () -> assertEquals("Object model - Wikipedia", contentPage.getTitle())
          , () -> assertEquals("https://en.wikipedia.org/wiki/Object_model", contentPage.getURL())
    );

    }
}
