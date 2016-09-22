import core.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.GMailPage;

import static data.TestData.mailLogin;
import static data.TestData.mailPassword;
import static pages.GMailPage.*;


/**
 * Created by barocko on 9/13/2016.
 */
public class GMailTest extends BaseTest {

    @Before
    public void setUp() {
        Configuration.timeout = 30;
    }

    @Test
    public void testLoginSendReceiveAndSearch() {
        String emailSubject = "Hello,Ukraine " + System.currentTimeMillis();

        openApp();
        login(mailLogin, mailPassword);

        composeEmail();
        send(mailLogin, emailSubject);

        refresh();
        assertMail(0, emailSubject);

        goToSent();
        assertMail(0, emailSubject);

        goToInbox();
        search(emailSubject);
        assertMails(emailSubject);

    }
}
