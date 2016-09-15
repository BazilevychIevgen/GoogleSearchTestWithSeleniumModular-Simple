package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by barocko on 9/13/2016.
 */
public class GMailPages extends BasePage {

    //public static List<WebElement> mails = driver.findElements(By.cssSelector("[role='main'] .zA"));

    public GMailPages(WebDriver driver) {
        super(driver);
    }
    public  void send(String email, String subject) {
        $(driver, By.name("to")).sendKeys(email);
        $(driver, By.name("subjectbox")).sendKeys(subject);
        $(driver, By.id(":lo")).click();
    }

    public  void login(String email,String password) {
        $(driver, byCss("#Email")).sendKeys(email + Keys.ENTER);
        $(driver, byCss("#Passwd")).sendKeys(password + Keys.ENTER);
    }

    public void openApp() {
        open(driver,"http://gmail.com");
    }


    public void search(String text) {
        $(driver, By.name("q")).sendKeys("\"" + text + "\"" + Keys.ENTER);
    }

    public void composeEmail() {
        $(driver, byText("COMPOSE")).click();
    }

    public void refresh() {
        $(driver, byCss(".asf")).click();
    }

    public void goToSent() {
        $(driver, byTitle("Sent Mail")).click();
    }

    public void goToInbox() {
        $(driver, byTitle("Inbox")).click();
    }
   /*
    public static void assertMail(int index,String mailText) {
    assertTrue(driver.findElements(By.cssSelector("[role='main'] .zA")).get(index).contains(mailText));
    }
    */

}
