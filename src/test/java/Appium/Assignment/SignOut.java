package Appium.Assignment;

import java.io.IOException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Resources.TestUtilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.SignOutPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignOut extends BaseDriver {
	
	//SIGN OUT MODULE
	

	private static AndroidDriver<AndroidElement> driver;
	private static WebDriverWait wait;
	
	public static Logger log =LogManager.getLogger(SignOut.class.getName());
	
	
	//Initializes driver and starts server
	@BeforeClass
	public void driverInitializer() throws IOException {
		startServer();
		driver=capabilityDriver();
		wait = new WebDriverWait(driver,10);
		
	}
	
	
    //Logs out of the App
	@Test
	public void signOutTest() throws IOException {
		
		
		
		       log.info("Inside Sign Out method);
		       SignOutPage signOutPage=new SignOutPage(driver);
		       TestUtilities testUtilities=new TestUtilities();
		
		 try {
			//Clicking on burger icon
		        signOutPage.getSideBar().click();
			//Scrolling down to Settings option
		        testUtilities.scrollToText(driver, "Settings");
		        wait.until(ExpectedConditions.visibilityOf(signOutPage.getSettings()));
			//Clicking on settings option
		        signOutPage.getSettings().click();
		
			if(signOutPage.getSignOutBtn().isDisplayed()) {
				//Clicking on Sign Out button if displayed
				signOutPage.getSignOutBtn().click();
				wait.until(ExpectedConditions.visibilityOf(signOutPage.getConfirmBtn()));
				//Clicking on Confirm button
				signOutPage.getConfirmBtn().click();
			}else { 
				//Only reaches this block if already Signed out
				log.info("Already Signed Out");
			}
			
		}catch(Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	
	}
	
	
	//Kills the server after class
	@AfterClass
	public void killService() {
		service.stop();
	}

}
