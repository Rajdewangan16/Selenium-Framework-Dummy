package company.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import company.TestComponents.BaseTest;
import company.TestComponents.Retry;
import company.pageobjects.CartPage;
import company.pageobjects.CheckOutPage;
import company.pageobjects.OrderPage;
import company.pageobjects.PlaceOrderPage;
import company.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest{
	
	@Test(dataProvider="getData" , groups= {"Purchase"} , retryAnalyzer=Retry.class)
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		
		productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean matchProduct = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(matchProduct);
		CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
		
		checkOutPage.selectCountry("india");
		PlaceOrderPage placeOrderPage = checkOutPage.confirmPlaceOrder();
		
		String ConfirmMessage = placeOrderPage.getConfirmText();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = {"submitOrder"} , dataProvider="getData")
	public void orderHistory(HashMap<String,String> input) throws IOException {
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatalog.goToOrderPage();
		Boolean matches = orderPage.verifyOrderDisplay(input.get("productName"));
		Assert.assertTrue(matches);
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "anshu1234@gmail.com");
//		map.put("password", "Anshu@1234");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map.put("email", "leena1234@gmail.com");
//		map.put("password", "Leena@1234");
//		map.put("productName", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + 
				"\\src\\test\\java\\company\\data\\PurchaseOrder.json");
		return new Object[][]  {{data.get(0)},{data.get(1)}};
	}
	
}
