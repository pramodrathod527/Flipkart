package pomClasses;

import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BecomeSeller {
	
	@FindBy(xpath="//span[text()='Become a Seller']/..")
	private WebElement becomeSellerButton;
	
	
	
	@FindBy(xpath="//input[@name='registrationNumber']")
	private WebElement registrationNumber;
	
	@FindBy(xpath="//div[text()='Start Selling']/..")
	private WebElement startSellerButton;
	
	@FindBy(xpath="//span[text()='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//input[@name='username']")
	private WebElement username;
	
	
	@FindBy(xpath="//span[text()='Next']/..")
	private WebElement nextButton;
	
	@FindBy(xpath="//h2[starts-with(text(),'Launch your')]")
	private WebElement launchText;
	
	
	
	
	
	
	
	
	private WebDriver driver;
	
	
	
	public BecomeSeller(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		
	}
	
	public void clickBecomeSeller() {
		becomeSellerButton.click();
	}
	
	public void startSelling(String resistrationNumber) {
		registrationNumber.sendKeys(resistrationNumber);
		startSellerButton.click();
	}
	
	public void loginButton() {
		loginButton.click();
	}
	
	public void sendUsername(String userNumber) {
		ArrayList<String> list = new ArrayList<String>(driver.getWindowHandles());
		String child = list.get(0); 
		driver.switchTo().window(child);
		username.sendKeys(userNumber);
		nextButton.click();
	}
	
	public void verifyLaunchText() {
		String actualText = launchText.getText();
		String ExpectedText = "Launch your business in 10 minutes";
		
		if(actualText.contentEquals(ExpectedText)) {
			System.out.println(actualText+" text is Matched!");
		}else
		{
			System.out.println(actualText+" text is not Matched!");

		}
	}
	
	

}
