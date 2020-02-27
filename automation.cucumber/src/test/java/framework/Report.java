package framework;

import com.vimalselvam.cucumber.listener.Reporter;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;

public class Report {

    public static void pass(String message)  {
        Reporter.addStepLog(message);
        assertTrue(message, true);
    }

    public static void fail(String message)  {
        Reporter.addStepLog(message);
        Assert.fail(message);
    }
}