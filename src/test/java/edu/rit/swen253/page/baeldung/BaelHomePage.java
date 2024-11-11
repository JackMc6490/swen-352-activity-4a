package edu.rit.swen253.page.baeldung;

import static org.junit.jupiter.api.Assertions.fail;
import org.openqa.selenium.By;
import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

/**
 * A Page Object for the Baeldung home page.
 * 
 * @author Liam Weiner
 */
public class BaelHomePage extends AbstractPage {
    // An ID selector that can be used to find the search bar on the home page
    private static final By PRIVACY_POPUP_DISMISS = By.cssSelector("#qc-cmp2-usp > button");
    private static final By MAGNIFYING_GLASS_FINDER = By.id("menu-item-40489");
    private static final By SEARCH_FINDER = By.id("search");

    // A dom element used to represent the <input> tag
    private DomElement privacyPopupDismiss;
    private DomElement searchinput;
    private DomElement magnifyingGlass;

    /**
     * The constructor for a BaelHomePage
     * Uses the ID selector to find the <input> tag and store it in the DomElement
     */
    public BaelHomePage() {
        super();
        privacyPopupDismiss = findOnPage(PRIVACY_POPUP_DISMISS);
        magnifyingGlass = findOnPage(MAGNIFYING_GLASS_FINDER);

    }


    /**
     * Dismisses the privacy popup if it is present
     */
    public void dismissPrivacyPopup() {
        if (privacyPopupDismiss != null) {
            privacyPopupDismiss.click();
        }
    }

    /**
     * Clicks on the magnifying glass to start the search
     */
    public void clickOnMagnifyingGlass() {
        magnifyingGlass.click();
    }

    /**
     * Makes a search on the Baeldung input field
     * @param search: The string to search for
     */
    public void searchFor(String search) {
        searchinput = findOnPage(SEARCH_FINDER);
        searchinput.enterText(search);
        searchinput.submit();
    }
    


}
