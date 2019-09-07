package org.craiglist.page.objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {
    public SelenideElement sortingSelector() {
        return $(".dropdown-sort");
    }

    public ElementsCollection items() {
        return $$(".result-row").filterBy(Condition.text("€"));
    }

    public SelenideElement searchInput() {
        return $("#query");
    }

    public SelenideElement searchButton() {
        return $(".searchbtn");
    }
}