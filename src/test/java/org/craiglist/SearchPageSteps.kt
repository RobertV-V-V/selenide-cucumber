package org.craiglist

import com.codeborne.selenide.Condition.visible
import cucumber.api.java.en.When
import org.craiglist.page.objects.SearchPage

class SearchPageSteps {

    var searchPage = SearchPage()

    @When("^I sort items by (lowest price|highest price|newest|relevant)$")
    fun sortBy(type: String) {
        val selector = getSelectorForSortingOption(type)

        searchPage.sortingSelector().click()
        searchPage.sortingSelector().find(selector) .click()
    }

    @When("^I see that (lowest price|highest price|newest|relevant|upcoming) sorting option is available$")
    fun sortingOptionAvailable(type: String) {
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

    @When("^I see that items are sorted by (lowest price|highest price)$")
    fun assertSortedBy(type: String) {
        val prices = collectPrices()

        when (type) {
            "lowest price" -> assertArraySortedByAsc(prices, type)
            "highest price" -> assertArraySortedByDesc(prices, type)
            else -> throw IllegalArgumentException("Wrong sorting type provided")
        }
    }

    private fun getSelectorForSortingOption(type: String): String {

        return when (type) {
            "lowest price" -> "[data-selection='priceasc']"
            "highest price" ->  "[data-selection='pricedsc']"
            "newest" ->  "[data-selection='date']"
            "relevant" ->  "[data-selection='rel']"
            "upcoming" ->  "[data-selection='upcoming']"
            else -> throw IllegalArgumentException("Wrong sorting type provided")
        }
    }

    private fun assertArraySortedByAsc(array: List<Int>, type: String) {
        val isSorted = array.zipWithNext().all { it.first <= it.second }

        if(!isSorted) {
            throw AssertionError("The grid is not sorted by $type")
        }
    }
    private fun assertArraySortedByDesc(array: List<Int>, type: String) {
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
