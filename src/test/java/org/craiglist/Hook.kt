package org.craiglist;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

public class Hook {
    @After
    public void addScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                File file = Screenshots.takeScreenShotAsFile();
                byte[] screenshot = Files.toByteArray(file);
                InputStream is = new ByteArrayInputStream(screenshot);
                Allure.addAttachment("failed step", is);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
