
## Appium E2E Assignment : 

This project has been developed to automate Amazon application testing on Android platform using Java, Appium, TestNG, Log4j and Maven.Test Data has been fetched externally from Excel Sheet using Apache POI. The driver configuration and Product Name can be changed/modified  from the Global.properties file.

## Steps to run the application:

1. Install Appium Server (CLI). Follow instructions on [Appium Installation Help](http://appium.io/docs/en/about-appium/getting-started/). Appium Server need not be started externally, it is handled in the code itself.

2. Install Android SDK (Command line tools). Follow instructions on [Android Installation Help](https://developer.android.com/studio/?gclid=CjwKCAjwwYP2BRBGEiwAkoBpAohuHRSwpwUk11WkmX7U1dBifIror9wPrmD_xfqMJVCdfkNqB-nSbhoCFyMQAvD_BwE&gclsrc=aw.ds)

3. Connect your android device and verify connectivity using "adb devices" command in command prompt

4. Run the below command on Command Prompt.

```sh
mvn clean install
```




## App Design

1. Testcases can be [found here](https://github.com/floyd96/AppiumE2E/tree/master/src/test/java/Appium/Assignment)

2. Code for application framework can be [found here](https://github.com/floyd96/AppiumE2E/tree/master/src/main/java)

3. Test data is read from [external source file](https://github.com/floyd96/AppiumE2E/blob/master/src/main/java/AmazonTestData.xlsx)

5. Extent Reports is used for reporting functionality.

6. Log4j has been used for logging functionality.


## Scenarios covered

1. signInTest- Logging in to the app with a set of credentials.

2. searchProductTest- Searching for "65 inch TV" from the homepage.

3. selectRandomProductTest- Selecting a random product from the search result and grab the details of the product

4. addProductToCartTest- Adding the randomly selected product to cart and move to cart view.

5. validateProductDetailsTest- Validate the details of the selected product from cart.

6. signOutTest- Signing Out from the app.

