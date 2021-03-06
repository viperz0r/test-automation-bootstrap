package io.company.tests.examples;

import io.company.pageobjects.pages.GoogleResultsPage;
import io.company.pageobjects.pages.GoogleSearchPage;
import io.company.tests.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.open;

public class GoogleSearchTest extends BaseTest {
    private GoogleSearchPage googleSearchPage;
    private GoogleResultsPage googleResultsPage;

    /**
     * Initialize private objects.
     */
    @BeforeMethod
    public void setup() {
        logger().debug("Setup test");
        googleSearchPage = new GoogleSearchPage();
        googleResultsPage = new GoogleResultsPage();
    }

    @AfterMethod
    public void teardown() {
        logger().debug("Teardown test");
    }

    @Test(description = "Open up a google page and search for the word 'dogs'")
    public void shouldPerformSearch() {
        open("https://google.com");

        googleSearchPage
                .searchComponent()
                .searchFor("dogs")
                .searchComponent()
                .self()
                .shouldHave(value("dogs"));
    }

    @Test
    public void shouldSearchResultsBeDisplayed() {
        open("https://www.google.pt/search?source=hp&q=dogs&oq=dogs");

        googleResultsPage
                .getResults()
                .shouldHave(sizeGreaterThan(1));
    }
}
