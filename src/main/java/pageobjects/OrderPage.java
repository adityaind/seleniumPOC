package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusablecomponents.AbstractComponents;

public class OrderPage extends AbstractComponents{

	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(id="name")
	WebElement name;
	
	@FindBy(id="country")
	WebElement country;

	@FindBy(id="city")
	WebElement city;
	@FindBy(id="card")
	WebElement card;
	@FindBy(id="year")
	WebElement year;
	@FindBy(id="month")
	WebElement month;
	//w.until(ExpectedConditions.elementToBeClickable(By.className("button[onclick='purchaseOrder()']")));
	@FindBy(css="button[onclick='purchaseOrder()']")
	WebElement purchaseButton;
	
	@FindBy(xpath="//div[@id='orderModal']//div[@class='modal-footer']")
	WebElement detailsForm;
	
	public void enterDetails(String Name,String Country,String City,String Card,String Month,String Year) {
		waitForElementsVisibility(detailsForm);
		name.sendKeys(Name);
		country.sendKeys(Country);
		city.sendKeys(City);
		card.sendKeys(Card);
		month.sendKeys(Month);
		year.sendKeys(Year);
		purchaseButton.click();
		}
	public String getConfirmationMessage() {
	String confirmationMessage = driver.findElement(By.xpath("//div[contains(@class, 'visible')]/h2")).getText();
	return confirmationMessage;
	}
	

}
