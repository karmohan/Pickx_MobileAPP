package utils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities {
	 static AndroidDriver<AndroidElement> driver;
	
	public Utilities(AndroidDriver<AndroidElement> driver) {
		Utilities.driver= driver;
	}

	public void switchcontext(String context) {
		System.out.println("Before switching" + driver.getContext());
		Set<String> contextNames = driver.getContextHandles();
    	for (String contextName : contextNames) {
    	    System.out.println("Available context : " + contextName);
    	    if(contextName.contains(context)) {
    	    	driver.context(contextName);
    	    	break;
    	    }
    	}
    	System.out.println("After switching" + driver.getContext());
	}
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
