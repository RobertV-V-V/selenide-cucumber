package org.craiglist

import com.codeborne.selenide.Selenide.open
import cucumber.api.java.en.Given

class CommonSteps {
    @Given("I open browser with helsinki.craiglist home page")
    fun openHomePage() {
        open("/")
    }
}
