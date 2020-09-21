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
	public void driverInitializer() throws IOException {
		startServer();
		driver=capabilityDriver();
		wait = new WebDriverWait(driver,10);
		
	}
	
	
	
	
	
	//Searches a for a product with name defined in Global.properties file
	@Test
	public  void searchProductTest() throws IOException, InterruptedException{
		
		FileInputStream fileInputStream = null;
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Global.properties");
		Properties properties=new Properties();
		properties.load(fileInputStream);

		//Fetching product name from properties file
		String productName=properties.getProperty("productName");
		HomePage homePage=new HomePage(driver);
		
		wait.until(ExpectedConditions.visibilityOf(homePage.getSearchField()));
		
		//Clicking on search field
                homePage.getSearchField().click();
	        Thread.sleep(5000);
		//Entering productName in search box
		driver.getKeyboard().sendKeys(productName);
		
		//Hitting seaarch
		driver.getKeyboard().sendKeys(Keys.ENTER);
	        Thread.sleep(3000);
		

	}
	
	// Selects a random product from the list of search result
	@Test
	public  void selectRandomProductTest() throws InterruptedException{
		
	    try {
			SearchResultPage searchResultPage=new SearchResultPage(driver);
			TestUtilities testUtilities=new TestUtilities();
			List<AndroidElement> productList=searchResultPage.getProductList();
		        //Getting a random product from the search list
			AndroidElement productToBeClicked=testUtilities.getRandomElement(productList);
		        //Grabbing product price and name
			productName=searchResultPage.fetchProductName(productToBeClicked);
			productPrice=searchResultPage.fetchProductPrice(productToBeClicked);
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
	@Test(dependsOnMethods = {"selectRandomProductTest"})
	public void addProductToCartTest() throws InterruptedException {
		
		ProductPage productPage=new ProductPage(driver);
		TestUtilities testUtils=new TestUtilities();
		
		
		AndroidElement addToCartBtn;
		
		        //Scrolling down to Add to Cart button
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
		       
		        //Performing Single Tap on Add to Cart button
			TouchActions action = new TouchActions(driver);
			action.singleTap(addToCartBtn);
			action.perform();                   //ADD TO CART BUTTON NOT GETTING CLICKED WITH CORRECT LOCATOR,TRYING SINGLE TAP INSTEAD

		    Reporter.log("Clicked on Add to Cart Button");
		    Thread.sleep(3000);
		
		
		
		
	}
	
	
	//Validating the details as fetched from Cart
	@Test(dependsOnMethods = {"addProductToCartTest"})
	public void validateProductDetailsTest() {
		
		CartPage cartPage=new CartPage(driver);
		       
		        //Clicking on Cart icon to switch to Cart list
		try {
			cartPage.getCartBtn().click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();
			e.printStackTrace();
		}
		        //Waiting for the visibilist of cart list
		try {
			wait.until(ExpectedConditions.visibilityOf(cartPage.getCartList()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();
			e.printStackTrace();
		}
		        //Grabbing name and price of product from Cart list
		        Reporter.log("Grabbing details from cart to Assert");
		        String productNameFromKart=cartPage.getProductNameFromCart();
		        String productPriceFromKart=cartPage.getProductPriceFromCart();
		
		        Reporter.log("Price and Name grabbed succesfully");
		     
		       
		        //Verifying the details
		        Assert.assertEquals(productNameFromKart, productName);
		        Assert.assertEquals(productPriceFromKart, productPrice);//Check if the details are matching
		
	
		
		
		
		
		
	}
	
	//Kills the server after class
	@AfterClass
	public void killService() {
		service.stop();
	}
	
	
	
	
	
	

	

}
