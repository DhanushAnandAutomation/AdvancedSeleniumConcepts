package seleniumAdvancedConcepts.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumAdvanced.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage LoginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +  "//src//main//java//testResources//GlobalData.properties");
		prop.load(fis);
		String browser = prop.getProperty("browser");


		if(browser.equalsIgnoreCase("chrome")) {

			/* WebDriverManager.chromedriver().setup(); */
			driver = new ChromeDriver();

		}

		else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			/* geckoDriver driver = new GeckoDriver(); */
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		return driver;


	}

	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException {

		driver = initializeDriver();

		LoginPage = new LoginPage(driver);
		LoginPage.gotoApplication();
		return LoginPage;


	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown() {
		
		driver.close();
		
	}
	
	public List<HashMap<String, String>> getJsonDataToList(String filepath) throws IOException {
		//using this method we can read the json file and then convert it into hashmap
		
		//step1: read json file and convert to string using java builtin class
		String jsonContent=FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		
		//step2: the string is then converted to hashmap
		
		//we are using jackson databind dependency here
		
		/*
		 * The Parentheses (): You need parentheses after the TypeReference<...> to call
		 * the constructor.
		 * 
		 * The Braces {}: TypeReference is an abstract class. Adding {} creates an
		 * anonymous inner class, which is required by Jackson to capture the generic
		 * type information at runtime (this is known as "Type Erasure" protection).
		 * 
		 * Closing the Method: The readValue method must be closed with a final );.
		 */
		
		//we created a list containing two string hashmaps
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(
			    jsonContent, 
			    new TypeReference<List<HashMap<String, String>>>() {} 
			);
		
		
		return data;
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"/Screenshots/" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"/Screenshots/" + testCaseName + ".png";
	}
}

