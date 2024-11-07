package edu.rit.swen253.page.wiki;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;
import edu.rit.swen253.utils.SeleniumUtils;

public class WikiSingleResult {
    private final DomElement viewContainer;
    private final DomElement link;
    //private final DomElement name;

    public WikiSingleResult(final DomElement viewContainer){
        this.viewContainer = viewContainer;
        this.link = viewContainer.findChildBy(HtmlUtils.ANCHOR_FINDER);
        //this.name = viewContainer.findChildBy(By.cssSelector("div.searchResultImage > div.searchResultImage-text > div.mw-search-result-heading > a > span"));
    }

    public void clickLink() {
        link.click();
    }

    public String getLink(){
        return this.link.getAttribute("href");
    }



}
