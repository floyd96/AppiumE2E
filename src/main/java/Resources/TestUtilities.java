package Resources;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;



import Appium.Assignment.BaseDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestUtilities extends BaseDriver {
	
	public static String getProperty(String key) throws IOException 
	{
		FileInputStream fileInputStream = null;
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Global.properties");
		Properties properties=new Properties();
		properties.load(fileInputStream);
		return properties.getProperty(key);
	}
	
	
	
	public  AndroidElement getRandomElement(List<AndroidElement> list) 
    { 
		int n=list.size();
		list.remove(0);
		list.remove(n-1);
        Random rand = new Random(); 
        return list.get(rand.nextInt(list.size())); 
    }
	
	public void scrollToText( AndroidDriver<AndroidElement> driver,String text) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
	}
	
	
	
}
