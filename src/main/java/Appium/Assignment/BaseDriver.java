package Appium.Assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseDriver  {
	
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> elementAndroidDriver;
	
	
	//Method to start appium server
	
	public static  AppiumDriverLocalService startServer() {
		
		boolean flag=checkIfServerIsRunnning(4723);
		if(!flag) {
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
		}
		return service;
	}
	
	//method to check if already any service running on specified port
    public static boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
 	
    
   
	

	public static AndroidDriver<AndroidElement> capabilityDriver() throws IOException  {
		// TODO Auto-generated method stub
		
		FileInputStream fileInputStream = null;
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Global.properties");
		Properties properties=new Properties();
		properties.load(fileInputStream);
		
		
		String device=properties.getProperty("Device");
		String appName=properties.getProperty("appName");
		String automationName=properties.getProperty("automatioName");
		String appPackage=properties.getProperty("appPackage");
		String appActivity=properties.getProperty("appActivity");
		File appDir = new File("src");
	    File app = new File(appDir,appName);
		
	    
	    
	    
	    DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,120);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,device);
		capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,automationName);
		capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
		capabilities.setCapability("androidInstallTimeout",1000000);
		capabilities.setCapability("appPackage",appPackage);
		capabilities.setCapability("appActivity",appActivity);
	    elementAndroidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		elementAndroidDriver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		return elementAndroidDriver;
	}
	
	public static void getScreenshot(String s) throws IOException {
		 File ssFile=((TakesScreenshot)elementAndroidDriver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(ssFile,new File(System.getProperty("user.dir")+"\\Screenshots\\"+s+".png"));
	}
	
	

}
