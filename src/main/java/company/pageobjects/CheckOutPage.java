package company.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import company.abstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css =".form-group input") private WebElement country;
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]") private WebElement targetCountry;
	@FindBy(css=".action__submit") private WebElement placeOrderButton;
	private By dropDownList = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country , countryName).build().perform();
		waitForElementToAppearBy(dropDownList);
		targetCountry.click();
	}
	
	public PlaceOrderPage confirmPlaceOrder() {
		scrollIntoView(placeOrderButton);
		waitForElementToClickable(placeOrderButton);
		placeOrderButton.click();
		PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver);
		return placeOrderPage;
	}
}
