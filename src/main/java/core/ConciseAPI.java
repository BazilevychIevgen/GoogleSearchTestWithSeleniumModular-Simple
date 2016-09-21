package core;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.BasePage.driver;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by barocko on 9/13/2016.
 */
public abstract class ConciseAPI {

    public abstract WebDriver getWebDriver();

    public <V> V assertThat(ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }

    public <V> V assertThat(ExpectedCondition<V> condition, int timeout) {
        return (new WebDriverWait(getWebDriver(), timeout)).until(condition);
    }

    public void open(String url) {
        driver.get(url);
    }

    public WebElement $(By locator) {
        return assertThat(visibilityOfElementLocated(locator));
    }

    public By byCss(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static By byText(String text) {
        return By.xpath(".//*/text()[normalize-space(.) = '" + text + "']/parent::*");
    }

    public static By byTitle(String text) {
        return By.xpath(".//*[contains(@title, " + Quotes.escape(text) + ")]");
    }
}
