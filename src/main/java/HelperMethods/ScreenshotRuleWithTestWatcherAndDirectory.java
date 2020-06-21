package HelperMethods;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotRuleWithTestWatcherAndDirectory extends TestWatcher {
    private WebDriver driver;

    public ScreenshotRuleWithTestWatcherAndDirectory(WebDriver driver){
        this.driver = driver;
    }

    //return current date as String
    //use this method in order to create folder with the date of execution time
    public String currentTime(){
        DateFormat dateFormat = new SimpleDateFormat("MM%dd%yyyy%HH%mm%ss");
        Date date = new Date();
        String currentTime = dateFormat.format(date);
        return currentTime;
    }
    //subfolder to save test execution failure by date
    //this subfolder should be created in an already existing folder
    public File createExecutionFolder() {
        File DestFile = new File("/AutoScreenshots");
        File file = new File(DestFile + File.separator + "FailedResults" + File.separator + currentTime());
        if (!file.exists()) {
            System.out.println("File created " + file);
            file.mkdir();
        }
        return file;
    }
		@Override
		public Statement apply(Statement base, Description description) {
			return super.apply(base, description);
		}
		// This method gets invoked if the test fails for any reason:
		@Override
		public void failed(Throwable e, Description description) {
                        // Print out the error message:
			System.out.println(description.getDisplayName() + " " + e.getClass().getSimpleName() + "\n");
		        // Now you can do whatever you need to do with it, for example take a screenshot
                        TakesScreenshot screenShot =((TakesScreenshot)driver);
                        File ScreenFile = screenShot.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.moveFileToDirectory(ScreenFile, createExecutionFolder(), true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		// This method gets called when the test finishes, regardless of status
		// If the test fails, this will be called after the method above
		@Override
		protected void finished(Description description) {
			if (driver != null)
				driver.quit();
		}
	}