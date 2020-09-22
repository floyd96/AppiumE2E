package pageObjects;

import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignInPage {
	
	
	
	
	public SignInPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//methods
	@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/sign_in_button")
	private AndroidElement signInBtn;
	public  AndroidElement getSignInBtn() {
		return signInBtn;
	}
	@AndroidFindBy(id="ap_email_login")
	private AndroidElement enterUserId;
	public  AndroidElement getEnterUserId() {
		return enterUserId;
	}
	@AndroidFindBy(id="continue")
	private AndroidElement continueBtn;
	public  AndroidElement getContinueBtn() {
		return continueBtn;
	}
	@AndroidFindBy(id="ap_password")
	private AndroidElement enterPassword;
	public  AndroidElement getEnterPassword() {
		return enterPassword;
	}
	
	@AndroidFindBy(id="signInSubmit")
	private AndroidElement logInBtn;
	public  AndroidElement getLogInBtn() {
		return logInBtn;
	}
	
	
}
