package DriverManager;

import org.openqa.selenium.WebDriver;

public abstract class DriverManagerAbstraction {
    protected WebDriver driver;

    protected abstract void createWebDriver();

    public void quitWebDriver(){
        if(driver != null){
            try{
                driver.quit();
                driver = null;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public WebDriver getWebDriver(){
        if(driver == null){
            createWebDriver();
        }
        return driver;
    }
}
