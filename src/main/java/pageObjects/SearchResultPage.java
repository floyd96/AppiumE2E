package pageObjects;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchResultPage {

public SearchResultPage(AndroidDriver<AndroidElement> driver) {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}

@AndroidFindBy(id ="com.amazon.mShop.android.shopping:id/list_product_linear_layout")
private List<AndroidElement> productList;

public List<AndroidElement> getProductList() {
	return productList;
}


@AndroidFindBy(id ="com.amazon.mShop.android.shopping:id/item_title")
private AndroidElement productNameElement;

public AndroidElement getProductNameElement() {
	return productNameElement;
}


    public String fetchProductName(AndroidElement element) {
	String productNameString=element.findElement(By.id("com.amazon.mShop.android.shopping:id/item_title")).getText();
	return productNameString;
    

     }
    public String fetchProductPrice(AndroidElement element) {
		  List<MobileElement> list=element.findElements(By.xpath("//android.widget.TextView[@index='0']")); 
		  String productPriceString=list.get(2).getText(); 
		  String[] PriceArray=productPriceString.split(Pattern.quote("."));
		  String price1=PriceArray[0];
		  String price2=price1.replaceAll("[^0-9]",""); 
		  String finalPriceString=price2.trim();
		  return finalPriceString;
    }







}