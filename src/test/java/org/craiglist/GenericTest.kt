package org.craiglist

import com.codeborne.selenide.Configuration
import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.BeforeClass
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(plugin = ["pretty", "html:format"], features = ["classpath:"], glue = ["org/craiglist"])
class GenericTest {
    companion object {
        @JvmStatic
        @BeforeClass
        fun setUp() {
            Configuration.startMaximized = true
            Configuration.reportsFolder = "target/surefire-reports"
            Configuration.baseUrl = "https://helsinki.craigslist.org"
            Configuration.timeout = 5000
        }
    }
}
