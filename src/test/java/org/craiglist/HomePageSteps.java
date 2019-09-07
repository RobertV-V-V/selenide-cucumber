package org.craiglist;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import org.craiglist.page.objects.HomePage;

import static com.codeborne.selenide.Condition.visible;

public class HomePageSteps {

    @Inject
    public HomePage homePage;

    @And("I set (english|français|español) language")
    public void setLanguage(String language) {
        homePage.languageSelector().shouldBe(visible).selectOptionContainingText(language);
    }
    @And("I open \"(.+)\" category")
    public void openCategory(String categoryName) {
        homePage.category(categoryName).click();
    }
}
