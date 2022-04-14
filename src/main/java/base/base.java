package base;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public class base {
	
	protected Properties prop;
	public static URL browserstackURL;
	static ThreadLocal<String> langs = new ThreadLocal<String>();
	static ThreadLocal<String> devices = new ThreadLocal<String>();
	protected FileInputStream base_file = new FileInputStream(
			System.getProperty("user.dir") + "/src/test/resources/data.properties");
	
	protected FileInputStream android_file = new FileInputStream(
			System.getProperty("user.dir") + "/src/test/resources/android_data.properties");
	
	protected FileInputStream ios_file = new FileInputStream(
			System.getProperty("user.dir") + "/src/test/resources/ios_data.properties");

	public base() throws IOException {
		prop = new Properties();
		prop.load(base_file);
		prop.load(android_file);
		prop.load(ios_file);
		String decodedCredentials = getBrowserstackUsername() + ":" + getBrowserstackAccessKey();
		browserstackURL = new URL("http://" + decodedCredentials + "@hub-cloud.browserstack.com/wd/hub");
		if(langs.get()==null)
			setLanguage(getDeviceLanguageFromProps());
		if(devices.get() == null)
			setDevice(getDeviceBasedOnConfig());
	}

	private String getDeviceBasedOnConfig() {
		if(getOSname().equalsIgnoreCase("Android"))
			if(getRunningPlatformName().equalsIgnoreCase("browserstack"))
				return getAndroidbrowserStackdeviceName();
			else
				return getandroid_deviceName();
		else
			if(getRunningPlatformName().equalsIgnoreCase("browserstack"))
				return getiOSbrowserStackdeviceName();
			else
				return getiOS_deviceName();
									
	}

	public String getUsername() {
		return prop.getProperty("username");
	}

	public String getPassword() {
		return prop.getProperty("password");
	}
	
	public String getNativeView() {
		return prop.getProperty("native");
	}

	public String getWebView() {
		return prop.getProperty("web");
	}
	
	public String getandroid_deviceName() {
		return prop.getProperty("android_deviceName");
	}
	
	public String getiOS_deviceName() {
		return prop.getProperty("iOS_deviceName");
	}
	
	public String getappPackage() {
		return prop.getProperty("appPackage");
	}

	public String getappActivity() {
		return prop.getProperty("appActivity");
	}
	
	public String getProdappPackage() {
		return prop.getProperty("prod_appPackage");
	}

	public String getProdappActivity() {
		return prop.getProperty("prod_appActivity");
	}

	public String getandroid_automationName() {
		return prop.getProperty("android_automationName");
	}
	
	public String getiOS_automationName() {
		return prop.getProperty("iOS_automationName");
	}
	
	public String getRunningPlatformName() {
		return prop.getProperty("runningPlatform");
	}
	
	public String getAndroidAppId() {
		return prop.getProperty("android_appId");
	}
	
	public String getiOSAppId() {
		return prop.getProperty("ios_appId");
	}
	
	public String getDeviceLanguageFromProps() {
		return prop.getProperty("deviceLanguage");
	}
	
	public String getAndroidBuildName() {
		return prop.getProperty("android_buildName");
	}
	
	public String getiOSBuildName() {
		return prop.getProperty("ios_buildName");
	}
	
	public String getBrowserstackUsername() {
		return prop.getProperty("browserstackUsername");
	}
	
	public String getBrowserstackAccessKey() {
		return prop.getProperty("browserstackAccessKey");
	}
	
	public String getAndroidbrowserStackdeviceName() {
		return prop.getProperty("android_browserstackdevicename");
	}
	
	public String getiOSbrowserStackdeviceName() {
		return prop.getProperty("ios_browserstackdevicename");
	}
	
	public String getGeoLocation() {
		return prop.getProperty("geoLocation");
	}
	
	public String getLineNumber() {
		return prop.getProperty("lineNumber");
	}
	
	public String getOSname() {
		return prop.getProperty("os");
	}
	
	public String getxcodeOrgId() {
		return prop.getProperty("xcodeOrgId");
		
	}
	
	public String getxcodeSigningId() {
		return prop.getProperty("xcodeSigningId");
	}
	
	public String getUdid() {
		return prop.getProperty("udid");
	}
	
	public String getbundleId() {
		return prop.getProperty("bundleId");
	}
	
	public String getProdbundleId() {
		return prop.getProperty("prod_bundleId");
	}
	
	public String getEpicUsername() {
		return prop.getProperty("epicusername");
	}
	
	public String getEpicPassword() {
		return prop.getProperty("epicpassword");
	}
	public String getEpicLineNumber() {
		return prop.getProperty("epiclineNumber");
	}
	
	public String getEndpointURL() {
		return prop.getProperty("IP_Address_EndpointURL");
	}
	
	public String getDeviceLocation() {
		return prop.getProperty("deviceLocation");
	}
	public static void setLanguage(String language) {
		langs.set(language);
	}
	public static void setDevice(String device) {
		devices.set(device);
	}
	public static String getDeviceLanguage() {
		return langs.get();
	}
	public static String getDevice() {
		return devices.get();
	}
	
}
