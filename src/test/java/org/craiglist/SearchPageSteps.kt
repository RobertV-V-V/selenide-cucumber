package org.craiglist

import com.codeborne.selenide.Condition.visible
import cucumber.api.java.en.When
import org.craiglist.page.objects.SearchPage
import org.craiglist.page.objects.Sorting
import org.craiglist.page.objects.Sorting.highest_price
import org.craiglist.page.objects.Sorting.lowest_price

class SearchPageSteps {

    var searchPage = SearchPage()

    @When("^I sort items by (lowest_price|highest_price|newest|relevant|upcoming)$")
    fun sortBy(type: Sorting) {
        val selector = getSelectorForSortingOption(type)

        searchPage.sortingSelector().click()
        searchPage.sortingSelector().find(selector) .click()
    }

    @When("^I see that (lowest_price|highest_price|newest|relevant|upcoming) sorting option is available$")
    fun sortingOptionAvailable(type: Sorting) {
        val selector = getSelectorForSortingOption(type)

        searchPage.sortingSelector().find(selector).shouldBe(visible)
    }

    @When("I open sorting selector")
    fun openSortingSelector() {
        searchPage.sortingSelector().click()
    }

    @When("I apply search")
    fun applySearch() {
        searchPage.searchInput().shouldBe(visible).sendKeys("helsinki")
        searchPage.searchButton().click()
    }

    @When("^I see that items are sorted by (lowest_price|highest_price)$")
    fun assertSortedBy(type: Sorting) {
        val prices = collectPrices()

        when (type) {
            lowest_price -> assertArraySortedByAsc(prices, type)
            highest_price -> assertArraySortedByDesc(prices, type)
        }
    }

    private fun getSelectorForSortingOption(type: Sorting) = "[data-selection='${type.selector}']"

    private fun assertArraySortedByAsc(array: List<Int>, type: Sorting) {
        val isSorted = array.zipWithNext().all { it.first <= it.second }

        if(!isSorted) {
            throw AssertionError("The grid is not sorted by $type")
        }
    }
    private fun assertArraySortedByDesc(array: List<Int>, type: Sorting) {
        val isSorted = array.zipWithNext().all { it.first >= it.second }

        if(!isSorted) {
            throw AssertionError("The grid is not sorted by $type")
        }
    }

    private fun collectPrices(): MutableList<Int> {
        val items = searchPage.items()
        val prices = mutableListOf<Int>()

        for (item in items) {
            val price = item.find(".result-price").text().removePrefix("€").toInt()
            prices.add(price)
        }

        return prices
    }
}
