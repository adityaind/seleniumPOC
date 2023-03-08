package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusablecomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//a[@id='login2']")
	WebElement loginButtonOnFirstPage;
	
	@FindBy(xpath="//input[@id='loginusername']")
	WebElement email;
	
	@FindBy(id="loginpassword")
	WebElement password;
	
	@FindBy(css="div[id='logInModal'] form")
	WebElement form;
	@FindBy(css="button[onclick='logIn()']")
	WebElement login;
	
	
	
	public void goTo() {
		driver.get("https://www.demoblaze.com");
	}
	
	public ProductCatalog loginOnWeb(String username, String passwrd) {
	
		loginButtonOnFirstPage.click();
		
		waitForElementsVisibility(form);
		email.sendKeys(username);
		password.sendKeys(passwrd);
		login.click();
		

		ProductCatalog prodCata= new ProductCatalog(driver);
		return prodCata;
	}
	
	
	

}
