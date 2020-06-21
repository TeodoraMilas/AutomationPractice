package DriverManager;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;

public class EdgeDriverManagerAbstraction extends DriverManagerAbstraction {
    File file = new File("C:\\EdgeDriver\\msedgedriver.exe");
    public void createWebDriver() {
        EdgeOptions options = new EdgeOptions();
        System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
        org.openqa.selenium.edge.EdgeDriverService service =
                org.openqa.selenium.edge.EdgeDriverService.createDefaultService();

        //set your browser-specific options here
        this.driver = new EdgeDriver(service, options);
    }
}