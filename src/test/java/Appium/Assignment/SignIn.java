package Appium.Assignment;

import pageObjects.*;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Resources.ReadExcel;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignIn extends BaseDriver {
	
	//SIGN IN MODULE
	
	private static AndroidDriver<AndroidElement> driver;
	private static WebDriverWait wait;
	
	public static Logger log =LogManager.getLogger(SignIn.class.getName());
	
	
	
	
	
	
	@DataProvider
	public String[][] getExcelData() throws InvalidFormatException, IOException{
		ReadExcel read = new ReadExcel();
		return read.getCellData(System.getProperty("user.dir")+"\\src\\main\\java\\AmazonTestData.xlsx", "Sheet1");
				
	}
	
	
	
	//Killing service on port if running
	@BeforeSuite
	public void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);	
	}
	
	
	
	//Initializing the driver to perform the tests and starts the server
	@BeforeClass
	public void driverInitializer() throws IOException {
		
		log.info("Inside driver initializer");
		startServer();
		driver=capabilityDriver();
		wait = new WebDriverWait(driver,20);
		
	}
	
	
	//SignINTest logs into the App with given credentials
	@Test(dataProvider="getExcelData")
	public void signInTest(String username,String password) throws IOException, InterruptedException {
		
		
		        SignInPage signInPage =new SignInPage(driver);
		//sending driver in the pageObject class
		  
		  try {
			        //Clicking on Sign In button
				signInPage.getSignInBtn().click();
				wait.until(ExpectedConditions.visibilityOf(signInPage.getEnterUserId()));
			        //Entering User ID
				signInPage.getEnterUserId().sendKeys(username);//userid entered
				driver.hideKeyboard();
			        //Clicking on continue button
				signInPage.getContinueBtn().click();//continue
				wait.until(ExpectedConditions.visibilityOf(signInPage.getEnterPassword()));
			        //Entering password
				signInPage.getEnterPassword().sendKeys(password);//enter password
				driver.hideKeyboard();
			        //Clicking on Log in button
				signInPage.getLogInBtn().click();//Log in
				log.info("sign-in successfully");
			
			  }
		  
		  catch(Exception e) {
			  
			 Assert.fail();
			 
		}
		
				Thread.sleep(2000);

	}	
	
	
	
	//Kills the server after class
	@AfterClass
	public void killService() {
		service.stop();
	}
	
	
	
	
	
}
