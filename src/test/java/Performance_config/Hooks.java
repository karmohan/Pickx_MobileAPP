package Performance_config;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import base.base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.messages.internal.com.google.common.io.Files;

public class Hooks extends base {

	Process process;
	Scenario scenario;
	public long afterAppInvokes;
	public long beforeAppCloses;

	static AppiumDriver<MobileElement> driver;
	AndroidDriver<MobileElement> androidDriver;
	String os = getOSname();

	public Hooks() throws IOException {
		super();
	}

	public static AppiumDriver<MobileElement> getDriver() {
		return driver;
	}

	@Before
	public void chooseRunningPlatform(Scenario scenario) throws IOException, InterruptedException {
		String runningPlatform = getRunningPlatformName();
		String os = getOSname();
		System.out.println("Running Platform is " + runningPlatform);
		System.out.println("Running OS is " + os);
  if (runningPlatform.equalsIgnoreCase("device")) {
			if (os.equalsIgnoreCase("Android")) {
				android_setupAppium();
			} else if (os.equalsIgnoreCase("iOS")) {
				iOS_setupAppium();
			}
		}
		System.out.println("Caps are set.............");
		afterAppInvokes = System.currentTimeMillis();
		System.out.println("bofore app invokes : "+ afterAppInvokes);
	}

	public void setBsSessionName(Scenario scenario) {
		String sessionName = scenario.getName();
		System.out.println("Session name :" + sessionName);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\""
				+ sessionName + "\" }}");
	}

	public void android_setupAppium() throws InterruptedException {
		startServer_cmd();
		URL url;
		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getandroid_deviceName());
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, getandroid_automationName());
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getProdappPackage());
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getProdappActivity());
			capabilities.setCapability("noReset", true);
			driver = new AndroidDriver<>(url, capabilities);
		} catch (Exception exp) {
			System.out.println("!Cause is :" + exp.getCause());
			System.out.println("Message is :.." + exp.getMessage());
			exp.printStackTrace();
		}
	}

	public void iOS_setupAppium() throws InterruptedException {
		URL url;
		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getiOS_deviceName());
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, getiOS_automationName());
			capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, getxcodeOrgId());
			capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, getxcodeSigningId());
			capabilities.setCapability(MobileCapabilityType.UDID, getUdid());
			capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, getProdbundleId());
			capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
			capabilities.setCapability("autoAcceptAlerts", true);
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("noReset", true);
			driver = new IOSDriver<>(url, capabilities);
		} catch (Exception exp) {
			System.out.println("!Cause is :" + exp.getCause());
			System.out.println("Message is :.." + exp.getMessage());
			exp.printStackTrace();
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

	public void updateResultToBs(Scenario scenario) {
		String reason = "error";
		if (scenario.getStatus().toString().equalsIgnoreCase("PASSED")) {
			reason = "Passed";
		} else if (scenario.getStatus().toString().equalsIgnoreCase("FAILED")) {
			reason = "Failed";
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""
				+ scenario.getStatus() + "\", \"reason\": \"Test " + reason + "\"}}");
	}

	@After(order = 1)
	public void takesScreenshotOnFailure(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			File destinationPath = new File(System.getProperty("user.dir") + "/target/screenshot/" + screenshotName
					+ System.currentTimeMillis() + ".png");
			Files.copy(sourcePath, destinationPath);
			scenario.attach(screenshot, "image/png", screenshotName);
		}
	}

	@After(order = 0)
	public void stopServer() {
		beforeAppCloses = System.currentTimeMillis();
		System.out.println("bofore app invokes : "+ beforeAppCloses);
		double timeDifference = (beforeAppCloses - afterAppInvokes);
		double difference = timeDifference/1000;
		System.out.println("The time difference is: " + difference + "sec");
		String runningPlatform = getRunningPlatformName();
		if (runningPlatform.equalsIgnoreCase("browserstack")) {
			updateResultToBs(scenario);
		}
		if (driver != null)
			driver.quit();
	}

}