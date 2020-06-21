package DriverManager;

import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class OperaDriverManagerAbstraction extends DriverManagerAbstraction {
    public void createWebDriver(){
        OperaOptions options = new OperaOptions();
        //set your browser-specific options here
        this.driver = new OperaDriver(options);
    }
}
