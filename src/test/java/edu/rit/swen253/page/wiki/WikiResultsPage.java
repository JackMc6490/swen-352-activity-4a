package edu.rit.swen253.page.wiki;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

public class WikiResultsPage extends AbstractPage {
    //copied full Xpath from edge's devoloper tools
    private static final By RESULTS_FINDER = By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[2]/div[4]/ul");
    private DomElement searchResults;

    public WikiResultsPage(){
        super();
        try {
            searchResults = findOnPage(RESULTS_FINDER);    
        } 
        catch (TimeoutException e) {
            fail("Search results not found");      
        }
    }

    public List<WikiResultView> getSearchResults(){
        return searchResults.findChildrenBy(By.xpath("li")).stream().map(WikiResultView::new).toList();
    }
}