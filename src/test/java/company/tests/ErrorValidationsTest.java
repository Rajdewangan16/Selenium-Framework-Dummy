package company.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import company.TestComponents.BaseTest;
import company.TestComponents.Retry;
import company.pageobjects.CartPage;
import company.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups = {"ErrorHandling"} , retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {
		
		landingPage.loginApplication("anshu1234@gmail.com", "Anshfu@1234");		
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException {
		
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("leena1234@gmail.com", "Leena@1234");
		
		productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean matchProduct = cartPage.verifyProductDisplay("Zara COAT 33");
		Assert.assertFalse(matchProduct);
	}
}
