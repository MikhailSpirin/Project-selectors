package com.wix.spirinmikhail.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.function.BiPredicate;
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

    public Comment getFirstCommentMatchTemplate(Comment templateComment) {
        Integer currentNumber;
        String currentNumberText;
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
                if (templateComment.getUniqueNumber() != null) {
                    currentNumber = Integer.valueOf(item.findElement(SelectorsDataBase.
                            getSelector(TblSel.COMMENT_NUMBER_IN_LINE, selectorsType)).getText());
                    if (!currentNumber.equals(templateComment.getUniqueNumber())) continue;
                }

                if (templateComment.getCommentText() != null) {
                    currentCommentText = item.findElement(SelectorsDataBase.
                            getSelector(TblSel.COMMENT_TEXT_IN_LINE, selectorsType)).getText();
                    if (!currentCommentText.equals(templateComment.getCommentText())) continue;
                }

                if (templateComment.getIsActive() != null) {
                    currentIsActive = item.findElement(SelectorsDataBase.
                            getSelector(TblSel.COMMENT_ACTIVE_IN_LINE, selectorsType)).getText().equals("V");
                    if (!currentIsActive.equals(templateComment.getIsActive())) continue;
                }

                if (templateComment.getCategories() != null) {
                    currentCategories = item.findElement(SelectorsDataBase.
                            getSelector(TblSel.COMMENT_CATEGORIES_IN_LINE, selectorsType)).getText();
                    if (!currentCategories.equals(templateComment.getCategories())) continue;
                }

                currentNumberText = item.findElement(SelectorsDataBase.getSelector(TblSel.COMMENT_NUMBER_IN_LINE, selectorsType)).getText();
                currentNumber = !(currentNumberText.equals("")) ? Integer.valueOf(currentNumberText) : null;
                currentCommentText = item.findElement(SelectorsDataBase.getSelector(TblSel.COMMENT_TEXT_IN_LINE, selectorsType)).getText();
                currentIsActive = item.findElement(SelectorsDataBase.getSelector(TblSel.COMMENT_ACTIVE_IN_LINE, selectorsType)).getText().equals("V");
                currentCategories = item.findElement(SelectorsDataBase.getSelector(TblSel.COMMENT_CATEGORIES_IN_LINE, selectorsType)).getText();

                return new Comment(currentNumber, currentCommentText, currentIsActive, currentCategories, item);
            }
            nextPageClicker();
        }
        return null;
    }

    public boolean verifyThatCommentIsPresent(Comment comment) {
        return !(getFirstCommentMatchTemplate(comment) == null);
    }

    public boolean verifyThatErrorMessageNumberFieldAppears(String expectedErrorMessage) {
        return getElement(EditPg.ERROR_MESSAGE_FIELD).getText().equals(expectedErrorMessage);
    }

    public boolean verifyThatCommentsAreSortedBy(TblSel selector, BiPredicate<String, String> xIsGreaterThanY) {
        String previousValue = "";
        String currentValue;
        int i = 0;
        Integer pages = getElements(TblSel.PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        do {
            for (WebElement item : getElements(TblSel.COMMENT_LINE)) {
                currentValue = item.findElement(SelectorsDataBase.getSelector(selector, selectorsType)).getText();
                if (xIsGreaterThanY.test(previousValue, currentValue)) return false;
                previousValue = currentValue;
            }
            nextPageClicker();
        } while (i < pages);
        return true;
    }

    public boolean verifyFilterBy(Enum filteredItem, String keyToFilter) {
        Integer pages = getElements(TblSel.PAGINATION_ELEMENTS_WITHOUT_TEXT).size();
        int i = 0;
        do {
            if (getElements(TblSel.COMMENT_LINE).stream()
                    .noneMatch(item -> item.findElement(SelectorsDataBase.getSelector(filteredItem, selectorsType))
                            .getText().contains(keyToFilter))) return false;
            nextPageClicker();
            i++;
        } while (i < pages);
        return true;
    }

    public boolean verifyDividerWeight(String expectedWeight) {
        return getElement(MainPg.DIVIDER).getCssValue("height").equals(expectedWeight);
    }

    public boolean verifyHeaderUnderlined() {
        return getElements(TblSel.LINKS_FOR_SORTING)
                .stream().allMatch(v -> v.getCssValue("text-decoration").equals("underline"));
    }



    private void nextPageClicker() {
        if (getElements(TblSel.PAGINATION_NEXT_PAGE).size() != 0)
            getElement(TblSel.PAGINATION_NEXT_PAGE).click();
    }
}
