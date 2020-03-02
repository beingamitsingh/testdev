package framework;

import com.vimalselvam.cucumber.listener.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static WebElement getElement(String elementName)   {
        WebElement webElement = null;
        ArrayList<String> objectData = MyRunner.allObjects.get(elementName);

        try {
            switch (objectData.get(0))  {
                case "ID":
                    webElement= MyRunner.webDriver.findElement(By.id(objectData.get(1)));
                    break;
                case "ClassName":
                    webElement= MyRunner.webDriver.findElement(By.className(objectData.get(1)));
                    break;
                case "LinkText":
                    webElement= MyRunner.webDriver.findElement(By.linkText(objectData.get(1)));
                    break;
                case "CSS_Selector":
                    webElement= MyRunner.webDriver.findElement(By.cssSelector(objectData.get(1)));
                    break;
                case "TagName":
                    webElement= MyRunner.webDriver.findElement(By.tagName(objectData.get(1)));
                    break;
                case "Name":
                    webElement= MyRunner.webDriver.findElement(By.name(objectData.get(1)));
                    break;
                case "PartialLinkText":
                    webElement= MyRunner.webDriver.findElement(By.partialLinkText(objectData.get(1)));
                    break;
                case "Xpath":
                    webElement= MyRunner.webDriver.findElement(By.xpath(objectData.get(1)));
                    break;
            }
        }
        catch (NullPointerException ex) {
            Report.fail(ex.getMessage());
        }
        return webElement;
    }

    public static List<WebElement> getElements(String elementName)   {
        List<WebElement> webElements = new ArrayList<>();
        ArrayList<String> objectData = MyRunner.allObjects.get(elementName);

        try {
            switch (objectData.get(0))  {
                case "ID":
                    webElements= MyRunner.webDriver.findElements(By.id(objectData.get(1)));
                    break;
                case "ClassName":
                    webElements= MyRunner.webDriver.findElements(By.className(objectData.get(1)));
                    break;
                case "LinkText":
                    webElements= MyRunner.webDriver.findElements(By.linkText(objectData.get(1)));
                    break;
                case "CSS_Selector":
                    webElements= MyRunner.webDriver.findElements(By.cssSelector(objectData.get(1)));
                    break;
                case "TagName":
                    webElements= MyRunner.webDriver.findElements(By.tagName(objectData.get(1)));
                    break;
                case "Name":
                    webElements= MyRunner.webDriver.findElements(By.name(objectData.get(1)));
                    break;
                case "PartialLinkText":
                    webElements= MyRunner.webDriver.findElements(By.partialLinkText(objectData.get(1)));
                    break;
                case "Xpath":
                    webElements= MyRunner.webDriver.findElements(By.xpath(objectData.get(1)));
                    break;
            }
        }
        catch (NullPointerException ex) {
            Report.fail(ex.getMessage());
        }
        return webElements;
    }

    public static void sendKeys(WebElement webElement, String string)  {
        webElement.sendKeys(string.trim());
        String webElementName= webElement.toString().split("->")[1].trim();
        Reporter.addStepLog(webElementName + ":" + string.trim());
    }

    public static void wait(String strElement, int seconds)   {
        WebDriverWait wait = new WebDriverWait (MyRunner.webDriver, seconds);
        wait.until(ExpectedConditions.visibilityOf(getElement(strElement)));
    }
}
