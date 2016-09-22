package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


import static core.ConciseAPI.*;
import static core.CustomConditions.elementHasText;
import static core.CustomConditions.texts;
import static org.junit.Assert.assertTrue;

/**
 * Created by barocko on 9/13/2016.
 */
public class GMailPage  {

    public static By mails = byCss("[role=main] .zA");

    public static void send(String email, String subject) {
        $(By.name("to")).sendKeys(email);
        $(By.name("subjectbox")).sendKeys(subject);
        $(byText("Send")).click();
    }

    public static void login(String email, String password) {
        $(byCss("#Email")).sendKeys(email + Keys.ENTER);
        $(byCss("#Passwd")).sendKeys(password + Keys.ENTER);
    }

    public static void openApp() {
        open("http://gmail.com");
    }

    public static void search(String text) {
        $(By.name("q")).sendKeys("\"" + text + "\"" + Keys.ENTER);
    }

    public static void composeEmail() {
        $(byText("COMPOSE")).click();
    }

    public static void refresh() {
        $(byCss(".asf")).click();
    }

    public static void goToSent() {
        $(byTitle("Sent Mail")).click();
    }

    public static void goToInbox() {
        $(byTitle("Inbox")).click();
    }

    public static void assertMail(int index, String mailText) {
        assertThat(elementHasText(mails, index, mailText));
    }

    public static void assertMails(String... mailTexts) {
        assertThat(texts(mails, mailTexts));
    }
}
