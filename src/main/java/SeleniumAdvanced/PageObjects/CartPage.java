package SeleniumAdvanced.PageObjects;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	
	@FindBy(xpath="//li[@class='totalRow']//button[contains(@class,'btn-primary')]")
	//@FindBy(css = ".totalRow button")
	WebElement checkoutEle;	
	
	@FindBy(css="ul.ng-star-inserted")
	List <WebElement> itemsIncart;
	
	By CartItemsBy = By.cssSelector("ul.ng-star-inserted");
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	
	
   By CheckOutBy = By.cssSelector(".totalRow button");
	
	public CheckOutPage gotoCheckOutOrder(){
		
		waitForElementToAppear(CheckOutBy);
		checkoutEle.click();
		CheckOutPage chkOutpage = new CheckOutPage(driver);
		return chkOutpage;
	}
	
	
	public List<WebElement> ListOfItems() {
		
		waitForElementToAppear(CartItemsBy);
		return itemsIncart;
		
	}
	
	public Boolean VerifyCartItemName(String ProductName) {
		
		Boolean match= ListOfItems().stream().anyMatch(itemsIncart -> itemsIncart.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(ProductName));
		return match;
		
	}

}
