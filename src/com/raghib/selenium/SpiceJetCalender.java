package com.raghib.selenium;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpiceJetCalender {
	public static WebDriver driver;
	public static Select selectObject = null;
	public static Select selectObject1 = null;
	
	public static void main(String[] args) {
		String expectedDate = "30-September-2023";
		String eDate = expectedDate.split("-")[0];
		String eMonth = expectedDate.split("-")[1];
		String eYear = expectedDate.split("-")[2];
		System.out.println(eDate+"-"+eMonth+"-"+eYear);
		
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		Duration duration = Duration.ofSeconds(3000);
		//driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES); //Deprecated in Selenium-4
		driver.manage().timeouts().pageLoadTimeout(duration);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://www.spicejet.com/");		
                
        /*Dynamic Drop Down - Select Source and Destination*/
		
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[3]/div/div[1]/div/div[2]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[3]/div/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div")).click();
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[3]/div/div[3]/div[1]/div[2]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[3]/div/div[3]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div")).click();        
	    
	    /*Date Selection - Departure Date Only*/
        //WebDriverWait explicitlyWait = new WebDriverWait(driver, 3000);	//Deprecated in Selenium-4
        WebDriverWait explicitlyWait = new WebDriverWait(driver, duration);
        //WebElement fromDateButton = explicitlyWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='ui-datepicker-trigger'])[1]")));
        WebElement fromDateButton = explicitlyWait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_mainContent_view_date1")));
        fromDateButton.click();
        
        String currentMonth = driver.findElement(By.xpath("(//span[@class='ui-datepicker-month'])[1]")).getText().trim();
        String currentYear = driver.findElement(By.xpath("(//span[@class='ui-datepicker-year'])[1]")).getText().trim();
        WebElement next;
        System.out.println("CurrentMonth : "+currentMonth.toString()+" / "+"CurrentYear : "+currentYear.toString());
        System.out.println("ExpectedMonth : "+eMonth+" / "+"ExpectedYear : "+eYear);
        
        while((!currentMonth.equalsIgnoreCase(eMonth)) || (!currentYear.equalsIgnoreCase(eYear))) {
        	next = driver.findElement(By.xpath("//span[text()='Next']"));
        	next.click();
        	currentMonth = driver.findElement(By.xpath("(//span[@class='ui-datepicker-month'])[1]")).getText().trim();
        	currentYear = driver.findElement(By.xpath("(//span[@class='ui-datepicker-year'])[1]")).getText().trim();
        }
        
        List<WebElement> dates = driver.findElements(By.xpath("//a[@class='ui-state-default']"));
        for(WebElement loopElement : dates) {
        	if(loopElement.getText().trim().equalsIgnoreCase(eDate)) {
        		loopElement.click();
        		break;        		
        	}
        }
        
        /*Static Drop Down - Select No of Passenger*/
		driver.findElement(By.xpath("//div[@id='divpaxinfo']")).click(); WebElement
		adult = driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_Adult']"));
		selectObject1 = new Select(adult); selectObject1.selectByIndex(2);
	    
	    /*Static Drop Down - Select Currency*/
        WebElement dropDownListCurrency = driver.findElement(By.xpath("//select[@name='ctl00$mainContent$DropDownListCurrency']"));
		selectObject = new Select(dropDownListCurrency);
		selectObject.selectByIndex(2); selectObject.selectByValue("BDT");
		selectObject.selectByVisibleText("USD");
        
		//driver.quit();        
	}

}
