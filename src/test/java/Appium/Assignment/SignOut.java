package Appium.Assignment;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Resources.TestUtilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.SignOutPage;

public class SignOut extends BaseDriver {
	
	//SIGN OUT MODULE
	

	private static AndroidDriver<AndroidElement> driver;
	private static WebDriverWait wait;
	
	
	//Iinitializes driver aand starts server
	@BeforeClass
	public void DriverInitializer() throws IOException {
		startServer();
		driver=capabilityDriver();
		wait = new WebDriverWait(driver,10);
		
	}
	
	
    //Logs out of the App
	@Test
	public void SignOutTest() throws IOException {
		
		
		
		SignOutPage signOutPage=new SignOutPage(driver);
		TestUtilities testUtilities=new TestUtilities();
		
		signOutPage.getSideBar().click();
		testUtilities.scrollToText(driver, "Settings");
		wait.until(ExpectedConditions.visibilityOf(signOutPage.getSettings()));
		signOutPage.getSettings().click();
		try {
			if(signOutPage.getSignOutBtn().isDisplayed()) {
				signOutPage.getSignOutBtn().click();
				wait.until(ExpectedConditions.visibilityOf(signOutPage.getConfirmBtn()));
				signOutPage.getConfirmBtn().click();
			}else {
				System.out.println("Already Signed Out");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	//Kills the server after class
	@AfterClass
	public void killService() {
		service.stop();
	}

}
