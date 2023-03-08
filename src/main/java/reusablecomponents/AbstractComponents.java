package reusablecomponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	WebDriver driver;

	
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(id = "cartur")
	WebElement cartLink;
	
	public void waitForElementsVisibility(WebElement we) 
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(we));	
	}
	
	public void waitAndAcceptAlert() {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}
	
	public void goToUrl(String url) {
		driver.get(url);
	}
	public void goToCart() {
		cartLink.click();
		
	}
}
