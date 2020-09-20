package Appium.Assignment;



import pageObjects.*;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Resources.ReadExcel;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SignIn extends BaseDriver {
	
	//SIGN IN MODULE
	
	private static AndroidDriver<AndroidElement> driver;
	private static WebDriverWait wait;
	
	
	
	
	
	
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
	
	
	
	//Initializing the driver to perform the tests and stats the server
	@BeforeClass
	public void DriverInitializer() throws IOException {
		startServer();
		driver=capabilityDriver();
		wait = new WebDriverWait(driver,20);
		
	}
	
	
	//SignINTest logs into the App with given credentials
	@Test(dataProvider="getExcelData")
	public void SignInTest(String username,String password) throws IOException, InterruptedException {
		
		
		        SignInPage signInPage =new SignInPage(driver);
		//sending driver in the pageObject class
		  
		  try {
			
				signInPage.getSignInBtn().click();
				wait.until(ExpectedConditions.visibilityOf(signInPage.getEnterUserId()));
				signInPage.getEnterUserId().sendKeys(username);//userid entered
				driver.hideKeyboard();
				signInPage.getContinueBtn().click();//continue
				wait.until(ExpectedConditions.visibilityOf(signInPage.getEnterPassword()));
				signInPage.getEnterPassword().sendKeys(password);//enter password
				driver.hideKeyboard();
				signInPage.getLogInBtn().click();//Log in
				Reporter.log("sign-in successfully");
			
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