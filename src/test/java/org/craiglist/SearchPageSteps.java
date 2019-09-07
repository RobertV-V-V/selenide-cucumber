package org.craiglist;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.google.common.base.CharMatcher;
import com.google.inject.Inject;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.craiglist.page.objects.SearchPage;
import org.junit.AssumptionViolatedException;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;

@ScenarioScoped
public class SearchPageSteps {

    @Inject
    public SearchPage searchPage;


    @When("I sort items by (lowest price|highest price|newest|relevant)")
    public void sortBy(String type) {
        String selector = getSelectorForSortingOption(type);

        searchPage.sortingSelector().click();
        searchPage.sortingSelector().find(selector).click();
    }

    @When("I see that (lowest price|highest price|newest|relevant|upcoming) sorting option is available")
    public void sortingOptionAvailable(String type) {
        String selector = getSelectorForSortingOption(type);

        searchPage.sortingSelector().find(selector).shouldBe(visible);
    }

    @When("I open sorting selector")
    public void openSortingSelector() {
        searchPage.sortingSelector().click();
    }

    @When("I apply search")
    public void applySearch() {
        searchPage.searchInput().shouldBe(visible).sendKeys("helsinki");
        searchPage.searchButton().click();
    }

    @When("I see that items are sorted by (lowest price|highest price)")
    public void assertSortedBy(String type) {
        ElementsCollection items = searchPage.items();
        ArrayList<Integer> prices = collectPrices(items);

        try{
            switch (type) {
                case "lowest price":
                    isArraySortedByType(prices, Sorting.ASC);
                    break;
                case "highest price":
                    isArraySortedByType(prices, Sorting.DESC);
                    break;
                default:
                    throw new IllegalArgumentException("Wrong sorting type provided");
            }
        } catch (RuntimeException e) {
            throw new AssumptionViolatedException("The grid is not sorted by " + type);
        }
    }

    private String getSelectorForSortingOption(String type) {
        String selector;

        switch (type) {
            case "lowest price":
                selector = "[data-selection='priceasc']";
                break;
            case "highest price":
                selector = "[data-selection='pricedsc']";
                break;
            case "newest":
                selector = "[data-selection='date']";
                break;
            case "relevant":
                selector = "[data-selection='rel']";
                break;
            case "upcoming":
                selector = "[data-selection='upcoming']";
                break;
            default:
                throw new IllegalArgumentException("Wrong sorting type provided");
        }

        return selector;
    }

    private void isArraySortedByType(ArrayList<Integer> array, Sorting type) {
        for (int i = 0; i < array.size() - 1; i++) {
            boolean check;
            if(type.equals(Sorting.ASC)) {
                check = array.get(i) > array.get(i + 1);
            } else {
                check = array.get(i) < array.get(i + 1);
            }
            if(check) {
                throw new RuntimeException("Array is not sorted");
            }
        }
    }

    private ArrayList<Integer> collectPrices(ElementsCollection items) {
        ArrayList<Integer> prices = new ArrayList<>();

        for (SelenideElement item : items) {
            String stringPrice = item.find(".result-price").text();
            String trimmedPrice = CharMatcher.is('€').trimFrom(stringPrice);

            prices.add(Integer.parseInt(trimmedPrice));
        }

        return prices;
    }

    enum Sorting {
        ASC,
        DESC
    }
}
