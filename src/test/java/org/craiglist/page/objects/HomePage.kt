package org.craiglist.page.objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public SelenideElement languageSelector() {
        return $("#chlang");
    }

    public SelenideElement category(String categoryName) {
        return $("[data-alltitle = '" + categoryName + "']");
    }
}
