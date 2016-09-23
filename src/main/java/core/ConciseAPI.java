package core;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by barocko on 9/13/2016.
 */
public abstract class ConciseAPI {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        ConciseAPI.driver = driver;
    }

    public static <V> V assertThat(ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }

    public static  <V> V assertThat(ExpectedCondition<V> condition, int timeout) {
        return new WebDriverWait(driver, timeout).until(condition);
    }

    public static void open(String url) {
        getDriver().get(url);
    }

    public static  WebElement $(By locator) {
        return assertThat(visibilityOfElementLocated(locator));
    }

    public  static By byCss(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static By byText(String text) {
        return By.xpath(".//*/text()[normalize-space(.) = '" + text + "']/parent::*");
    }

    public static By byTitle(String text) {
        return By.xpath(".//*[contains(@title, " + Quotes.escape(text) + ")]");
    }
}
