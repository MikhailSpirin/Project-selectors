package com.wix.spirinmikhail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by mikhails on 24.12.2015
 */
public class TestHelper {

    private enum SelectorsType { CSS, XPATH }
    public enum Selectors { NEW_BUTTON, DUPLICATE_BUTTON, EDIT_BUTTON, DELETE_BUTTON, COMMENT_TEXT_FIELD,
        CAT_ZERO_CHECKBOX, ADD_SELECTED_CATEGORY, SAVE_RETURN_BUTTON, COMMENT_TEXT_LABEL,
        FIRST_COMMENT_CHECKBOX, FIRST_COMMENT_TEXT, NUMBER_TEXT_FIELD,
        DELETE_POPUP_YES_BUTTON,
        NEXT_PAGE, FIRST_PAGE, ALL_ELEMENTS_WITHOUT_TEXT, REFRESH_LINK, MAIN_HEADER}
    private static WebDriver driver;

    public SelectorsType selectorsType = SelectorsType.CSS; // default value

    // CSS selectors base
    private Map<Selectors, String> cssSelectors = new HashMap<Selectors, String>(){{
        put(Selectors.NEW_BUTTON, "div#command-navigation input[value='New...']");
        put(Selectors.DUPLICATE_BUTTON, "div#command-navigation input[value='Duplicate...']");
        put(Selectors.EDIT_BUTTON, "div#command-navigation input[value='Edit...']");
        put(Selectors.DELETE_BUTTON, "div#command-navigation input[value='Delete']");
        put(Selectors.COMMENT_TEXT_FIELD, "div.commenteditor input#Text");
        put(Selectors.CAT_ZERO_CHECKBOX, "div#alvailablecategories div:nth-of-type(1) input#Categories");
        put(Selectors.ADD_SELECTED_CATEGORY, "div.selectbuttons input[value='>']");
        put(Selectors.SAVE_RETURN_BUTTON, "div#editor-navigation input[value='Save & Return']");
        put(Selectors.COMMENT_TEXT_LABEL, "tr.webgrid-row-style td.textcolumn, tr.webgrid-alternating-row td.textcolumn");

        put(Selectors.FIRST_COMMENT_CHECKBOX, "tbody tr:nth-of-type(1) td.checkedcolumn input[type=checkbox]");
        put(Selectors.FIRST_COMMENT_TEXT, "tbody tr:nth-of-type(1) td.textcolumn");
        put(Selectors.NUMBER_TEXT_FIELD, "div.commenteditor input#Number");

        put(Selectors.DELETE_POPUP_YES_BUTTON, "div.ui-dialog-buttonset button:nth-of-type(1)");

        put(Selectors.NEXT_PAGE, "tr.webgrid-footer a:last-child");
        put(Selectors.FIRST_PAGE, "tr.webgrid-footer a[href='/?page=1']");
        put(Selectors.ALL_ELEMENTS_WITHOUT_TEXT, "tr.webgrid-footer td *");
        put(Selectors.REFRESH_LINK, "div#logindisplay a");
        put(Selectors.MAIN_HEADER, "header div#title h1");
    }};

    // xpath selectors base
    private Map<Selectors, String> xpathSelectors = new HashMap<Selectors, String>(){{
        put(Selectors.NEW_BUTTON, "//div[@id='command-navigation']/input[@value='New...']");
        put(Selectors.DUPLICATE_BUTTON, "//div[@id='command-navigation']/input[@value='Duplicate...']");
        put(Selectors.EDIT_BUTTON, "//div[@id='command-navigation']/input[@value='Edit...']");
        put(Selectors.DELETE_BUTTON, "//div[@id='command-navigation']/input[@value='Delete']");
        put(Selectors.COMMENT_TEXT_FIELD, "//div[@class='commenteditor']/input[@id='Text']");
        put(Selectors.CAT_ZERO_CHECKBOX, "//div[@id='alvailablecategories']/div[contains(.,'Cat0')]/input[@id='Categories']");
        put(Selectors.ADD_SELECTED_CATEGORY, "//div[@class='selectbuttons']/input[@value='>']");
        put(Selectors.SAVE_RETURN_BUTTON, "//div[@id='editor-navigation']/input[@value='Save & Return']");
        put(Selectors.COMMENT_TEXT_LABEL, "//tr[@class='webgrid-row-style' or 'webgrid-alternating-row']/td[@class='textcolumn']");

        put(Selectors.FIRST_COMMENT_CHECKBOX, "//tbody/tr[1]/td[@class='checkedcolumn']/input[@name='SelectedId']");
        put(Selectors.FIRST_COMMENT_TEXT, "//tbody/tr[1]/td[@class='textcolumn']");
        put(Selectors.NUMBER_TEXT_FIELD, "//div[@class='commenteditor']/input[@id='Number']");

        put(Selectors.DELETE_POPUP_YES_BUTTON, "//div[@class='ui-dialog-buttonset']/button[1]");

        put(Selectors.NEXT_PAGE, "//tr[@class='webgrid-footer']/td/*[last()]");
        put(Selectors.FIRST_PAGE, "//tr[@class='webgrid-footer']/td/*[contains(., '1')]");
        put(Selectors.ALL_ELEMENTS_WITHOUT_TEXT, "//tr[@class='webgrid-footer']/td/*");
        put(Selectors.REFRESH_LINK, "//div[@id='logindisplay']/a");
        put(Selectors.MAIN_HEADER, "//header/div[@id='title']/h1");
    }};

    public TestHelper() {
        driver = new ChromeDriver();

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
            selectorsType = SelectorsType.CSS;
            System.out.println("------- CSS selectors are chosen --------------------");
        }
        if (selectorsFromFile.equals("xpath")) {
            selectorsType = SelectorsType.XPATH;
            System.out.println("------- XPATH selectors are chosen --------------------");}
    }

    // Wrappers for css/xpath handling
    public WebElement getElement(Selectors query) {
        WebElement webElement = null;
        if (selectorsType == SelectorsType.CSS) webElement = driver.findElement(By.cssSelector(cssSelectors.get(query)));
        if (selectorsType == SelectorsType.XPATH) webElement = driver.findElement(By.xpath(xpathSelectors.get(query)));
        return webElement;
    }
    public List<WebElement> getElements(Selectors query) {
        List<WebElement> webElement = null;
        if (selectorsType == SelectorsType.CSS) webElement = driver.findElements(By.cssSelector(cssSelectors.get(query)));
        if (selectorsType == SelectorsType.XPATH) webElement = driver.findElements(By.xpath(xpathSelectors.get(query)));
        return webElement;
    }

    public void getURL(String url) { driver.get(url);  }

    public WebDriver getDriver() { return driver; }

    public void quit() { driver.quit();  }

    public boolean verifyThatCommentIsPresent(String commentText) {
        driver.get("http://comments.azurewebsites.net/");
        for (int i = 0; i < getElements(Selectors.ALL_ELEMENTS_WITHOUT_TEXT).size(); i++) {
            for (WebElement item : getElements(Selectors.COMMENT_TEXT_LABEL)) {
                if (item.getText().equals(commentText)) {
                    return true;
                }
            }
            getElement(Selectors.NEXT_PAGE).click();
        }
        return false;
    }


}
