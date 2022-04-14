package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.Platform;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import base.base;

public class ExtentManager extends base{
    public ExtentManager() throws IOException {
		super();
	}
	private static ExtentReports extent =null;
    private static Platform platform;
    protected static Properties prop;
    protected static boolean systemInfoWritten = false;
    private final static String reportName = "Pickx_App_Parallel_Test_Execution_Report";
    private static String macPath = System.getProperty("user.dir")+ "/Parallel_Test_Report";
    private static String windowsPath = System.getProperty("user.dir")+ "\\Parallel_Test_Report";
    private static String macReportFileLoc = macPath + "/" + reportName+".html";
    private static String winReportFileLoc = windowsPath + "\\" + reportName+".html";
    
	public static ExtentReports getInstance() {
    	
        if (extent == null)
			createInstance();
        return extent;
    }
	
    public static ExtentReports createInstance(){
        platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform);
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        try {
			sparkReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/src/test/resources/extent-config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle(reportName);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName(reportName);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        sparkReporter.filter().apply();
        return extent;
    }
    public static void setSystemProps() {
    	
    	if(!systemInfoWritten) {
    		
	    	prop = new Properties();
	        FileInputStream data_file = null,extent_file = null;
	 		try {
	 			extent_file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/extent.properties");
	 			data_file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/data.properties");
	 			prop.load(data_file);
	 			prop.load(extent_file);
	        	extent_file.close();
				data_file.close();
				
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 		}
	 		
	    	extent.setSystemInfo("Platform",prop.getProperty("os"));
	    	extent.setSystemInfo("User",prop.getProperty("username"));
	    	extent.setSystemInfo("Build",prop.getProperty("systeminfo.build"));
	    	extent.setSystemInfo("App Name",prop.getProperty("systeminfo.AppName"));
	    	extent.setSystemInfo("Environment",prop.getProperty("systeminfo.Environment"));
	    	extent.setSystemInfo("Crash count",prop.getProperty("systeminfo.crash_count"));
	    	
	    	prop.clear();
	        systemInfoWritten = true;
    	}
    	
	}
	//Select the extent report file location based on platform
    private static String getReportFileLocation (Platform platform) {
        String reportFileLocation = null;
        switch (platform) {
            case MAC:
            case LINUX:
                reportFileLocation = macReportFileLoc;
                createDirectory(macPath);
                System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
                break;
            case WINDOWS:
                reportFileLocation = winReportFileLoc;
                createDirectory(windowsPath);
                System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            default:
                System.out.println("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return reportFileLocation;
    }
    
    //Create the report path if it does not exist
    public static void createDirectory (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } 
    }
    
    //Get current platform
    private static Platform getCurrentPlatform () {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = Platform.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                platform = Platform.LINUX;
            } else if (operSys.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }
}
    