package pageObjects;


import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage {
	
public ProductPage(AndroidDriver<AndroidElement> driver) {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}

@AndroidFindBy(id ="add-to-cart-button")
private AndroidElement addToCartBtn;

public AndroidElement getAddToCartBtn() {
	return addToCartBtn;
}



}
