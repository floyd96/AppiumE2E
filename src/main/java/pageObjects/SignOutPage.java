package pageObjects;



import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignOutPage {

	
	public SignOutPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	
	
		
	//methods
	@AndroidFindBy(id="in.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon")
	private MobileElement sideBar;
	
	public  MobileElement getSideBar() {
		return sideBar;
	}
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	private MobileElement settings;
	public  MobileElement getSettings() {
		return settings;
	}
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Sign out\")")
	private MobileElement signOutBtn;
	public  MobileElement getSignOutBtn() {
		return signOutBtn;
	}
	
	
	@AndroidFindBy(id="android:id/button2")
	private MobileElement confirmBtn;
	public  MobileElement getConfirmBtn() {
		return confirmBtn;
	}
}