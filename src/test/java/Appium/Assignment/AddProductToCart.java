package Appium.Assignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Resources.TestUtilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SearchResultPage;

public class AddProductToCart extends BaseDriver {
	
	private static String productName;
	private static String productPrice;
	
	
	private static AndroidDriver<AndroidElement> driver;
	private static WebDriverWait wait;
	
	
	
	
	
	
	//Initializing driver with capabilities and starting the server
	@BeforeClass
	public void DriverInitializer() throws IOException {
		startServer();
		driver=capabilityDriver();
		wait = new WebDriverWait(driver,10);
		
	}
	
	
	
	
	
	//Searches a for a product with name defined in Global.properties file
	@Test
	public  void SearchProductTest() throws IOException, InterruptedException{
		
		Reporter.log("Inside SearchProductTest");
		
		
		FileInputStream fileInputStream = null;
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Global.properties");
		Properties properties=new Properties();
		properties.load(fileInputStream);

		
		String productName=properties.getProperty("productName");
		HomePage homePage=new HomePage(driver);
		
		wait.until(ExpectedConditions.visibilityOf(homePage.getSearchField()));
        homePage.getSearchField().click();
	    Thread.sleep(5000);
		//Entering productName in search box
		driver.getKeyboard().sendKeys(productName);
		driver.getKeyboard().sendKeys(Keys.ENTER);
	    Thread.sleep(3000);
		

	}
	
	// Selects a random product from the list of search result
	@Test
	public  void SelectRandomProductTest() throws InterruptedException{
		
	    try {
			SearchResultPage searchResultPage=new SearchResultPage(driver);
			TestUtilities testUtilities=new TestUtilities();
			List<AndroidElement> productList=searchResultPage.getProductList();
			AndroidElement productToBeClicked=testUtilities.getRandomElement(productList);//Getting a random product
			productName=searchResultPage.fetchProductName(productToBeClicked);
			productPrice=searchResultPage.fetchProductPrice(productToBeClicked);//Grabbing product price and name
			Reporter.log("product name is "+ productName);
			Reporter.log("product price is "+ productPrice);
			productToBeClicked.click();
			Reporter.log("Clicked on a random product");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();
			e.printStackTrace();
		}
		Thread.sleep(3000);
	
		
		
				  
		
}   
	
	//Adds the selected product to cart
	@Test(dependsOnMethods = {"SelectRandomProductTest"})
	public void AddProductToCartTest() throws InterruptedException {
		
		ProductPage productPage=new ProductPage(driver);
		TestUtilities testUtils=new TestUtilities();
		
		
		AndroidElement addToCartBtn;
		try {
			testUtils.scrollToText(driver,"Add to Cart");//Scrolls down to add to cart button
			wait.until(ExpectedConditions.visibilityOf(productPage.getAddToCartBtn()));
		}
		
		catch (Exception e1) {
			// TODO Auto-generated catch block
			Assert.fail();
			e1.printStackTrace();
		}
			
			addToCartBtn = productPage.getAddToCartBtn(); 
	    
	        Reporter.log("This test may fail because Add to cart button not getting clicked");
			TouchActions action = new TouchActions(driver);
			action.singleTap(addToCartBtn);
			action.perform();                   //ADD TO CART BUTTON NOT GETTING CLICKED WITH CORRECT LOCATOR,TRYING SINGLE TAP INSTEAD

		    Reporter.log("Clicked on Add to Cart Button");
		    Thread.sleep(3000);
		
		
		
		
	}
	
	
	//Validating the details as fetched from Cart
	@Test(dependsOnMethods = {"AddProductToCartTest"})
	public void ValidateProductDetailsTest() {
		
		CartPage cartPage=new CartPage(driver);
		
		try {
			cartPage.getCartBtn().click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();
			e.printStackTrace();
		}
		try {
			wait.until(ExpectedConditions.visibilityOf(cartPage.getCartList()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();
			e.printStackTrace();
		}
		Reporter.log("Grabbing details from cart to Assert");
		String productNameFromKart=cartPage.getProductNameFromCart();
		String productPriceFromKart=cartPage.getProductPriceFromCart();//Grab name and price of product from Cart
		
		Reporter.log("Price and Name grabbed succesfully");
		
		Assert.assertEquals(productNameFromKart, productName);
		Assert.assertEquals(productPriceFromKart, productPrice);//Check if the details are matching
		
	
		
		
		
		
		
	}
	
	//Kills the server after class
	@AfterClass
	public void killService() {
		service.stop();
	}
	
	
	
	
	
	

	

}
