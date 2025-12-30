package testResources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	
	
	public static ExtentReports getReportObject() {
		
		
		String path= System.getProperty("user.dir")+ "/ExtentReports/index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Java Selenium Test Run Report");
		reporter.config().setDocumentTitle("Advanced functional Test");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Created By", "Dhanush Anand");
		
		return extent;
		
	}

}
