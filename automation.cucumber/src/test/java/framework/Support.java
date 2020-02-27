package framework;

import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class Support {

    protected static String strTestCase;
    protected Scenario scenario;

    @Before
    public void getTC(Scenario scene)  {
        this.scenario = scene;
        strTestCase = scenario.getName();
        Reporter.addScenarioLog(strTestCase);
    }

    protected static WebDriver setBrowser(String browser)  {
        WebDriver webDr = null;
        String driverPath = Config.getProperty("driverPath");

        switch (browser)  {
            case "Chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-popup-blocking");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
                webDr = new ChromeDriver(chromeOptions);
                break;
            case "Opera":
                OperaOptions operaOptions = new OperaOptions();
                operaOptions.addArguments("disable-popup-blocking");
                operaOptions.addArguments("disable-infobars");
                System.setProperty("webdriver.ie.driver", driverPath + "operadriver.exe");
                webDr = new OperaDriver();
                break;
            case "IE":
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.ignoreZoomSettings();
                ieOptions.introduceFlakinessByIgnoringSecurityDomains();
                System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
                webDr = new InternetExplorerDriver(ieOptions);
                break;
            default:
                System.out.println("Invalid browser specified in Config file: " + browser);
                break;
        }
        return webDr;
    }

    protected static HashMap<String, ArrayList<String>> getAllObjects()   {

        HashMap<String, ArrayList<String>> objData = new HashMap<>();
        ArrayList<String> objInnerData;
        try {
            FileInputStream fis=new FileInputStream(new File(Config.getProperty("objectRepository")));
            XSSFWorkbook wb= new XSSFWorkbook(fis);
            XSSFSheet sheet=wb.getSheetAt(0);
            for(int i=1; i< sheet.getPhysicalNumberOfRows(); i++)  {
                Row row = sheet.getRow(i);
                objInnerData = new ArrayList<>();
                objInnerData.add(0, row.getCell(1).getStringCellValue().trim());
                objInnerData.add(1, row.getCell(2).getStringCellValue().trim());
                objData.put(row.getCell(0).getStringCellValue().trim(), objInnerData);
                objInnerData = null;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return objData;
    }

    private String convertToMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++)
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

        return sb.toString();
    }

}
