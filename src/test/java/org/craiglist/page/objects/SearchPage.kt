package org.craiglist.page.objects

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements

class SearchPage {
    fun sortingSelector() = element(".dropdown-sort")

    fun items() = elements(".result-row").filterBy(Condition.text("€"))

    fun searchInput() = element("#query")

    fun searchButton() = element(".searchbtn")
}