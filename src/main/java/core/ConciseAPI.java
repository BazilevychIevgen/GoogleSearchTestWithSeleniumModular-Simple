package core;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by barocko on 9/13/2016.
 */
public abstract class ConciseAPI {

    public abstract WebDriver getWebDriver();

    public <V> V assertThat( ExpectedCondition<V> condition) {
        return (new WebDriverWait(getWebDriver(), 10)).until(condition);
    }

    public static <V> V assertThat(WebDriver driver, ExpectedCondition<V> condition, int timeout) {
        return new WebDriverWait(driver, timeout).until(condition);
    }

    public void open(WebDriver driver,String url) {
       driver.get(url);
    }

    public WebElement $(WebDriver driver, By locator) {
        return assertThat(visibilityOfElementLocated(locator));
    }

    public By byCss(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static By byText(String text) {
        return By.xpath(".//*/text()[normalize-space(.) = '" + text + "']/parent::*");
    }

    public static By byTitle(String text) {
        return By.xpath(".//*[contains(@title, " + text + ")]");
    }
}
