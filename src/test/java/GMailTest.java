import core.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.GMailPages;


/**
 * Created by barocko on 9/13/2016.
 */
public class GMailTest extends TestData  {

    private WebDriver driver = new FirefoxDriver();

    GMailPages pages=new GMailPages(driver);


    @Before
    public void setUp() {
        Configuration.timeout = 15;
    }

    @After
    public void closeDriver() {
        driver.quit();
    }


    @Test
    public void  testLoginSendReceiveAndSearch() {
        String emailSubject = "Hello,Ukraine " + System.currentTimeMillis();

        pages.openApp();
        pages.login(mailLogin,mailPassword);

        pages.composeEmail();
        pages.send(mailLogin, emailSubject);

        pages.refresh();

        pages.goToSent();


        pages.goToInbox();
        pages.search(emailSubject);

    }
}
