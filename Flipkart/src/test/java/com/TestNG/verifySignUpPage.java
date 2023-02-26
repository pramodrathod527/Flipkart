package com.TestNG;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browser.Pojo;
import io.github.bonigarcia.wdm.WebDriverManager;
import pomClasses.BecomeSeller;
import pomClasses.SignUpPage;
import utils.Utility;

public class verifySignUpPage {
	
	WebDriver driver;
	SignUpPage signUpPage;
	String data;
	String TestcaseID;
	int row = 1;
	int col = 1;
	
	@Parameters("browser")
	@BeforeTest
	public void launchBrowser(String browserName) {
		
	if(browserName.equals("chrome")) {
		driver = Pojo.openChromeBrowser();
		}
	 if(browserName.equals("firefox")) {
		 driver = Pojo.openFirefoxBrowser();
		}
	 
	 if(browserName.equals("Edge")) {
		 driver = Pojo.openEdgeBrowser();
		}
	 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@BeforeClass
	
	public void initializePomClasses() {
		 signUpPage = new SignUpPage(driver);
	}
	
	
	
	@BeforeMethod
	public void launchWebsite() {
		driver.get("https://www.flipkart.com/");
	}
	
	@Test (priority = 1)
	public void verifySignUpForm() throws IOException{
		TestcaseID =  Utility.getDataFromExcelSheet("Sheet2",row,col);
		signUpPage.clickPopUp();
		signUpPage.clickSignUpButton();
		data = Utility.getDataFromExcelSheet("Sheet2", 1, 3);
		signUpPage.sendmobileNumber(data);
		signUpPage.clickCutSignUp();
	}
	
	
	
	@Test (priority = 2)
	public void VerifyLooksLikeText() throws InterruptedException {
		Thread.sleep(3000);
		signUpPage.clickSignUpButton();
		signUpPage.lookLikeText();
		signUpPage.clickCutSignUp();
		
	}
	
	@Test (priority = 3)
	public void VerifySignUpText() throws InterruptedException {
		Thread.sleep(3000);
		signUpPage.clickSignUpButton();
		signUpPage.singUpWithText();
		signUpPage.clickCutSignUp();
	}
	
	@AfterMethod
	public void goToMainPage(ITestResult result) throws IOException {
		if(ITestResult.FAILURE==result.getStatus()) {
			Utility.captureScreenshot(driver, TestcaseID);
		}
		row++;
		driver.navigate().refresh();
	}
	
	@AfterClass
	public void closePomObjects() {
		 signUpPage = null;
	}
	
	@AfterTest
	public void closerowser() {
		driver.close();
		driver = null;
		System.gc();
	}
	
	

}
