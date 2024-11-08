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




public class WikiTest extends AbstractWebTest{
    private static final Logger logger = Logger.getLogger(WikiTest.class.getName());

    @Test
    public void testSearch() throws InterruptedException{
       WikiHomePage homePage = navigateToPage("https://www.wikipedia.org/",WikiHomePage::new);
       homePage.searchFor("page object model");
       homePage.waitUntilGone();

       WikiResultsPage searchResults = assertNewPage(WikiResultsPage::new);
       for (WikiResultView result : searchResults.getSearchResults()) {
            logger.info(result.getName() + ": " + result.getLink());
       }

       searchResults.getSearchResults().get(0).clickLink();
       searchResults.waitUntilGone();
       final SimplePage contentPage = assertNewPage(SimplePage::new);
       assertAll("group assertions"
          , () -> assertEquals("Object model - Wikipedia", contentPage.getTitle())
          , () -> assertEquals("https://en.wikipedia.org/wiki/Object_model", contentPage.getURL())
    );

    }
}
