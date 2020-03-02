/*
 * Class Name: MyRunner
 * Author: Amit Singh
 * Description: Starting point of execution. Contains runners and cucumber annotations
 * Date Created: 26-Feb-2020
 */
package framework;

import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:"},
        features = "src\\main\\resources\\features",
        glue={"StepDefinitions"},
        strict = true,
        monochrome = true
)

public class MyRunner   {

    public static HashMap<String, ArrayList<String>> allObjects;
    public static WebDriver webDriver;
    protected static String sReportPath;
    private TestNGCucumberRunner testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

    @BeforeClass
    public static void setUp()  {
        //load config variables
        Config config = new Config();

        //Fetch all objects from repository
        allObjects = Support.getAllObjects();

        //Initialize Webdriver
        webDriver= Support.setBrowser(Config.getProperty("browser"));

        //Set report path
        sReportPath = Report.createReportFolder();

        //Initialize ExtentReports
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath(sReportPath + "\\executionReport.html");
        extentProperties.setProjectName("Test task for testdevlabs.com");
    }

    @Test(dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass
    public static void tearDown() throws IOException {
        Reporter.loadXMLConfig(new File("extent-config.xml"));
        Reporter.setSystemInfo("Tester Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("OS", "Windows 10, 64-bit");
        Reporter.assignAuthor(System.getProperty("user.name"));

        webDriver.quit();
    }
}