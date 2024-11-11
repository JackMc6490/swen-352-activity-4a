package edu.rit.swen253.page.youtube;

import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;

public class YouTubeResultView {

    // The full element for the ytd-video-renderer
    private final DomElement element;

    /** 
     * The constructor for YouTubeResultView
     * All subsequent functions will access inner 
     * data
     * @param element: The <ytd-video-renderer>
     */
    public YouTubeResultView(final DomElement element) {
        this.element = element;
    }

    /**
     * Finds and clicks the link within the element
     */
    public void followResult() {
        element.findChildBy(By.id("video-title")).click();
    }

    /**
     * Returns the url within the <a> element of the result
     * @return: full URL of search result
     */
    public String getLink() {
        return element.findChildBy(By.id("video-title")).getAttribute("href");
    }

    /**
     * Returns the title for the search result
     * @return: title of the search result
     */
    public String getTitle() {
        return element.findChildBy(By.tagName("yt-formatted-string")).getText();
    }
}
