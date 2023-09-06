package com.raghib.polymorphism;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitClass {
	
	public WebElement explicitWaitClick(WebDriver driver, Duration time, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public WebElement explicitWaitClick(WebDriver driver, Duration time, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
	public void implicitWait(WebDriver driver, Duration time) {
		driver.manage().timeouts().implicitlyWait(time);
	}
	
	public void pageLoadTimeout(WebDriver driver, Duration time) {
		driver.manage().timeouts().pageLoadTimeout(time);
	}
}
