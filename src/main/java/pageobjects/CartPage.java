package pageobjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusablecomponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> cartProducts;
	
	@FindBy(css=".btn.btn-success")
	WebElement buyButton;
	
	public Boolean verifyProductInCart(String productName) {
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public OrderPage buyButtonClick() {
		buyButton.click();
		
		OrderPage op = new OrderPage(driver);
		return op;
	}
	
	

}
