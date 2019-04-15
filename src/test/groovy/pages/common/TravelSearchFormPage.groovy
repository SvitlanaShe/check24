package pages.common

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import pages.base.BasePage

class TravelSearchFormPage extends BasePage {

    static url = config.urlaubUrl

    private static final FORM_TITLE = "Urlaubsreisen beim Preissieger".toUpperCase()
    private static final FORM_SUB_TITLE = "Jetzt die besten Angebote für Ihren Urlaub vergleichen".toUpperCase()

    static content = {
        searchFormTitle() { $('h1.c24-travel-heading') }
        searchFormSubTitle() { $('h2.c24-travel-sub-heading') }
        flyAndHotelForm() { $('.c24-travel-searchform-wrapper.c24-travel-js-api-dynamic-visible-normal-form-layer') }
        destinationInput() { $('.c24-travel-input-wrapper #c24-travel-destination-element') }
        travelAirportInput() {
            $('.c24-travel-form-field.c24-travel-js-form-ele-wrapper.c24-travel-airport-field.c24-travel-instant-load')
        }
        travelDurationSelect() { $('.c24-travel-duration-overlay.c24-travel-js-open-duration-layer') }
        travelDepartureDateInput() { $('div.c24-travel-tablet-input-overlay.c24-travel-empty', 0) }
        travelReturnDateInput() { $(By.xpath('//input[@name="returnDate"]/../div[1]')) }

        searchButton() { $('button[name="searchButton"]') }
    }

    static at = {
        searchFormSubTitle.text().toUpperCase().contains(FORM_SUB_TITLE)
        browser.getCurrentUrl().toLowerCase().startsWith(url)
        searchButton.isDisplayed()
    }

    def fillDepartureAirportField(String airport) {
        waitFor { travelAirportInput.isDisplayed() }
        travelAirportInput.click()
        $(".c24-travel-list-item[data-val=\"${airport}\"]", 0).click()
    }

    def fillDestinationField(String destination) {
        destinationInput.click()
        destinationInput << destination
        waitForTimeInMillisecinds(500)
        destinationInput << Keys.ENTER
    }


    def fillDepartureDateField(String departureDate) {
        $(By.xpath('//div[contains(@class,"c24-travel-js-datefinder")]//span[@class="c24-travel-js-close-ele c24-travel-close-ele"]')).click()
        System.out.println("In fillDepartureDateField")
        travelDepartureDateInput.click()
        travelDepartureDateInput << departureDate
        $(By.xpath('//div[contains(@class,"c24-travel-js-datefinder")]//span[@class="c24-travel-js-close-ele c24-travel-close-ele"]')).click()
    }

    def fillReturnDateField() {
        $('span.c24-travel-js-close-ele.c24-travel-close-ele').click()
        travelReturnDateInput.click()
        waitForTimeInMillisecinds(500)
        $('span.c24-travel-js-close-ele.c24-travel-close-ele').click()
        waitForTimeInMillisecinds(500)
    }

    def clickOnSearchButton() {
        waitForTimeInMillisecinds(500)
        searchButton.click()
    }

    def fillDuration(String duration) {
        travelDurationSelect.click()
        switch (duration) {
            case "1 week":
                $('.c24-travel-duration-col.c24-travel-first-duration-col span', 2).click()
                break
            case "2 weeks":
                $('.c24-travel-duration-col.c24-travel-first-duration-col span', 3).click()
                break
            default:
                $('.c24-travel-duration-col.c24-travel-first-duration-col span', 2).click()
                break
        }

    }
}
