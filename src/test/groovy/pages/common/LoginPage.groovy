/*
 * Copyright (c) 2018. https://automationschool.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package pages

import pages.base.BasePage

class LoginPage extends BasePage {

    static url = config.kundenbereichUrl + "/user/login"

    private static final FORM_TITLE = "Sie haben ein Konto? Melden Sie sich an.".toUpperCase()

    static content = {
        anmeldungBreadcrumb() { $('li a[href="/user/login.html"]') }
        loginFormContainer() { $('#c24-page-container-content') }
        loginFormTitle() { loginFormContainer.find('.c24-kb-headline') }
        loginForm() { loginFormContainer.find('#c24-content') }
        usernameField() { loginForm.find('input#email') }
        passwordField() { loginForm.find('input#password') }
        loginButton() { loginForm.find('button[name="login"]') }
        newCustomerStartHereLink() { $('.c24-kb-form-button-row a') }
    }

    static at = {
        loginFormTitle.text().toUpperCase().contains(FORM_TITLE)
        browser.getCurrentUrl().toLowerCase().startsWith(url)
        loginButton.isDisplayed()
        anmeldungBreadcrumb.isDisplayed()
        usernameField.isDisplayed() && passwordField.isDisplayed()
        newCustomerStartHereLink.isDisplayed()
    }

    def enterLoginPassword(login, password) {
        usernameField.value(login)
        passwordField.value(password)
    }

    def clickOnLoginButton() {
        loginButton.click()
    }

    def clickOnNewCustomerStartHereLink() {
        newCustomerStartHereLink.click()
    }

}