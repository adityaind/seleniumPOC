package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import reporterHelper.ExtentReportHelper;

public class Listener extends BaseTest implements ITestListener {
	ExtentReports extentReportsObj =ExtentReportHelper.getReporterObj();
	ExtentTest test;
	ThreadLocal<ExtentTest> threadSafe = new ThreadLocal<ExtentTest>();
	
@Override
public void onTestStart(ITestResult result) {
	
	test = extentReportsObj.createTest(result.getMethod().getMethodName());
	threadSafe.set(test);
}

@Override
public void onTestFailure(ITestResult result) {
	threadSafe.get().fail(result.getThrowable());
	String scrnShtPath = null;
	 try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		scrnShtPath = takeScreenShot(result.getMethod().getMethodName(), driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//Attaching ScreenShot
	threadSafe.get().addScreenCaptureFromPath(scrnShtPath, result.getMethod().getMethodName());
}
@Override
public void onFinish(ITestContext context) {
	// TODO Auto-generated method stub
extentReportsObj.flush();
}
}
