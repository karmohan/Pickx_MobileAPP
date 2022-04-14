package retryOnFailure;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import config.Hooks;
import utils.TestListener;

public class RetryFailedTestCases implements IRetryAnalyzer {
    private int retryCount = 0;
    //maximun retry count
    private int maxRetryCount = 1;
    
    //on failure this method will be called
    public boolean retry(ITestResult result) {
    	if (retryCount < maxRetryCount) {
            System.out.println("Retrying " + result.getName() + " again and the count is " + (retryCount+1));
            retryCount++;
        	TestListener.scenarios.get().fail(result.getThrowable());
        	TestListener.scenarios.get().addScreenCaptureFromBase64String(Hooks.screenshotDestinations.get());       
            
            return true;
        }
        return false;
    }
   
}
