package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomConditions {

    public static ExpectedCondition<WebElement> elementHasText(final By elements, final int index, final String expectedText) {
        return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {
            private String actualText;

            public WebElement apply(WebDriver driver) {

                WebElement actualElement =driver.findElements(elements).get(index);
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

    public static ExpectedCondition<List<WebElement>> texts(final By elements, final String... expectedTexts) {
        return elementExceptionsCatcher(new ExpectedCondition<List<WebElement>>() {
            private List<String> elementsTexts;

            public List<WebElement> apply(WebDriver driver) {

                elementsTexts = new ArrayList<>();
                List<WebElement> innerElements = driver.findElements(elements);

                for (int i = 0; i < innerElements.size(); i++) {
                    elementsTexts.add(innerElements.get(i).getText());
                }

                if (innerElements.size() != expectedTexts.length) {
                    return null;
                }

                for (int i = 0; i < innerElements.size(); i++) {
                    if (!elementsTexts.get(i).contains(expectedTexts[i])) {
                        return null;
                    }
                }
                return innerElements;
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