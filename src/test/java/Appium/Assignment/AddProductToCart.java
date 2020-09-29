package Appium.Assignment;


import java.io.IOException;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddProductToCart extends BaseDriver {
	
	//Storing as global variables to verify with cart details
	private static String productName;
	private static String productPrice;
	
	//Declaring driver object along with wait
	private static AndroidDriver<AndroidElement> driver;
	private static WebDriverWait wait;
	
	//Initializing logger
	public static Logger log =LogManager.getLogger(AddProductToCart.class.getName());
	
	
	
	
	
	
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
		
		//Fetching product name from properties file
		String productName=TestUtilities.getProperty("productName");
		HomePage homePage=new HomePage(driver);
		
		wait.until(ExpectedConditions.visibilityOf(homePage.getSearchField()));
		
		//Clicking on search field
                homePage.getSearchField().click();
	        Thread.sleep(5000);
		//Entering productName in search box
		driver.getKeyboard().sendKeys(productName);
		
		//Hitting search
		driver.getKeyboard().sendKeys(Keys.ENTER);
	        Thread.sleep(3000);
		

	}
	
	// Selects a random product from the list of search result
	//No need to give priority or dependsOnMethods helper attributes as alphabetically sorted in right order
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
			log.info("product name is "+ productName);
			log.info("product price is "+ productPrice);
			productToBeClicked.click();
			log.info("Clicked on a random product");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();
			e.printStackTrace();
		}
		Thread.sleep(3000);
	
		
		
				  
		
}   
	
	//Adds the selected product to cart
	//Not alphabetically sorted in correct order hence using dependsOnMethods helper attribute
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
	    
	                log.warn("This test may fail because Add to cart button not getting clicked");
		       
		        //Performing Single Tap on Add to Cart button
			TouchActions action = new TouchActions(driver);
			action.singleTap(addToCartBtn);
			action.perform();                   //ADD TO CART BUTTON NOT GETTING CLICKED WITH CORRECT LOCATOR,TRYING SINGLE TAP INSTEAD

		       log.info("Clicked on Add to Cart Button");
		    Thread.sleep(3000);
		
		
		
		
	}
	
	
	//Validating the details as fetched from Cart
	//Using dependsOnMethods helper attribute to maintain order
	@Test(dependsOnMethods = {"addProductToCartTest"})
	public void validateProductDetailsTest() throws InterruptedException {
		
		CartPage cartPage=new CartPage(driver);
		       
		        //Clicking on Cart icon to switch to Cart list
		try {
			cartPage.getCartBtn().click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();
			e.printStackTrace();
		}
		        Thread.sleep(2000);
		
		        //Grabbing name and price of product from Cart list
		        log.info("Grabbing details from cart to Assert");
		        String productNameFromKart=cartPage.getProductNameFromCart();
		        log.info("Name of the product is"+productNameFromKart);
		        String productPriceFromKart=cartPage.getProductPriceFromCart();
		        log.info("Price of the product is"+productPriceFromKart);
		
		        log.info("Price and Name grabbed succesfully");
		     
		       
		        //Verifying the details
		        Assert.assertEquals(productNameFromKart, productName);
		        Assert.assertEquals(productPriceFromKart, productPrice);//Checking if the details are matching
		
	
		
		
		
		
		
	}
	
	//Kills the server after class
	@AfterClass
	public void killService() {
		service.stop();
	}
	
	
	
	
	
	

	

}
