package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomConditions {

    public static ExpectedCondition<WebElement> elementHasText(final By elementsLocator, final int index, final String expectedText) {
        return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {
            private String actualText;

            public WebElement apply(WebDriver driver) {

                WebElement actualElement =driver.findElements(elementsLocator).get(index);
                actualText = actualElement.getText();
                if (!actualText.contains(expectedText)) {
                    return null;
                }
                return actualElement;
            }

            public String toString() {
                return String.format("element of list should have (\'%s\') text, while actual text is ('%s')", expectedText, actualText);
            }
        });
    }

    public static ExpectedCondition<List<WebElement>> texts(final By elementsLocator, final String... expectedTexts) {
        return elementExceptionsCatcher(new ExpectedCondition<List<WebElement>>() {
            private List<String> elementsTexts;

            public List<WebElement> apply(WebDriver driver) {

                elementsTexts = new ArrayList<>();
                List<WebElement> elements = driver.findElements(elementsLocator);

                for (int i = 0; i < elements.size(); i++) {
                    elementsTexts.add(elements.get(i).getText());
                }

                if (elements.size() != expectedTexts.length) {
                    return null;
                }

                for (int i = 0; i < elements.size(); i++) {
                    if (!elementsTexts.get(i).contains(expectedTexts[i])) {
                        return null;
                    }
                }
                return elements;
            }

            public String toString() {
                return String.format("elements of list should have ('%s') texts, " +
                        "while actual texts are ('%s')", elementsTexts.toString(),  Arrays.toString(expectedTexts));
            }
        });
    }

    private static <V> ExpectedCondition<V> elementExceptionsCatcher(final ExpectedCondition<V> condition) {
        return new ExpectedCondition<V>() {
            public V apply(WebDriver input) {
                try {
                    return condition.apply(input);
                } catch (StaleElementReferenceException | ElementNotVisibleException | IndexOutOfBoundsException e) {
                    return null;
                }
            }

            public String toString() {
                return condition.toString();
            }
        };
    }
}