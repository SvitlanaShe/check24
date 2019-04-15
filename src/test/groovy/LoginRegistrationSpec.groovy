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

import geb.spock.GebReportingSpec
import pages.LoginPage
import pages.base.BasePage
import pages.common.CookieBannerPage
import pages.common.HomePage
import pages.common.PunkteModalPage
import pages.common.RegistrationPage
import spock.lang.Ignore
import spock.lang.Shared

class LoginRegistrationSpec extends GebReportingSpec {
    @Shared
            cfg
    @Shared
            username
    @Shared
            password

    def setupSpec() {
        cfg = ConfigReader.getConfiguration()
        BasePage.config = cfg
        driver.manage().window().maximize()
        username = cfg.loginFlowConfig.username
        password = cfg.loginFlowConfig.password
    }

    def cleanupSpec() {
    }

    def setup() {
        baseUrl = cfg.baseurl
    }

    def "Login with valid username and password"() {
        given: "You are on Check24 homepage"
        to HomePage
        report("Check24 Home Page")
        HomePage homePage = at HomePage

        when: "You close cookie banner clicking OK button"
        CookieBannerPage cookieBannerPage = at CookieBannerPage
        cookieBannerPage.clickOkButton()

        then: "You are on Home page"
        at HomePage

        when: "You click on Login button"
        homePage.check24Header.clickOnLoginLinkInHeader()

        then: "Check you are on login page"
        LoginPage loginPage = at LoginPage
        report("Check24 Login Page")

        when: "Enter valid Login & Password and click Login button"
        loginPage.enterLoginPassword(username, password)
        report("Check24 Login Page with email and password")
        loginPage.clickOnLoginButton()

        then: "Check you are on Punkte Modal page"
        at PunkteModalPage
        report("Check24 Punkte Modal Page for username - " + username)
    }

    def "User could be registered"() {
        given: "You are on Check24 homepage"
        to HomePage
        report("Check24 Home Page")
        HomePage homePage = at HomePage

        when: "You close cookie banner clicking OK button"
        CookieBannerPage cookieBannerPage = at CookieBannerPage
        cookieBannerPage.clickOkButton()

        then: "You are on Home page"
        at HomePage

        when: "You click on Login button"
        homePage.check24Header.clickOnLoginLinkInHeader()

        then: "Check you are on login page"
        LoginPage loginPage = at LoginPage
        report("Check24 Login Page")

        when: "Click on 'New Customer start here' link"
        loginPage.clickOnNewCustomerStartHereLink()

        then:"Check you are on Registration page"
        RegistrationPage registrationPage = at RegistrationPage
        report("Check24 Registration Page")

        when: "Enter valid Login & Password & RepeatPassword and click Register button"
        registrationPage.enterLoginPasswordRepeatPassword()
        report("Check24 Registration Page with email and password and repeat password")
        registrationPage.clickOnRegistrationButton()

        then:"User account page is opened"
        at PunkteModalPage
        report("Check24 Punkte Modal Page for username - " + username)

    }
}