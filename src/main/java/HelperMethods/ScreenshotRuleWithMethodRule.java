package HelperMethods;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;

public class ScreenshotRuleWithMethodRule implements MethodRule {
    private WebDriver driver;

    public ScreenshotRuleWithMethodRule(WebDriver driver){
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

    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                    // only when a test fails exception will be thrown.
                    captureScreenShot();
                    // rethrow to allow the failure to be reported by JUnit
                    throw t;
                }
            }

            public void captureScreenShot() throws IOException {
                TakesScreenshot screenShot =((TakesScreenshot)driver);
                File ScreenFile = screenShot.getScreenshotAs(OutputType.FILE);
                try {
                    LOGGER.info("Saving screenshot of failed test...");
                    FileUtils.moveFileToDirectory(ScreenFile, createExecutionFolder(), true);
                } catch (IOException ioe) {
                    LOGGER.warning("Error taking screenshot");
                } finally {
                    closeDriver();
                }
            }
            public void closeDriver(){
                driver.close();
            }
        };
    }
}