package org.craiglist;

import cucumber.api.java.en.Given;

import static com.codeborne.selenide.Selenide.open;

public class CommonSteps {
    @Given("I open browser with helsinki.craiglist home page")
    public void openHomePage() {
        open("/");
    }
}
