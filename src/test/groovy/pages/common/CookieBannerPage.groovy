package pages.common

import pages.base.BasePage

class CookieBannerPage extends BasePage {

    static url = ""

    static content = {
        cookieBannerBlock() { $('body .c24-cookie.c24-cookie-animate') }
        okButton() { cookieBannerBlock.find('a.c24-cookie-button') }
    }

    static at = {
        cookieBannerBlock.isDisplayed()
        okButton.isDisplayed()
    }

    def clickOkButton() {
        okButton.click()
    }
}
