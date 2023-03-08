package pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusablecomponents.AbstractComponents;

public class ProductDescPage extends AbstractComponents{
	
	WebDriver driver;
	public ProductDescPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css="a[class*='btn-success']")
	WebElement addToCartButton;
	
	public CartPage clickAddToCart() {
		
		addToCartButton.click();
		waitAndAcceptAlert();
		goToCart();
		CartPage cp = new CartPage(driver);
		return cp;
		
		
	}

}
