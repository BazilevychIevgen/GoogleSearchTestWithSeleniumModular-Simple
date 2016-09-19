import core.ConciseAPI;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by barocko on 9/15/2016.
 */
public class BaseTest extends ConciseAPI {

    WebDriver driver = new FirefoxDriver();

    @After
    public void tearDown() {
        driver.quit();
    }
    public WebDriver getWebDriver() {
        return driver;
    }
}
