package com.wix.spirinmikhail;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

import static com.wix.spirinmikhail.TestHelper.Selectors;

/**
 * Created by mikhails on 21.12.2015
 */

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// TODO:
// -- more strict way of naming selectors
// -- unite css and xpath selectors in one hashmap (by storing not text, but 'By.byCssSelector(...)', By.xpath, parameters for one key)
// -- add parameters to checked elements - for categories, for comments
// -- improve way to get quantity of pages with comments (to enhance verifyThatCommentIsPresent)
// -- To do some wrappers for simple methods with simple selectors
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class SelectorTest {

    private TestHelper testHelper;

    @Before
    public void before() {
        testHelper = new TestHelper();
        testHelper.getURL("http://comments.azurewebsites.net/");
    }

    @Test
    public void addCommentTest() {
        final String expectedCommentText = "qwerty";

        System.out.println("Create new comment");
        testHelper.getElement(Selectors.NEW_BUTTON).click();

        System.out.println("Fill in comment name" + expectedCommentText);
        testHelper.getElement(Selectors.COMMENT_TEXT_FIELD).sendKeys(expectedCommentText);

        System.out.println("Select Cat0 category");
        testHelper.getElement(Selectors.CAT_ZERO_CHECKBOX).click();

        System.out.println("Add it to current comment");
        testHelper.getElement(Selectors.ADD_SELECTED_CATEGORY).click();

        System.out.println("Click Save and Return");
        testHelper.getElement(Selectors.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that comment " + expectedCommentText + " is exist");
        assertTrue("Comment is not added", testHelper.verifyThatCommentIsPresent(expectedCommentText));
    }

    @Test
    public void duplicateCommentTest() {
        System.out.println("Select first comment");
        testHelper.getElement(Selectors.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("");
        String sourceCommentText = testHelper.getElement(Selectors.FIRST_COMMENT_TEXT).getText();

        System.out.println("Find button/link 'Duplicate...' and click it");
        testHelper.getElement(Selectors.DUPLICATE_BUTTON).click();

        System.out.println("Find text-field 'Number' and delete number from it");
        testHelper.getElement(Selectors.NUMBER_TEXT_FIELD).clear();

        System.out.println("Find 'Save and Return' and click it");
        testHelper.getElement(Selectors.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that copied comment exist");
        assertTrue("Comment is not copied", testHelper.verifyThatCommentIsPresent("Copy of" + sourceCommentText));
    }

    @Test
    public void changeCommentTest() {
        final String newCommentText = "New Name For Comment";

        System.out.println("Find first comment's checkbox and check it");
        testHelper.getElement(Selectors.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Find button/link 'Edit...' and click it");
        testHelper.getElement(Selectors.EDIT_BUTTON).click();

        System.out.println("Find text-field 'Comment' and change it");
        WebElement commentField = testHelper.getElement(Selectors.COMMENT_TEXT_FIELD);
        commentField.clear();
        commentField.sendKeys(newCommentText);

        System.out.println("Find 'Save and Return' and click it");
        testHelper.getElement(Selectors.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that copied comment exist");
        assertTrue("Comment is not edited", testHelper.verifyThatCommentIsPresent(newCommentText));
    }

    @Test
    public void deleteCommentTest() {
        System.out.println("Find first comment's checkbox and check it");
        testHelper.getElement(Selectors.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Get first comment text");
        String commentText = testHelper.getElement(Selectors.FIRST_COMMENT_TEXT).getText();

        System.out.println("Find button/link 'Delete...' and click it");
        testHelper.getElement(Selectors.DELETE_BUTTON).click();

        System.out.println("Press 'Yes' on popup");
        testHelper.getElement(Selectors.DELETE_POPUP_YES_BUTTON).click();

        System.out.println("Verify that deleted comment is not exist");
        assertTrue("Comment is not deleted", !testHelper.verifyThatCommentIsPresent(commentText));
    }

    @Test
    public void refreshPageTest() {
        System.out.println("Verify refresh via link on page");
        testHelper.getElement(Selectors.REFRESH_LINK).click();

        System.out.println("Verify refresh via driver");
        testHelper.getDriver().navigate().refresh();
    }

    @Test
    public void verifyHeaderColorTest() {
        final String expectedColor = "rgba(255, 255, 255, 1)";

        System.out.println("Get header color");
        String actualColor = testHelper.getElement(Selectors.MAIN_HEADER).getCssValue("color");

        System.out.println("Verify that color is " + expectedColor);
        Assert.assertEquals("Color is not as expected", expectedColor, actualColor);
    }

    @After
    public void after() {
        testHelper.quit();
        System.out.println("--------  Finished!  ------------------------------------");
    }
}
