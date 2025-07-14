package cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features" ,glue = "cucumber.stepDefinitions",monochrome = true,
        plugin = {"html:target/cucumber.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
