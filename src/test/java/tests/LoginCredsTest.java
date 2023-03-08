package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.Retry;



public class LoginCredsTest extends BaseTest {
	
	WebDriverWait w;
	@Test(groups = {"LoginCreds"},retryAnalyzer = Retry.class)
	public void invalidEmail() throws IOException, InterruptedException {
		lp.loginOnWeb("aditya@email.co", "Aditya@123");
		w = new WebDriverWait(driver,Duration.ofSeconds(5000));
		
		
		Alert alert = w.until(ExpectedConditions.alertIsPresent());
		
		String errorMsg = driver.switchTo().alert().getText();
		alert.accept();
		Assert.assertEquals(errorMsg, "Usr does not exist.");
	}
	@Test(groups = {"LoginCreds"})
	public void invaidPassword() throws InterruptedException {
		lp.loginOnWeb("aditya@email.com", "Aditya@12");
		w = new WebDriverWait(driver,Duration.ofSeconds(5000));
		
		
		Alert alert = w.until(ExpectedConditions.alertIsPresent());
		
		
		String errorMsg = driver.switchTo().alert().getText();
		alert.accept();
		Assert.assertEquals(errorMsg, "Wrong password.");
		
	}
}
