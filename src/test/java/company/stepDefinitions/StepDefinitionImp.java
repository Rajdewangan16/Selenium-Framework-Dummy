package company.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import company.TestComponents.BaseTest;
import company.pageobjects.CartPage;
import company.pageobjects.CheckOutPage;
import company.pageobjects.LandingPage;
import company.pageobjects.PlaceOrderPage;
import company.pageobjects.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	PlaceOrderPage placeOrderPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("Logged in with username {string} and password {string}")
	public void Logged_in_with_username_and_password(String username, String password) {
		productCatalog = landingPage.loginApplication(username,password);
	}
	
	@When("I add product {string} to cart")
	public void I_add_product_to_cart(String productName) {
		productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}
	@And("^Checkout (.+) and submit order$")
	public void Checkout_and_submit_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean matchProduct = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(matchProduct);
		CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
		
		checkOutPage.selectCountry("india");
		placeOrderPage = checkOutPage.confirmPlaceOrder();
	}
	@Then("{string} message is displayed on Confirmation Page")
	public void message_is_displayed_on_Confirmation_Page(String string) {
		String ConfirmMessage = placeOrderPage.getConfirmText();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
    public void error_Message_Is_Displayed(String str) {
		Assert.assertEquals(str , landingPage.getErrorMessage());
		driver.close();
    }
}
