package reporterHelper;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportHelper {

	public static ExtentReports getReporterObj() {
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter =  new ExtentSparkReporter(path);
		reporter.config().setReportName("Demoblaze automation");
		reporter.config().setDocumentTitle("Test Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Aditya");
		return extent;
	}
}
