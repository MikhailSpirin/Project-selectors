package com.wix.spirinmikhail.helpers;

import com.wix.spirinmikhail.helpers.Comment;
import com.wix.spirinmikhail.helpers.SelectorsDataBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import static com.wix.spirinmikhail.helpers.SelectorsDataBase.SelectorsKeys;

/**
 * Created by mikhails on 24.12.2015
 */
public class TestHelper {

    private static WebDriver driver;

    public String selectorsType = "CSS"; // default value

    public TestHelper() {

        driver = new FirefoxDriver();

        FileInputStream configFile;
        Properties properties = new Properties();
        String selectorsFromFile = "";
        try {
            configFile = new FileInputStream("target/test-classes/config.properties");
            properties.load(configFile);
            selectorsFromFile = properties.getProperty("selectors.type");
        } catch (IOException e) {
            System.err.println("Error: There is no config file!");
        }

        if (selectorsFromFile.equals("css")) {
            selectorsType = "CSS";
            System.out.println("------- CSS selectors are chosen --------------------");
        }
        if (selectorsFromFile.equals("xpath")) {
            selectorsType = "XPATH";
            System.out.println("------- XPATH selectors are chosen --------------------");
        }
    }

    // Wrappers for css/xpath handling
    public WebElement getElement(SelectorsKeys query) {
        return driver.findElement(SelectorsDataBase.getSelector(query, selectorsType));
    }

    public WebElement getElement(SelectorsKeys query, String param) {
        return driver.findElement(SelectorsDataBase.getSelector(query, selectorsType, param));
    }

    public List<WebElement> getElements(SelectorsKeys query) {
        return driver.findElements(SelectorsDataBase.getSelector(query, selectorsType));
    }

    public void getURL(String url) { driver.get(url);  }

    public WebDriver getDriver() { return driver; }

    public void quit() { driver.quit();  }

    public boolean verifyThatCommentIsPresent(Comment comment) {
        Integer currentNumber;
        String currentCommentText;
        Boolean currentIsActive;
        String currentCategories;

        driver.get("http://comments.azurewebsites.net/");
        Integer pages = getElements(SelectorsKeys.ALL_PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        for (int i = 0; i < pages; i++) {
            for (WebElement item : getElements(SelectorsKeys.COMMENT_LINE)) {

                // if expected != null then check this field
                // If null -> don't check this field
                // if expected != current -> continue to next comment on page
                if (comment.getUniqueNumber() != null) {
                    currentNumber = Integer.valueOf(item.findElement(SelectorsDataBase.
                            getSelector(SelectorsKeys.COMMENT_NUMBER_IN_LINE, selectorsType)).getText());
                    if (!currentNumber.equals(comment.getUniqueNumber())) continue;
                }

                if (comment.getCommentText() != null) {
                    currentCommentText = item.findElement(SelectorsDataBase.
                            getSelector(SelectorsKeys.COMMENT_TEXT_IN_LINE, selectorsType)).getText();
                    if (!currentCommentText.equals(comment.getCommentText())) continue;
                }

                if (comment.getIsActive() != null) {
                    currentIsActive = item.findElement(SelectorsDataBase.
                            getSelector(SelectorsKeys.COMMENT_ACTIVE_IN_LINE, selectorsType)).getText().equals("V");
                    if (!currentIsActive.equals(comment.getIsActive())) continue;
                }

                if (comment.getCategories() != null) {
                    currentCategories = item.findElement(SelectorsDataBase.
                            getSelector(SelectorsKeys.COMMENT_CATEGORIES_IN_LINE, selectorsType)).getText();
                    if (!currentCategories.equals(comment.getCategories())) continue;
                }
                return true;
            }
            getElement(SelectorsKeys.NEXT_PAGE).click();
        }
        return false;
    }

    public boolean verifyThatErrorMessageNumberFieldAppears(String expectedErrorMessage) {
        WebElement errorField = getElement(SelectorsKeys.ERROR_MESSAGE_FIELD);
        return errorField.getText().equals(expectedErrorMessage);
    }

    public boolean verifyThatCommentsAreSortedByNumber() {
        Integer previousValue = 0;
        Integer currentValue;
        Integer pages = getElements(SelectorsKeys.ALL_PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        for (int i = 0; i < pages; i++) {
            for (WebElement item : getElements(SelectorsKeys.COMMENT_LINE)) {
                currentValue = Integer.valueOf(item.findElement(SelectorsDataBase.
                        getSelector(SelectorsKeys.COMMENT_NUMBER_IN_LINE, selectorsType)).getText());
                if (currentValue < previousValue) return false;
                previousValue = currentValue;
            }
            getElement(SelectorsKeys.NEXT_PAGE).click();
        }
        return true;
    }

    public boolean verifyThatCommentsAreSortedByCommentText() {
        String previousValue = "";
        String currentValue;
        Integer pages = getElements(SelectorsKeys.ALL_PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        for (int i = 0; i < pages; i++) {
            for (WebElement item : getElements(SelectorsKeys.COMMENT_LINE)) {
                currentValue = item.findElement(SelectorsDataBase.
                        getSelector(SelectorsKeys.COMMENT_TEXT_IN_LINE, selectorsType)).getText();
                if (previousValue.compareTo(currentValue) > 0) return false;
                previousValue = currentValue;
            }
            getElement(SelectorsKeys.NEXT_PAGE).click();
        }
        return true;
    }

    public boolean verifyThatCommentsAreSortedByActive() {
        String previousValue = "";
        String currentValue;
        Integer pages = getElements(SelectorsKeys.ALL_PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        for (int i = 0; i < pages; i++) {
            for (WebElement item : getElements(SelectorsKeys.COMMENT_LINE)) {
                currentValue = item.findElement(SelectorsDataBase.
                        getSelector(SelectorsKeys.COMMENT_ACTIVE_IN_LINE, selectorsType)).getText();
                if (previousValue.compareTo(currentValue) > 0) return false;
                previousValue = currentValue;
            }
            getElement(SelectorsKeys.NEXT_PAGE).click();
        }
        return true;
    }

    public boolean verifyFilterBy(SelectorsKeys filteredItem, String keyToFilter) {
        Integer pages = getElements(SelectorsKeys.ALL_PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        for (int i = 0; i < pages; i++) {
            for (WebElement item : getElements(SelectorsKeys.COMMENT_LINE)) {
                if (!item.findElement(SelectorsDataBase.getSelector(filteredItem, selectorsType))
                        .getText().contains(keyToFilter)) return false;
            }
            getElement(SelectorsKeys.NEXT_PAGE).click();
        }
        return true;
    }

    public boolean verifyDividerWeight(String expectedWeight) {
        return getElement(SelectorsKeys.DIVIDER).getCssValue("height").equals(expectedWeight);
    }

    public boolean verifyHeaderUnderlined() {
        return getElements(SelectorsKeys.HEADERS)
                .stream().allMatch(v -> v.getCssValue("text-decoration").equals("underline"));
    }
}
