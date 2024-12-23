package edu.rit.swen253.page.baeldung;

import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;

public class BaelResultView {
    private final DomElement link;

    /**
     * The constructor for BaelResultView, takes the passed in element and uses 
     * findChildBy to find the link as that is the only information we need to log for testing 
     * @param viewContainer: One article element in the unordered list
     */
    public BaelResultView(final DomElement viewContainer){
        this.link = viewContainer.findChildBy(HtmlUtils.ANCHOR_FINDER);
    }

    /**
     * Clicks the link found in the article element
     */
    public void clickLink() {
        link.click();
    }

    /**
     * Returns the url found in the <a> tag of the article element
     * @return: the full URL of the search result
     */
    public String getLink(){
        return this.link.getAttribute("href");
    }
    /**
     * Returns the title found in the <a> tag of the article element
     * @return: the title of the search result
     */
    public String getName(){
        return this.link.getAttribute("title");
    }
}
