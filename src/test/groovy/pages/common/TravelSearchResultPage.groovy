package pages.common

import pages.base.BasePage

class TravelSearchResultPage extends BasePage {

    static url = config.urlaubUrl + "/suche/hotel"

    private static final FORM_TITLE = "Hotels für Ihre Mallorca-Pauschalreise".toUpperCase()

    static content = {
        searchResultsTitle() { $('h3 span[class="js-hotellist-headline-dynamic-text"]').getAttribute("title") }
        resultsCount() {
            $('.cheapest-lowest-price.js-hotel-headline.js-headline-multiple-hotels  .js-deferred-count.deferred-count')
        }
    }

    static at = {
        waitFor(driver.getCurrentUrl().toLowerCase().contains(url))
        searchResultsTitle.toUpperCase().contains(FORM_TITLE)
    }

    def getSearchResultsCount() {
        return resultsCount.text()
    }
}
