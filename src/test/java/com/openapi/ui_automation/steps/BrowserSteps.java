package com.openapi.ui_automation.steps;

import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.openapi.ui_automation.BaseTest;
import com.openapi.ui_automation.commonMethods;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

/**
 * This class has the steps for the features defined in the MFA-Login feature
 * file
 */
public class BrowserSteps extends BaseTest {
	private Response response;
	private String JsonPayLoad;
	private Logger log = Logger.getLogger(BrowserSteps.class);
	WebDriver driver;
	

	@Given("User opens application url")
	public void user_opens_application_url() throws Exception {


		System.setProperty(
	            "webdriver.chrome.driver",
	            System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
	 
	    // Instantiate a ChromeDriver class.
	    driver = new ChromeDriver();
	    
	        // Maximize the browser
	        driver.manage().window().maximize();

	        
	        Properties properties=new Properties();
	        FileReader reader=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
	        properties.load(reader);
	        properties.getProperty("url");
	        
	 
	        // Launch Website
	        driver.get(properties.getProperty("url"));
	        
	        driver.findElement(By.xpath("//a[@class='header-main__signup login-modal-btn']")).click();
		 	
	}
	
	@When("User click on login button")
	public void User_click_on_login_button() throws Exception {
	        
	        driver.findElement(By.xpath("//a[@class='header-main__signup login-modal-btn']")).click();
		 	
	}
	



}


