package company.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import company.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	public WebDriver driver;
	
	public LandingPage(WebDriver driver) {	
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement userEmail = driver.findElement(By.id("userEmail"));
//  driver.findElement(By.id("userPassword"))

	//PageFactory
	@FindBy(id="userEmail") private WebElement userEmail;
	@FindBy(id="userPassword") private WebElement userPassword;
	@FindBy(id="login") private WebElement submit;
	@FindBy(css=".toast-error") private WebElement errorMessage;
	
	//Action
	public ProductCatalog loginApplication(String email , String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
