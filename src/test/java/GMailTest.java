import core.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.GMailPage;

import static data.TestData.mailLogin;
import static data.TestData.mailPassword;


/**
 * Created by barocko on 9/13/2016.
 */
public class GMailTest extends BaseTest {

    private WebDriver driver = new FirefoxDriver();

    GMailPage page=new GMailPage(driver);

    @Before
    public void setUp() {
        Configuration.timeout = 20;
    }

    @Test
    public  void  testLoginSendReceiveAndSearch() {
        String emailSubject = "Hello,Ukraine " + System.currentTimeMillis();

        page.openApp();
        page.login(mailLogin,mailPassword);

        page.composeEmail();
        page.send(mailLogin, emailSubject);

        page.refresh();
        page.assertMail(0,emailSubject);

        page.goToSent();
        page.assertMail(0,emailSubject);

        page.goToInbox();
        page.search(emailSubject);
        page.assertMails(emailSubject);

    }
}
