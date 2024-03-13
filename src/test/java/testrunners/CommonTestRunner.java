package testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( 	features = {"src/test/resources/features"}, 
					glue = {"com.openapi.ui_automation.steps"},
					plugin = {"html:target/cucumber-html-reports/run_report.html"},
					tags="@Healthcheck",
					dryRun = false )
    
public class CommonTestRunner extends AbstractTestNGCucumberTests {}
