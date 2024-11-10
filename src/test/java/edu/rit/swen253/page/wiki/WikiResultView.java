package edu.rit.swen253.page.wiki;

import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;

/**
 * A View Object class that represents a single li element in the unordered list that makes up Wikipedia search results
 * 
 * Example:
 * 
 * <li class="mw-search-result mw-search-result-ns-0">
 *      <div class="searchResultImage">
 *            <div class="searchResultImage-thumbnail">
 *                  <div class="searchResultImage-thumbnail-placeholder" aria-hidden="true"><!--?xml version="1.0" encoding="UTF-8"?-->
 *                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20">
 *                              <title>
 *		                            image layout frameless
 *	                            </title>
 *                              <path d="M19 3H1v14h18zM3 14l3.5-4.5 2.5 3L12.5 8l4.5 6z"></path>
 *                              <path d="M19 5H1V3h18zm0 12H1v-2h18z"></path>
 *                        </svg>
 *                  </div>
 *           </div>
 *           <div class="searchResultImage-text">
 *               <div class="mw-search-result-heading">
 *                   <a href="/wiki/Object_model" title="Object model" data-serp-pos="0">
 *                       <span class="searchmatch">Object</span> 
 *                       <span class="searchmatch">model</span>
 *                   </a>    
 *               </div>
 *               <div class="searchresult"> 
 *                   Examples are the 
 *                   <span class="searchmatch">object</span> 
 *                   <span class="searchmatch">models</span> 
 *                   of Java, the Component 
 *                   <span class="searchmatch">Object</span> 
 *                   <span class="searchmatch">Model</span> 
 *                   (COM), or 
 *                   <span class="searchmatch">Object</span>-
 *                   <span class="searchmatch">Modeling</span> 
 *                   Technique (OMT). Such 
 *                   <span class="searchmatch">object</span> 
 *                   <span class="searchmatch">models</span> 
 *                   are usually defined...
 *               </div> 
 *               <div class="mw-search-result-data">5 KB (602 words) - 13:45, 4 August 2023</div>
 *           </div>
 *       </div>
 *   </li>
 * @author Jack McCarthy
 */
public class WikiResultView {
    //The <a> tag found in the <li> element
    private final DomElement link;

    /**
     * The constructor for WikiResultView, takes the passed in element and uses 
     * findChildBy to find the link as that is the only information we need to log for testing 
     * @param viewContainer: One <li> element in the unordered list
     */
    public WikiResultView(final DomElement viewContainer){
        this.link = viewContainer.findChildBy(HtmlUtils.ANCHOR_FINDER);
    }

    /**
     * Clicks the link found in the <li> element
     */
    public void clickLink() {
        link.click();
    }

    /**
     * Returns the url found in the <a> tag of the <li> element
     * @return: the full URL of the search result
     */
    public String getLink(){
        return this.link.getAttribute("href");
    }
    /**
     * Returns the title found in the <a> tag of the <li> element
     * @return: the title of the search result
     */
    public String getTitle(){
        return this.link.getAttribute("title");
    }



}
