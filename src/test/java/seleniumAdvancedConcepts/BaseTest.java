package seleniumAdvancedConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;

import SeleniumAdvanced.PageObjects.LoginPage;

public class BaseTest {
	
	public static void main (String Args[]) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
		prop.load(fis);
		
		
		String URL = prop.getProperty("URL");
		
		
		
		
		ChromeDriver driver = new ChromeDriver();
		
		LoginPage LoginPage = new LoginPage(driver);
		
		
		driver.manage().window().maximize();
        driver.get(URL);
		
		LoginPage.loginApplication("dhanushanand076@gmail.com", "Optimus01!");
		
		
		
	}

	
	
	
	
}
