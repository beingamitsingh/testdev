package framework;

import StepDefinitions.Hooks;
import com.aventstack.extentreports.utils.FileUtil;
import com.vimalselvam.cucumber.listener.Reporter;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class Report {

    public static void pass(String message)  {

        Reporter.addStepLog(message);
        Reporter.setTestRunnerOutput(java.time.LocalTime.now().toString() + " : " + message);
        Reporter.setTestRunnerOutput("<br>");
        assertTrue(message, true);
    }

    public static void fail(String message) {

        try {
            Reporter.addStepLog(message);
            Reporter.setTestRunnerOutput(java.time.LocalTime.now().toString() + " : " + message);
            Reporter.setTestRunnerOutput("<br>");

            getScreenshot(Hooks.strTestCase.replace(" ", "") + timeStamp());
            Assert.fail(message);
        }
        catch (Exception ex)  {
            ex.getMessage();
        }
    }

    protected static String createReportFolder() {
        String path = Config.getProperty("reportPath") + "ExecutionReport" + "_" + timeStamp();
        File file = new File(path);
        boolean bool = file.mkdir();
        if(bool)
            return path;
        else
            return "Unable to create report folder.";

    }

    private static String timeStamp()    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return Long.toString(timestamp.getTime());
    }

    private static void getScreenshot(String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) MyRunner.webDriver;
            File finalSource = ts.getScreenshotAs(OutputType.FILE);
            String destination = MyRunner.sReportPath + "\\failureScreenshots\\" + screenshotName + ".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(finalSource, finalDestination);
            Reporter.addScreenCaptureFromPath(finalDestination.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}