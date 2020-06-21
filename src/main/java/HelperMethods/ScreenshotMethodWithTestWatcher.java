package HelperMethods;

import org.apache.commons.io.FileUtils;
//import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotMethodWithTestWatcher extends TestWatcher {
    private final TakesScreenshot takesScreenshot;

    public ScreenshotMethodWithTestWatcher(TakesScreenshot takesScreenshot) {
        this.takesScreenshot = takesScreenshot;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        File screenshot = new File(description.getClassName()
                + "-" + description.getMethodName() + ".png");

        try {
            FileUtils.moveFile(takesScreenshot.getScreenshotAs(OutputType.FILE), screenshot);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }

        System.err.println("taken " + screenshot);
    }
    }



