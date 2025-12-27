package SeleniumAdvanced.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	
	 WebDriver driver;

	public  ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		//this initElements will help to create all the FindBy webelements initialization
		
	}
	
	//CSS selector
	//if you know class name : tagname.classname
	//if you know id: tagname#id
	
	@FindBy(css= ".mb-3")
	List <WebElement> Products;
	
	
	By productsBy = By.cssSelector(".mb-3");
	// .mb-3 means .(classname) or (part of classname without spaces)
	
	//By addToCart = By.xpath("//*[contains(@class, 'card-body')]//button[last()]");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	                       /*By.cssSelector(".card-body button:last-of-type");*/
	//this is new in CSS selector - .(className) (tageNAme):(if there are duplicate 'button', last of them will get selected)
	
	By toastContainer = By.id("toast-container");
	
	public List<WebElement> ListOfProducts() {
		
		waitForElementToAppear(productsBy);
		return Products;
		
	}
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	
	
	  public WebElement getProductByName(String productName) { 
		  
	  WebElement prod = ListOfProducts().stream().filter(products->
	  products.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); 
	  String abc = prod.getText();
	  //System.out.println(abc); 
	  return prod; 
	  
	  }
	  
	  public void addProductToCart(String productName) throws InterruptedException{
	  
	  WebElement name= getProductByName(productName);
	  name.findElement(addToCart).click();
	  
	  waitForElementToAppear(toastContainer);
	  waitForElementToDisappear(spinner);
	  
	  
	  }
	 
	  

}
