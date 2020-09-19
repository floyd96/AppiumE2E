package pageObjects;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage {
	
public CartPage(AndroidDriver<AndroidElement> driver) {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}


//methods
@AndroidFindBy(id ="com.amazon.mShop.android.shopping:id/chrome_action_bar_cart")
private AndroidElement cartBtn;

public AndroidElement getCartBtn() {
	return cartBtn;
}

@AndroidFindBy(id ="sc-active-cart")
private List<AndroidElement> cartList;

public WebElement getCartList() {
	return (WebElement) cartList;
}


@AndroidFindBy(className ="android.widget.Image")
private List<AndroidElement> productList;

public List<AndroidElement> getProductList() {
	return productList;
}

@AndroidFindBy(className ="android.view.View")
private List<AndroidElement> productPrice;

public List<AndroidElement> getProductPrice() {
	return productList;
}

public  String getProductNameFromCart() {
	return productList.get(1).getText();
}

public String getProductPriceFromCart() {
	
	String[] rawPriceText=productPrice.get(4).getText().split(Pattern.quote("."));
	return rawPriceText[0].replaceAll("[^0-9]","");
}




}
