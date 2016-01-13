package com.wix.spirinmikhail.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import static com.wix.spirinmikhail.helpers.SelectorsDataBase.MainPg;
import static com.wix.spirinmikhail.helpers.SelectorsDataBase.EditPg;
import static com.wix.spirinmikhail.helpers.SelectorsDataBase.TblSel;

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
    public WebElement getElement(Enum query) {
        return driver.findElement(SelectorsDataBase.getSelector(query, selectorsType));
    }

    public WebElement getElement(Enum query, String param) {
        return driver.findElement(SelectorsDataBase.getSelector(query, selectorsType, param));
    }

    public List<WebElement> getElements(Enum query) {
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
        Integer pages = getElements(TblSel.PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        for (int i = 0; i < pages; i++) {
            for (WebElement item : getElements(TblSel.COMMENT_LINE)) {

                // if expected != null then check this field
                // If null -> don't check this field
                // if expected != current -> continue to next comment on page
                if (comment.getUniqueNumber() != null) {
                    currentNumber = Integer.valueOf(item.findElement(SelectorsDataBase.
                            getSelector(TblSel.COMMENT_NUMBER_IN_LINE, selectorsType)).getText());
                    if (!currentNumber.equals(comment.getUniqueNumber())) continue;
                }

                if (comment.getCommentText() != null) {
                    currentCommentText = item.findElement(SelectorsDataBase.
                            getSelector(TblSel.COMMENT_TEXT_IN_LINE, selectorsType)).getText();
                    if (!currentCommentText.equals(comment.getCommentText())) continue;
                }

                if (comment.getIsActive() != null) {
                    currentIsActive = item.findElement(SelectorsDataBase.
                            getSelector(TblSel.COMMENT_ACTIVE_IN_LINE, selectorsType)).getText().equals("V");
                    if (!currentIsActive.equals(comment.getIsActive())) continue;
                }

                if (comment.getCategories() != null) {
                    currentCategories = item.findElement(SelectorsDataBase.
                            getSelector(TblSel.COMMENT_CATEGORIES_IN_LINE, selectorsType)).getText();
                    if (!currentCategories.equals(comment.getCategories())) continue;
                }
                return true;
            }
            getElement(TblSel.PAGINATION_NEXT_PAGE).click();
        }
        return false;
    }

    public boolean verifyThatErrorMessageNumberFieldAppears(String expectedErrorMessage) {
        WebElement errorField = getElement(EditPg.ERROR_MESSAGE_FIELD);
        return errorField.getText().equals(expectedErrorMessage);
    }

    public boolean verifyThatCommentsAreSortedByNumber() {
        Integer previousValue = 0;
        Integer currentValue;
        Integer pages = getElements(TblSel.PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        for (int i = 0; i < pages; i++) {
            for (WebElement item : getElements(TblSel.COMMENT_LINE)) {
                currentValue = Integer.valueOf(item.findElement(SelectorsDataBase.
                        getSelector(TblSel.COMMENT_NUMBER_IN_LINE, selectorsType)).getText());
                if (currentValue < previousValue) return false;
                previousValue = currentValue;
            }
            getElement(TblSel.PAGINATION_NEXT_PAGE).click();
        }
        return true;
    }

    public boolean verifyThatCommentsAreSortedByCommentText() {
        String previousValue = "";
        String currentValue;
        Integer pages = getElements(TblSel.PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        for (int i = 0; i < pages; i++) {
            for (WebElement item : getElements(TblSel.COMMENT_LINE)) {
                currentValue = item.findElement(SelectorsDataBase.
                        getSelector(TblSel.COMMENT_TEXT_IN_LINE, selectorsType)).getText();
                if (previousValue.compareTo(currentValue) > 0) return false;
                previousValue = currentValue;
            }
            getElement(TblSel.PAGINATION_NEXT_PAGE).click();
        }
        return true;
    }

    public boolean verifyThatCommentsAreSortedByActive() {
        String previousValue = "";
        String currentValue;
        Integer pages = getElements(TblSel.PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        for (int i = 0; i < pages; i++) {
            for (WebElement item : getElements(TblSel.COMMENT_LINE)) {
                currentValue = item.findElement(SelectorsDataBase.
                        getSelector(TblSel.COMMENT_ACTIVE_IN_LINE, selectorsType)).getText();
                if (previousValue.compareTo(currentValue) > 0) return false;
                previousValue = currentValue;
            }
            getElement(TblSel.PAGINATION_NEXT_PAGE).click();
        }
        return true;
    }

    public boolean verifyFilterBy(Enum filteredItem, String keyToFilter) {
        Integer pages = getElements(TblSel.PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        for (int i = 0; i < pages; i++) {
            for (WebElement item : getElements(TblSel.COMMENT_LINE)) {
                if (!item.findElement(SelectorsDataBase.getSelector(filteredItem, selectorsType))
                        .getText().contains(keyToFilter)) return false;
            }
            getElement(TblSel.PAGINATION_NEXT_PAGE).click();
        }
        return true;
    }

    public boolean verifyDividerWeight(String expectedWeight) {
        return getElement(MainPg.DIVIDER).getCssValue("height").equals(expectedWeight);
    }

    public boolean verifyHeaderUnderlined() {
        return getElements(TblSel.LINKS_FOR_SORTING)
                .stream().allMatch(v -> v.getCssValue("text-decoration").equals("underline"));
    }
}
