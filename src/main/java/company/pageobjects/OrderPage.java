package company.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import company.abstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".table td:nth-child(3)") private List<WebElement> orderProducts;
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = orderProducts.stream().anyMatch(orderProduct->orderProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
}
