import core.ConciseAPI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static core.ConciseAPI.getDriver;
import static core.ConciseAPI.setDriver;

/**
 * Created by barocko on 9/15/2016.
 */
public class BaseTest {

    @BeforeClass
    public static void setUpDriver() {
        setDriver(new FirefoxDriver());
    }
    @AfterClass
    public static void closeDriver() {
        getDriver().quit();
    }

}
