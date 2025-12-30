package seleniumAdvancedConcepts;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumAdvanced.PageObjects.CartPage;
import SeleniumAdvanced.PageObjects.ProductCatalogue;
import seleniumAdvancedConcepts.TestComponents.BaseTest;



public class ErrorValidation extends BaseTest{
	
	@Test
	public void LoginWithWrongEmail() {
	ProductCatalogue productCatalogue= LoginPage.loginApplication("dhanushanand0761@gmail.com", "Optimus01!");
	
	Assert.assertEquals("Incorrect email or password1.", LoginPage.getErrorMessage());

}
	
	

  @Test() public void ProductErrorValidation() throws IOException,
  InterruptedException {
  
  String productName = "ZARA COAT 3"; ProductCatalogue productCatalogue =
  LoginPage.loginApplication("dhanushanand@gmail.com", "Dhanush01!");
  List<WebElement> products = productCatalogue.ListOfProducts();
  productCatalogue.addProductToCart(productName); CartPage cartPage =
  productCatalogue.gotoCart(); Boolean match =
  cartPage.VerifyCartItemName("ZARA COAT 33"); Assert.assertTrue(match);
  
  
  
  }
 
	
}
