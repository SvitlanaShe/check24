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

package pages.base

import geb.Page
import modules.HeaderModule

import java.text.SimpleDateFormat

abstract class BasePage extends Page {

    static ConfigObject config

    static at = {
        //we will check this for all  pages derived from this page
        browser.getCurrentUrl().toLowerCase().startsWith(config.urlBase)
    }

    static content = {
        check24Header(required: false, wait: true) { module HeaderModule }
    }

    def getUniqEmail() {
        Date now = new Date()
        SimpleDateFormat timestamp = new SimpleDateFormat("yyMMddHHmmss")
        def date = timestamp.format(now)
        return "check24.test+$date@gmail.de"
    }

    def waitForTimeInMillisecinds(int timeInMillisconds) {
        try {
            Thread.sleep(timeInMillisconds)
        }
        catch (Exception e) {

        }
    }

}
