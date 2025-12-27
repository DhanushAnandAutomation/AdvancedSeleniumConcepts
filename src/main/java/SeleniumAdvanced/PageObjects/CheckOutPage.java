package SeleniumAdvanced.PageObjects;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import abstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	
	
	@FindBy(css="div.field input.text-validated")
	WebElement creditCardNumberField;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css="button.ng-star-inserted")
	List<WebElement> countriesListed;
	
	@FindBy(css="a.action__submit")
	WebElement SubmitOrder;
	
	
	
	By countriesListedBy= By.cssSelector("button.ng-star-inserted");
	
	
	
	public void enterCreditCardNum() {
		
		creditCardNumberField.clear();
		creditCardNumberField.sendKeys("123412341234");
	}
	
	public List<WebElement> Countries() {
		
		waitForElementToAppear(countriesListedBy);
		return countriesListed;
	}
	
	public void countrySelection(String CountryName) throws InterruptedException {
		
		country.click();
		country.sendKeys(CountryName);

		waitForElementToAppear(countriesListedBy);
		
		
		WebElement Country=Countries().stream().filter(countriesListed -> 
		countriesListed.findElement(By.cssSelector("span")).getText().equals(CountryName)).findFirst().orElseThrow();
		
		Country.click();
	}
	
	public OrderConfirmationPage SubmitOrder() {
		
		SubmitOrder.click();
		OrderConfirmationPage orderConfirm= new OrderConfirmationPage(driver);
		waitForElementToAppear(orderConfirm.orderSuccessBy);
		Assert.assertEquals(orderConfirm.OrderSuccess.getText(), "THANKYOU FOR THE ORDER.");
		
		return orderConfirm;
		
		
		
		
	}
	

}
