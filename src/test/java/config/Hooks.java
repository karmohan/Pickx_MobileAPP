package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentReports;
import com.google.common.collect.ImmutableMap;

import base.base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.messages.internal.com.google.common.io.Files;
import utils.ExtentManager;


public class Hooks extends base {

	Process process;
	Scenario scenario;
	static ThreadLocal<AppiumDriver<MobileElement>> driver = new ThreadLocal<AppiumDriver<MobileElement>>();
	public static ThreadLocal<String> screenshotDestinations = new ThreadLocal<String>();
	AppiumDriver<MobileElement> androidDriver;
	private static AppiumDriverLocalService service;
	String os = getOSname();

	ExtentReports extent = new ExtentReports();
	public static String extent_line;
	public static String searched_text;
	public static String systemInfoCrash;
	public static String after_updation;
	public static String line1;

	public Hooks() throws IOException {
		super();
	} 

	public static  AppiumDriver<MobileElement> getDriver() {
		return driver.get();
	}
	public static void setDriver(AppiumDriver<MobileElement> appiumDriver) {
		driver.set(appiumDriver);
	}

	@Before
	public void chooseRunningPlatform( Scenario scenario) throws IOException, InterruptedException {

		String runningPlatform = getRunningPlatformName();
		System.out.println("Running Platform is " + runningPlatform);
		System.out.println("Running OS is " + os);
		
		if (runningPlatform.equalsIgnoreCase("browserstack")) {
			this.scenario = scenario;
			runInBrowserstack(scenario);
		} else if (runningPlatform.equalsIgnoreCase("device")) {
			if (os.equalsIgnoreCase("Android")) {
				android_setupAppium();
			} else if (os.equalsIgnoreCase("iOS")) {
				iOS_setupAppium();
			}
		
		}
		System.out.println("Caps are set.............");
		driver.get().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	}

