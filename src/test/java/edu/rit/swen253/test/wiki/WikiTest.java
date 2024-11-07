package edu.rit.swen253.test.wiki;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.wiki.WikiHomePage;
import edu.rit.swen253.page.wiki.WikiSearchResults;
import edu.rit.swen253.page.wiki.WikiSingleResult;
import edu.rit.swen253.test.AbstractWebTest;




public class WikiTest extends AbstractWebTest{
    private static final Logger logger = Logger.getLogger(WikiTest.class.getName());

    @Test
    public void testSearch() throws InterruptedException{
       WikiHomePage homePage = navigateToPage("https://www.wikipedia.org/",WikiHomePage::new);
       homePage.searchFor("Cat S");
       homePage.waitUntilGone();

       WikiSearchResults searchResults = assertNewPage(WikiSearchResults::new);
       for (WikiSingleResult result : searchResults.getSearchResults()) {
            logger.info(result.getLink());
       }

       searchResults.getSearchResults().get(0).clickLink();
       searchResults.waitUntilGone();
       final SimplePage contentPage = assertNewPage(SimplePage::new);
       assertAll("group assertions"
          , () -> assertEquals("Cat - Wikipedia", contentPage.getTitle())
          , () -> assertEquals("https://en.wikipedia.org/wiki/Cat", contentPage.getURL())
    );

    }
}
