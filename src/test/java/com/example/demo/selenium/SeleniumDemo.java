package com.example.demo.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SeleniumDemo {
	
	// A browser window
	private WebDriver driver;
	
	@BeforeEach
	void init() {
		// Allows Selenium to access Chrome browser dev tools
		// Pass it into the driver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// Create new browser window
		this.driver = new ChromeDriver(options);
		this.driver.manage().window().maximize();
	}
	
	@AfterEach
	void tearDown() {
	}
	
	@Test
	void turtleTest() {
		// Redirect browser to the URL
		this.driver.get("https://www.bbc.co.uk/search");
		
		// Can also use By.id but By.cssSelector is more generic approach
		WebElement search = this.driver.findElement(By.cssSelector("#search-input"));
//		search.getRect();
		search.sendKeys("turtle");
		search.sendKeys(Keys.ENTER);
	}
	
}
