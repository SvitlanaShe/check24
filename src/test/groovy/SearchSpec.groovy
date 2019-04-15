import geb.spock.GebReportingSpec
import pages.base.BasePage
import pages.common.CookieBannerPage
import pages.common.HomePage
import pages.common.TravelSearchFormPage
import pages.common.TravelSearchResultPage
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class SearchSpec extends GebReportingSpec {
    @Shared
            cfg

    def setupSpec() {
        cfg = ConfigReader.getConfiguration()
        BasePage.config = cfg
        driver.manage().window().maximize()
    }

    def cleanupSpec() {
    }

    def setup() {
        baseUrl = cfg.baseurl
    }

    def "User is able to fill valid search data "() {
        given: "You are on Check24 homepage"
        to HomePage
        report("Check24 Home Page")
        HomePage homePage = at HomePage
        when: "You click on Travel tab"
        homePage.check24Header.clickOnTravelTab()

        then: "Check you are on travel initial page and close cookie banner"
        CookieBannerPage cookieBannerPage = at CookieBannerPage
        cookieBannerPage.clickOkButton()
        TravelSearchFormPage travelSearchFormPage = at TravelSearchFormPage
        report("Check24 travel Page")

        when: "Enter valid data to search fields and click Search button"
        travelSearchFormPage.fillDestinationField("Mallorca, Spanien")
        travelSearchFormPage.fillDepartureAirportField("STR")
        travelSearchFormPage.fillDuration("1 week")
        travelSearchFormPage.fillReturnDateField()

        then:"Search started and load image is visible to you"
        report("Check24 load image is displayed on the Page")

    }

    def "Search results list is not empty on Search Travel Results page"() {
        given: "YOu are on Search travel form page"
        when: "Check you are on Travel Search Result page"
        TravelSearchFormPage travelSearchFormPage = at TravelSearchFormPage

        then: "Search button could be clicked"
        travelSearchFormPage.clickOnSearchButton()
        report("Check24 Travel Form Page search button clicked")

        when:"On travel search results page"
        TravelSearchResultPage travelSearchResultPage = at TravelSearchResultPage
        report("Check24 Travel Search Results Page is opened")

        then: "Verify Count of search possibilities more than 1"
        assert Integer.parseInt(travelSearchResultPage.getSearchResultsCount()) > 1
    }

}
