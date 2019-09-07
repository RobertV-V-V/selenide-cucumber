package org.craiglist

import com.codeborne.selenide.Screenshots
import com.google.common.io.Files.toByteArray
import cucumber.api.Scenario
import cucumber.api.java.After
import io.qameta.allure.Allure
import java.io.ByteArrayInputStream

class Hook {
    @After
    fun addScreenshot(scenario: Scenario) {
        if (scenario.isFailed) {
            try {
                val file = Screenshots.takeScreenShotAsFile()
                val screenshot = ByteArrayInputStream(toByteArray(file))
                Allure.addAttachment("failed step", screenshot)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
