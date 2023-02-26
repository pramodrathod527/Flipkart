package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
	

	@FindBy(xpath="//button[@class='_2KpZ6l _2doB4z']")
	private WebElement popup_button;
	
	@FindBy(xpath="//a[text()='Login']")
	private WebElement login_Button;
	
	@FindBy(xpath="//div[text()='Sign Up']")
	private WebElement signUp_button;
	
	@FindBy(xpath="//span[starts-with(text(),'Looks like')]/../../../div[2]/div/form/div/input")
	private WebElement mobileNumberField;
	

	@FindBy(xpath="//span[starts-with(text(),'Looks like')]")
	private WebElement looksLikeText;
	
	@FindBy(xpath="//span[starts-with(text(),'Sign up ')]")
	private WebElement signUpWithText;
	
	@FindBy(xpath="//span[starts-with(text(),'Looks like ')]/../../../../../button")
	private WebElement cutSignUp;
	
	
	
	
	
	
	
	
	
	
	
	private WebDriver driver;
	private Actions action;
	
	public SignUpPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		action = new Actions(driver);
	}
	
	
	public void clickPopUp() {
		popup_button.click();
	}
	
	public void clickSignUpButton() {
		action.moveToElement(login_Button).build().perform();
		action.click(signUp_button).build().perform();
			
	}
	
	public void sendmobileNumber(String username) {
		mobileNumberField.sendKeys(username);
	}
	
	public void lookLikeText() {
		String expectedText = "Looks like you're new here!";
		String actualText = looksLikeText.getText();
		if(expectedText.contentEquals(actualText)) {
			System.out.println(actualText+ " text is Matched!");
		}else {
			System.out.println(actualText+" Does note Match!");
		}
	}
	
	public void singUpWithText() {
		String expectedText = "Sign up with your mobile number to get started";
		String actualText = signUpWithText.getText();
		if(expectedText.contentEquals(actualText)) {
			System.out.println(actualText+ " text is Matched!");
		}else {
			System.out.println(actualText+" Does note Match!");
		}
	}
	
	public void clickCutSignUp() {
		cutSignUp.click();
	}

}
