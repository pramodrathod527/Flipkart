package com.TestNG;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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

public class VerifyBecomeSeller {
	WebDriver driver;
	SignUpPage signUpPage;
	BecomeSeller becomeSeller;
	String data;
	String TestCaseID;
	int row =4;
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
		 becomeSeller = new BecomeSeller(driver);
	}
	
	
	@BeforeMethod
	public void launchWebsite() {
		driver.get("https://www.flipkart.com/");
	}
	
	@Test (priority=1)
	
	public void VerifyStartSelling() throws IOException {
		TestCaseID = Utility.getDataFromExcelSheet("Sheet2",row,col);
		signUpPage.clickPopUp();
		becomeSeller.clickBecomeSeller();
		data = Utility.getDataFromExcelSheet("Sheet2",1,3);
		becomeSeller.startSelling(data);
		
	}
	
	@Test (priority=2)
	
	public void VerifyLoginBecomeSeller() throws InterruptedException, IOException {
		TestCaseID = Utility.getDataFromExcelSheet("Sheet2",row,col);
//		signUpPage.clickPopUp();
		Thread.sleep(3000);
		becomeSeller.clickBecomeSeller();
		becomeSeller.loginButton();
		data = Utility.getDataFromExcelSheet("Sheet2",1, 3);
		becomeSeller.sendUsername(data);
	}
	
	@Test (priority=3)
	
	public void VerifyTextBecomeSeller() throws IOException {
		TestCaseID = Utility.getDataFromExcelSheet("Sheet2",row,col);
		becomeSeller.clickBecomeSeller();
		becomeSeller.verifyLaunchText();
		
		ArrayList<String> address = new ArrayList<String>(driver.getWindowHandles());
		
		
		String expectedAddress = address.get(0);
		String actualAddress = driver.getWindowHandle();
		Assert.assertEquals(actualAddress,expectedAddress," URL Not Matched");
		 
	}
	
	@AfterMethod 
	public void goToMainPage(ITestResult result) throws IOException {
		if(ITestResult.FAILURE==result.getStatus()) {
			Utility.captureScreenshot(driver, TestCaseID);
		}
		row++;
		driver.navigate().back();
	}
	
	@AfterClass
	public void closePomObjects() {
		 signUpPage = null;
		 becomeSeller = null;
	}
	
	@AfterTest
	public void closerowser() {
		
		driver.close();
		driver = null;
		System.gc();
	}
	
	

}
