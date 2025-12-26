package seleniumAdvancedConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import SeleniumAdvanced.PageObjects.LoginPage;
import SeleniumAdvanced.PageObjects.ProductCatalogue;


public class BaseTest {
	
	public static void main (String Args[]) throws IOException, InterruptedException {
		
		String ProductName= "ZARA COAT 3";
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
		prop.load(fis);
		
		
		String URL = prop.getProperty("URL");
		
		
		
		
		ChromeDriver driver = new ChromeDriver();
		
		LoginPage LoginPage = new LoginPage(driver);
		
		
		driver.manage().window().maximize();
        driver.get(URL);
		
		LoginPage.loginApplication("dhanushanand076@gmail.com", "Optimus01!");
		
		 ProductCatalogue productCatalogue = new ProductCatalogue(driver); 
		
		 List<WebElement> products = productCatalogue.ListOfProducts();
			
		System.out.println(products.size());
		
		
		  productCatalogue.addProductToCart(ProductName);
		  
		  productCatalogue.gotoCart();
		 
		//since productCatalogue class extends AbstractComponents and object of productCatalogue is available here due to inheritance
		


	   
		
		
		
		
	}

	
	
	
	
}
