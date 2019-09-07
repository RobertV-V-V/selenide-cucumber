package org.craiglist.page.objects

import com.codeborne.selenide.Selenide.element

class HomePage {
    fun languageSelector() = element("#chlang")

    fun category(categoryName: String) = element("[data-alltitle = '$categoryName']")
}
