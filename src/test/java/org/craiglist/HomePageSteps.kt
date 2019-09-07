package org.craiglist

import com.codeborne.selenide.Condition.visible
import cucumber.api.java.en.And
import org.craiglist.page.objects.HomePage

class HomePageSteps {
    var homePage = HomePage()

    @And("^I set (english|français|español) language$")
    fun setLanguage(language: String) {
        homePage.languageSelector().shouldBe(visible).selectOptionContainingText(language)
    }

    @And("I open {string} category")
    fun openCategory(categoryName: String) {
        homePage.category(categoryName).click()
    }
}
