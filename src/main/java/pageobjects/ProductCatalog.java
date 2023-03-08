package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusablecomponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {
	WebDriver driver;
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath = "//div[@class='col-lg-4 col-md-6 mb-4']")
	List<WebElement> products;
	
	public ProductDescPage clickOnDesiredProduct(String productName) {
	
	WebElement prod = products.stream().filter(product->product.findElement(By.className("card-title")).getText().equals(productName)).findFirst().orElse(null);

	try{
		prod.click();
	    }
	catch(StaleElementReferenceException e) {
		products = driver.findElements(By.xpath("//div[@class='col-lg-4 col-md-6 mb-4']"));
		
		prod = products.stream().filter(product->product.findElement(By.className("card-title")).getText().equals(productName)).findFirst().orElse(null);
		prod.click();
		
											}
	ProductDescPage pdp = new ProductDescPage(driver);
	return pdp;
	
	}
//	public void clickOnBuyNow(String productName) {
//		clickOnProduct(productName);
//		
//	}
}
