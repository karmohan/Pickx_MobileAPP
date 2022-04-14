package utils;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.base;
import config.Hooks;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

public class TestListener extends base implements ITestListener{
    public TestListener() throws IOException {
		super();
	}
    private static ExtentReports extent = ExtentManager.createInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> scenarios = new ThreadLocal<>();
    private  ArrayList<ExtentTest> features = new ArrayList<ExtentTest>();

    @Override
    public synchronized void onStart(ITestContext context) {
    }
    @Override
    public synchronized void onFinish(ITestContext context) {
    	ExtentManager.setSystemProps();
        extent.flush();
    }
    @Override
    public synchronized void onTestStart(ITestResult result) {
    	//Getting the scenario name to add to the test report
    	PickleWrapper pickleWrapper = (PickleWrapper) result.getParameters()[0];
    	String featureWrapper = ((FeatureWrapper) result.getParameters()[1]).toString();
    	String feature = featureWrapper.substring(featureWrapper.indexOf("[")+1,featureWrapper.indexOf("]"));
       	ExtentTest extentTest = null,scenario;
		
		//Adding the feature as the main test and scenario as sub-tests
		for(ExtentTest test : features) {
			System.out.println(test.getModel().getName());
			if(test.getModel().getName().equalsIgnoreCase(feature)) {
				extentTest = test;
				break;
			}
		}
		if(extentTest == null) {
			extentTest = TestListener.extent. createTest(feature);
			features.add(extentTest);	
		}
		scenario = extentTest.createNode(pickleWrapper.getPickle().getName());
		scenario.assignDevice(getDevice());
		scenario.assignCategory(getDeviceLanguage());
		test.set(extentTest);
		scenarios.set(scenario);
			
    }
	@Override
    public synchronized void onTestSuccess(ITestResult result) {
		scenarios.get().pass("Scenario passed");
    }
    @Override
    public synchronized void onTestFailure(ITestResult result) {
    	System.out.println("on failure here");
        scenarios.get().fail(result.getThrowable());
		scenarios.get().addScreenCaptureFromBase64String(Hooks.screenshotDestinations.get());        
    }
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
    	//scenarios.get().skip(result.getThrowable());
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
}
