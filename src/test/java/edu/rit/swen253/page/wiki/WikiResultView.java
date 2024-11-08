package edu.rit.swen253.page.wiki;

import java.util.List;

import javax.swing.text.html.HTML;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;
import edu.rit.swen253.utils.SeleniumUtils;

public class WikiResultView {
    private final DomElement viewContainer;
    private final DomElement link;

    public WikiResultView(final DomElement viewContainer){
        this.viewContainer = viewContainer;
        this.link = viewContainer.findChildBy(HtmlUtils.ANCHOR_FINDER);
    }

    public void clickLink() {
        link.click();
    }

    public String getLink(){
        return this.link.getAttribute("href");
    }

    public String getName(){
        return this.link.getAttribute("title");
    }



}
