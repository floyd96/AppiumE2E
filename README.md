## Amazon Appium Assignment : 

This project has been developed to automate Amazon application testing on Android platform using Java, Appium, TestNG and Maven.

## Steps to run the application:

1. Install Appium Server (CLI). Follow instructions on [Appium Installation Help](http://appium.io/docs/en/about-appium/getting-started/). Appium Server need not be started externally, it is handled in the code itself.

2. Install Android SDK (Command line tools). Follow instructions on [Android Installation Help](https://developer.android.com/studio/?gclid=CjwKCAjwwYP2BRBGEiwAkoBpAohuHRSwpwUk11WkmX7U1dBifIror9wPrmD_xfqMJVCdfkNqB-nSbhoCFyMQAvD_BwE&gclsrc=aw.ds)

3. Connect your android device and verify connectivity using "adb devices" command in command prompt

5. Run the below command on Command Prompt.

```sh
mvn clean install
```
6. On completion of execution, test report will be generated in [Amazon.html file](https://github.com/floyd96/AmazonAssignment/tree/master/Reports/htmlreport.html)

![image](https://github.com/floyd96/AmazonAssignment/tree/master/Screenshots)


## App Design

1. Testcases can be [found here](https://github.com/floyd96/AmazonAssignment/tree/master/src/test/java/Appium/Assignment)

2. Code for application framework can be [found here](https://github.com/floyd96/AmazonAssignment/tree/master/src/main/java)

3. Test data is read from [external source file](https://github.com/floyd96/AmazonAssignment/tree/master/src/main/java/AmazonTestData.xlsx)

5. Extent Reports is used for reporting functionality.


## Scenarios covered

1.SignInTest- Logging in to the app with a set of credentials.

2.SearchProductTest- Searching for "65 inch TV" from the homepage.

3.SelectRandomProductTest- Selecting a random product from the search result and grab the details of the product

4.AddProductToCartTest- Adding the randomly selected product to cart and move to cart view.

5.ValidateProductDetailsTest- Validate the details of the selected product from cart.

6.SignOutTest- Signing Out from the app.

