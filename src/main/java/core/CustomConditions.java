package core;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.List;

public class CustomConditions {

    public static ExpectedCondition<WebElement> elementHasText(final List<WebElement> elements, final int index, final String expectedText) {
        return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {
            private String actualText;

            public WebElement apply(WebDriver driver) {

                WebElement actualElement = elements.get(index);
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

    public static ExpectedCondition<List<WebElement>> texts(final List<WebElement> elements, final String... expectedTexts) {
        return elementExceptionsCatcher(new ExpectedCondition<List<WebElement>>() {
            private List<String> elementsTexts;

            public List<WebElement> apply(WebDriver driver) {

                elementsTexts = new ArrayList<>();

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
                        "while actual texts are ('%s')", elementsTexts.toArray().toString(), expectedTexts.toString());
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