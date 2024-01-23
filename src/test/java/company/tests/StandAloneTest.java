package company.tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("anshu1234@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Anshu@1234");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		List<String> targetProducts = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO");

		// Loop through the target products and add them to the cart

		targetProducts.forEach(targetProduct -> { WebElement product = products.stream()
					.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(targetProduct))
					.findFirst().orElse(null);
		
			if (product != null) {
				
				product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.cssSelector("div[class*='ng-animating']"))));

				wait.until(ExpectedConditions
						.invisibilityOf(driver.findElement(By.cssSelector("div[class*='ngx-spinner-overlay']"))));
			}
		});
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
		targetProducts.forEach(targetProduct -> { Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(targetProduct));

		Assert.assertTrue(match);

		});
		
		WebElement checkOutButton = driver.findElement(By.cssSelector(".totalRow button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", checkOutButton);
		Thread.sleep(1000);
		checkOutButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".actions a")));

		driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
		List<WebElement> countryOptions = driver.findElements(By.cssSelector(".ta-results button"));
		(countryOptions.stream().filter(countryOption->countryOption.getText().equalsIgnoreCase("India"))
							.findFirst().orElse(null)).click();

		WebElement placeOrderButton = driver.findElement(By.cssSelector(".action__submit"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
		wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
		placeOrderButton.click();

		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		
		System.out.println("Completed");
		driver.close();
	}

}
