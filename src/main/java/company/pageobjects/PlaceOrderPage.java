package company.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import company.abstractComponents.AbstractComponents;

public class PlaceOrderPage extends AbstractComponents {

	WebDriver driver;
	
	public PlaceOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".hero-primary") private WebElement text;
	public String getConfirmText() {
		String confirmMsg = text.getText();
		return confirmMsg;
	}

}
