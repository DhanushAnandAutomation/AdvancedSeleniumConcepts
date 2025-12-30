package SeleniumAdvanced.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {

	//test


WebDriver driver;

	//this is constructor used for initializing the driver from BaseTest
	public LoginPage(WebDriver driver)
	{
		
		super(driver);
		//here, this refers to this class's driver object
		this.driver=driver;	
		PageFactory.initElements(driver, this); // this code initialize all the elements. Since constructor runs first, this line also runs with it and thus all WebElements gets initialized first
		
	}
	
	//normal way of object creation for locator
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory method of object creation for locator
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	

	//methods
	
	public void gotoApplication() {
		
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public ProductCatalogue loginApplication(String Email, String Password) {
		
		userEmail.sendKeys(Email);
		userPassword.sendKeys(Password);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver); 
		return productCatalogue;
		//we are sure that when we login into the application, it takes to productCatalogue page. hence we return an object for productcatalogue
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}


	
	
	
}
