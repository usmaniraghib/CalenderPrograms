package com.raghib.calender;

/**
 * Reference:-
 * https://www.youtube.com/watch?v=VeJ5WH_9PPg
 */

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.raghib.polymorphism.WaitClass;
import com.raghib.selenium.BaseClass;

public class SpiceJetCalender extends BaseClass {
	
	public static WebDriver driver;
	public static WaitClass wc;
	public static String browserName = "chrome";
	public static String browserVersion = "116";
	
	public static String url = "https://www.spicejet.com/";
	
	public static String dynamicDate = "10";
	public static String dynamicMonth = "September";
	public static String dynamicYear = "2023";
	public static String currency = "SGD";
	public static String sourceLocation = "DEL";
	public static String destinationLocation = "BHO";

	public static void main(String[] args) {		
		wc = new WaitClass();		
		// Chrome Browser
		driver = BaseClass.getDriver(browserName, browserVersion);		
		wc.pageLoadTimeout(driver,Duration.ofMinutes(1));
		wc.implicitWait(driver,Duration.ofSeconds(30));		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();		
		driver.get(url);       		
	    	            
		/*Dynamic Drop Down - Select Source and Destination*/
		driver.findElement(By.xpath("//div[@data-testid='to-testID-origin']//input[@type='text']")).click();
	    driver.findElement(By.xpath("//div[contains(text(),'"+sourceLocation+"')]")).click();
	    driver.findElement(By.xpath("//div[contains(text(),'"+destinationLocation+"')]")).click();		
	    
	    /*Date Selection - Departure Date Only*/
        driver.findElement(By.xpath("//div[@data-testid='undefined-month-"+dynamicMonth+"-"+dynamicYear+"']//div[@data-testid='undefined-calendar-day-"+dynamicDate+"']")).click();
        
        /*Static Drop Down - Select No of Passenger*/
		driver.findElement(By.xpath("//div[@data-testid='home-page-travellers']")).click(); 
		
		int adultCount = 0;
		if(adultCount  <= 2) {
			driver.findElement(By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']")).click();
		}		
	    
	    /*Static Drop Down - Select Currency*/
        driver.findElement(By.xpath("//div[@class='css-76zvg2 css-bfa6kz r-1862ga2 r-1gkfh8e' and text()='Currency']")).click();
        driver.findElement(By.xpath("//div[text()='"+currency+"']")).click();
        
		BaseClass.quitDriver();
	}
}