	public void setBsSessionName(Scenario scenario) {
		String sessionName = scenario.getName();
		System.out.println("Session name :" + sessionName);
		JavascriptExecutor jse = (JavascriptExecutor) driver.get();
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\""
				+ sessionName + "\" }}");
	}

	public void android_setupAppium() throws InterruptedException {

		startServer_cmd();
		URL url;
		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
		
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getandroid_deviceName());
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, getandroid_automationName());
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getappPackage());
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getappActivity());
			capabilities.setCapability(MobileCapabilityType.LANGUAGE, getDeviceLanguage());
			capabilities.setCapability(MobileCapabilityType.LOCALE, getDeviceLocation());
			capabilities.setCapability("chromedriverExecutable",
					System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
			capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("noReset", true);

				androidDriver = new AndroidDriver<>(url, capabilities);
				driver.set(androidDriver)  ;		

		} catch (Exception exp) {
			System.out.println("!Cause is :" + exp.getCause());
			System.out.println("Message is :.." + exp.getMessage());
			exp.printStackTrace();
		}
	}

	public void iOS_setupAppium() throws InterruptedException {

		URL url;
		try {
			url = new URL("http://0.0.0.0:4723/wd/hub");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getiOS_deviceName());
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, getiOS_automationName());
			capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, getxcodeOrgId());
			capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, getxcodeSigningId());
			capabilities.setCapability(MobileCapabilityType.UDID, getUdid());
			capabilities.setCapability(MobileCapabilityType.LANGUAGE, getDeviceLanguage());
			capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, getbundleId());
			capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
			capabilities.setCapability("autoAcceptAlerts", true);
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("noReset", true);
			driver.set(new IOSDriver<>(url, capabilities))  ;	
		
			//driver.installApp()
		} catch (Exception exp) {
			System.out.println("!Cause is :" + exp.getCause());
			System.out.println("Message is :.." + exp.getMessage());
			exp.printStackTrace();
		}
	}

	public void runInBrowserstack( Scenario scenario) throws MalformedURLException {
		String buildName = "build";
		String geoLocation = "browserstack.geoLocation";
		try {
			DesiredCapabilities capability = new DesiredCapabilities();			
			if (os.equalsIgnoreCase("Android")) {
				capability.setCapability(MobileCapabilityType.APP, getAndroidAppId());
				capability.setCapability(MobileCapabilityType.DEVICE_NAME, getDevice());
				capability.setCapability(buildName, getAndroidBuildName());
			} else if (os.equalsIgnoreCase("iOS")) {
				capability.setCapability(MobileCapabilityType.APP, getiOSAppId());
				capability.setCapability(MobileCapabilityType.DEVICE_NAME, getDevice());
				capability.setCapability(buildName, getiOSBuildName());
			}
			System.out.println("current conf  "+getDevice()+ " " +getDeviceLanguage());
			capability.setCapability(MobileCapabilityType.LANGUAGE, getDeviceLanguage());
			capability.setCapability(geoLocation, getGeoLocation());
			//capability.setCapability("browserstack.networkLogs", "true");
			
			if (os.equalsIgnoreCase("Android")) {
				driver.set(new AndroidDriver<>(browserstackURL, capability));
			} else if (os.equalsIgnoreCase("iOS")) {
				driver.set(new IOSDriver<>(browserstackURL, capability));
			}
			setBsSessionName(scenario);
		} catch (Exception e) {
			System.out.println("!Cause is :" + e.getCause());
			System.out.println("Message is :.." + e.getMessage());
			e.printStackTrace();
		}
	}


	public void startServer_cmd() throws InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		try {
			process = runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override \"");
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void startserver() {
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File(System.getProperty("user.dir") + "/src/test/resources/node.exe"))
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE));
		service.start();

		if (service == null || !service.isRunning()) {
			throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
		}
	}

	public void updateResultToBs(Scenario scenario) {
		String reason = "error";
		if (scenario.getStatus().toString().equalsIgnoreCase("PASSED")) {
			reason = "Passed";
		} else if (scenario.getStatus().toString().equalsIgnoreCase("FAILED")) {
			reason = "Failed";
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver.get();
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""
				+ scenario.getStatus() + "\", \"reason\": \"Test " + reason + "\"}}");
	}

	public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
	
	@After(order = 3)
	public void crashCountOnFailure(Scenario scenario) throws IOException {
		int Crash_Count = 0;
		if (scenario.isFailed()) {
			System.out.println("------------------------checking app crashed------------------------");
//			It gives the application status
//			mobi.inthepocket.proximus.pxtvui.ui.features.bottomnavigation.BottomNavigationActivity when app is active
//			.activities.LauncherActivity when app is inactive
			if (os.equalsIgnoreCase("Android")) {
				String currentActivity = ((StartsActivity) androidDriver).currentActivity();
				System.out.println("current activity    " + currentActivity);
				if (currentActivity.equals(".activities.LauncherActivity")) {
					System.out.println("App got CRASHED.......................");
					Crash_Count = Crash_Count + 1;
					getCrashCountInExtentProps(Crash_Count);
				}
			} 
//			It gives the application status
//			NOT_INSTALLED,
//			NOT_RUNNING, 
//			RUNNING_IN_BACKGROUND_SUSPENDED,
//			RUNNING_IN_BACKGROUND, 
//			RUNNING_IN_FOREGROUND
			else if (os.equalsIgnoreCase("iOS")) {
				ApplicationState activity = driver.get().queryAppState("com.proximus.inhouse.tv.itp");
				System.out.println("current activity    " + activity);
				String currentActivity = activity.toString();
				if (!currentActivity.equals("RUNNING_IN_FOREGROUND")) {
					System.out.println("App got CRASHED.......................");
					Crash_Count = Crash_Count + 1;
					getCrashCountInExtentProps(Crash_Count);
				}
				
			}
		}
		//return Crash_Count;
		
	}

	@After(order = 1)
	public void takesScreenshotOnFailure(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			
			System.out.println("------------------------capturing Screenshot------------------------");
			String screenshotName = scenario.getName().replaceAll(" ", "_")+getDeviceLanguage();
			File sourcePath = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
			final byte[] screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.BYTES);
			ExtentManager.createDirectory(System.getProperty("user.dir") + "/target/screenshot/");
			File destinationPath = new File(System.getProperty("user.dir") + "/target/screenshot/" + screenshotName
					+ timestamp() + ".png");
					//+ System.currentTimeMillis() + ".png");

			Files.copy(sourcePath, destinationPath);
			scenario.attach(screenshot, "image/png", screenshotName);	
			screenshotDestinations.set(Base64.getEncoder().encodeToString(screenshot));
			}
	}

	
	public static void getCrashCountInExtentProps(int data) throws IOException {
		String Crash_log = "systeminfo.crash_count";
		int crash_number = 0;
		File file_location = new File(System.getProperty("user.dir") + "/src/test/resources/extent.properties");
		FileReader fileReader = new FileReader(file_location);

		try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			while ((extent_line = bufferedReader.readLine()) != null) {
				if (extent_line.contains(Crash_log))
					System.out.println("Crash count before update  " +extent_line);
				searched_text = extent_line;
			}
			systemInfoCrash = searched_text.replaceAll("[^\\d]", " ").trim();
			crash_number = Integer.parseInt(systemInfoCrash) + data;
		}
		String replace = "systeminfo.crash_count=" + crash_number;

		try {
			FileReader fileReader_new = new FileReader(file_location);
			String single_line;
			String extend_Props = "";
			try (BufferedReader bufferedReader = new BufferedReader(fileReader_new)) {

				while ((single_line = bufferedReader.readLine()) != null) {
					extend_Props += single_line + System.lineSeparator();
				}
				extend_Props = extend_Props.replaceAll(searched_text, replace);
				FileWriter fw = new FileWriter(file_location);
				fw.write(extend_Props);
				fw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@After(order = 2)
	public void afterCountUpdate() throws FileNotFoundException, InterruptedException {
	//	System.out.println("------------------------Checking the properties file after update------------------------");
		String Crash_log = "systeminfo.crash_count";
		File file_location = new File(System.getProperty("user.dir") + "/src/test/resources/extent.properties");
		FileReader fileReader = new FileReader(file_location);
		try (BufferedReader bufferedReader1 = new BufferedReader(fileReader)) {
			while ((line1 = bufferedReader1.readLine()) != null) {
				if (line1.contains(Crash_log))
					//System.out.println("Crash count after update  " +line1);
				after_updation = line1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@After(order = 0)
	public void stopServer() throws IOException {
		String runningPlatform = getRunningPlatformName();
		if (runningPlatform.contains("browserstack")) {
			updateResultToBs(scenario);
		}
		if (driver.get() != null) {
			driver.get().quit();
			driver.set(null);
		}

	}

}