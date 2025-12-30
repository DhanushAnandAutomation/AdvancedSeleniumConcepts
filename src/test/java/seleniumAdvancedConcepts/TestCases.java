package seleniumAdvancedConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumAdvanced.PageObjects.CartPage;
import SeleniumAdvanced.PageObjects.CheckOutPage;
import SeleniumAdvanced.PageObjects.LoginPage;
import SeleniumAdvanced.PageObjects.OrderPage;
import SeleniumAdvanced.PageObjects.ProductCatalogue;
import seleniumAdvancedConcepts.TestComponents.BaseTest;


public class TestCases extends BaseTest {

	String ProductName= "ZARA COAT 3";


	@Test(dataProvider="getDataForPurchaseTest", groups="BasicFlow")
	public void Sanity(/* String Email, String Password, String ProductName */ HashMap<String,String> dataset) throws IOException, InterruptedException {


		String Country="India";

		ProductCatalogue productCatalogue= LoginPage.loginApplication(dataset.get("Email"), dataset.get("Password"));

		productCatalogue.addProductToCart(dataset.get("Product"));
		CartPage cartpage=productCatalogue.gotoCart();
		//since productCatalogue class extends AbstractComponents and object of productCatalogue is available here due to inheritance

		Boolean match=cartpage.VerifyCartItemName(dataset.get("Product"));
		Assert.assertTrue(match);

		CheckOutPage chkOutpage = cartpage.gotoCheckOutOrder();
		chkOutpage.enterCreditCardNum();
		chkOutpage.countrySelection(Country);
		chkOutpage.SubmitOrder();
	}


	@Test(dependsOnMethods= {"Sanity"})
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3";
		ProductCatalogue productCatalogue = LoginPage.loginApplication("dhanushanand076@gmail.com", "Optimus01!");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(ProductName));

	}
	
	@DataProvider()
	public Object[][] getDataForPurchaseTest() throws IOException {
		
		/*
		 * HashMap<String,String> dataset1 = new HashMap<String,String>();
		 * 
		 * dataset1.put("Email", "dhanushanand076@gmail.com"); dataset1.put("Password",
		 * "Optimus01!"); dataset1.put("Product", "ZARA COAT 3");
		 * 
		 * 
		 * HashMap<String,String> dataset2 = new HashMap<String,String>();
		 * 
		 * 
		 * dataset2.put("Email", "dhanushanand@gmail.com");
		 * dataset2.put("Password","Dhanush01!"); dataset2.put("Product",
		 * "ADIDAS ORIGINAL");
		 */
		  
		List<HashMap<String,String>> data = getJsonDataToList(System.getProperty("user.dir")+
				"//src//test//resources//dataSetArchive//Sanity.json");
		
		  return new Object [][] {{data.get(0)},{data.get(1)}};
		 
		
		
		//return new Object[][] {{"dhanushanand076@gmail.com","Optimus01!","ZARA COAT 3"},{"dhanushanand@gmail.com","Dhanush01!","ADIDAS ORIGINAL"}}; 
		
	}
		
	


}
