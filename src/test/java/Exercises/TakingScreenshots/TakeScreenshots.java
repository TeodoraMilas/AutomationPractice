package Exercises.TakingScreenshots;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TakeScreenshots {
    WebDriver driver;
    //save screenshot in a file
    @Test
    public void screenshotInFile() throws IOException {
        driver = new ChromeDriver();
        driver.navigate().to("https://demo-teo.herokuapp.com/");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        //here goes my actual test
        //Convert web driver object to TakeScreenshot
        TakesScreenshot screenShot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
        File ScreenFile = screenShot.getScreenshotAs(OutputType.FILE);
        System.out.println(ScreenFile.getAbsolutePath());
        //Move image file to new destination
        File DestFile = new File("/AutoScreenshots");
        //Copy file at destination
        FileUtils.moveFileToDirectory(ScreenFile, DestFile, false);
        System.out.println(FileUtils.sizeOf(DestFile));

        driver.quit();
    }
    //save as base64
    @Test
    public void screenshotInBase64(){
        driver = new ChromeDriver();
        driver.navigate().to("https://demo-teo.herokuapp.com/");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        //here goes my actual test
        //Convert web driver object to TakeScreenshot
        TakesScreenshot screenShot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
        String ScreenFile = screenShot.getScreenshotAs(OutputType.BASE64);
        //convert output into a file
        File file = OutputType.FILE.convertFromBase64Png(ScreenFile);
        //store that file
        File DestFile = new File("/AutoScreenshots");
        try {
            FileUtils.moveFileToDirectory(file, DestFile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
    //save screenshot as byte
    @Test
    public void screenshotInByte(){
        driver = new ChromeDriver();
        driver.navigate().to("https://demo-teo.herokuapp.com/");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        //here goes my actual test
        //Convert web driver object to TakeScreenshot
        TakesScreenshot screenShot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
        byte[] ScreenFile = screenShot.getScreenshotAs(OutputType.BYTES);
        // convert the Bytes to File type
        File file = OutputType.FILE.convertFromPngBytes(ScreenFile);
        //store that file
        File DestFile = new File("/AutoScreenshots");
        try {
            FileUtils.moveFileToDirectory(file, DestFile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
    //build some logic around screenshots: take screenshot only when a test fails
    //create subfolder with an actual date and time for the run

    public String currentTime(){
        DateFormat dateFormat = new SimpleDateFormat("MM%dd%yyyy%HH%mm%ss");
        Date date = new Date();
        String currentTime = dateFormat.format(date);
        return currentTime;
    }

    public File createExecutionFolder() {
        File DestFile = new File("/AutoScreenshots");
        File file = new File(DestFile + File.separator + "FailedResults" + File.separator + currentTime());
        if (!file.exists()) {
            System.out.println("File created " + file);
            file.mkdir();
        }
        return file;
    }
    public String getTestClassName(String testName) {
        String[] reqTestClassname = testName.split("\\.");
        int i = reqTestClassname.length - 1;
        System.out.println("Required Test Name : " + reqTestClassname[i]);
        return reqTestClassname[i];
    }

    @Test
    public void captureScreenShot() throws IOException {
        driver = new ChromeDriver();
        driver.navigate().to("https://demo-teo.herokuapp.com/");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        TakesScreenshot screenShot =((TakesScreenshot)driver);
        File ScreenFile = screenShot.getScreenshotAs(OutputType.FILE);
        FileUtils.moveFileToDirectory(ScreenFile, createExecutionFolder(), true);

        driver.quit();
    }
}
