package com.wix.spirinmikhail.tests;

import com.wix.spirinmikhail.helpers.Comment;
import com.wix.spirinmikhail.helpers.SelectorsDataBase;
import com.wix.spirinmikhail.helpers.TestHelper;
import org.junit.*;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

/**
 * Created by mikhails on 21.12.2015
 */

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// TODO:
// -- more strict way of naming selectors
// -- To do some wrappers for simple methods with simple selectors
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class CommentsTest {

    private TestHelper testHelper;

    @Before
    public void before() {
        testHelper = new TestHelper();
        testHelper.getURL("http://comments.azurewebsites.net/");
    }

    // task 1 - add comment and check if it's added
    @Test
    public void addCommentTest() {
        final String expectedCommentText = "qwerty";

        System.out.println("Create new comment");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.NAV_BUTTON, "New...").click();

        System.out.println("Fill in comment name" + expectedCommentText);
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.COMMENT_TEXT_FIELD).sendKeys(expectedCommentText);

        System.out.println("Select Cat0 category");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.CAT_ZERO_CHECKBOX).click();

        System.out.println("Add it to current comment");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.ADD_SELECTED_CATEGORY).click();

        System.out.println("Click Save and Return");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that comment " + expectedCommentText + " is exist");
        assertTrue("Comment is not added",
                testHelper.verifyThatCommentIsPresent(new Comment(null, expectedCommentText, null, null)));
    }

    // task 2 - duplicate comment
    @Test
    public void duplicateCommentTest() {

        System.out.println("Select first comment");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Save text of the first comment");
        String sourceCommentText = testHelper.getElement(SelectorsDataBase.SelectorsKeys.FIRST_COMMENT_TEXT).getText();

        System.out.println("Duplicate selected comment");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.NAV_BUTTON, "Duplicate...").click();

        System.out.println("In opened popup delete number (for automatic generation");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.NUMBER_TEXT_FIELD).clear();

        System.out.println("Click 'Save and Return' button");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that copied comment exist");
        assertTrue("Comment is not copied",
                testHelper.verifyThatCommentIsPresent(new Comment(null, "Copy of" + sourceCommentText, null, null)));
    }

    // task3 - changing comment
    @Test
    public void changeCommentTest() {
        final String newCommentText = "New Name For Comment";

        System.out.println("Select first comment");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Edit selected comment - open popup");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.NAV_BUTTON, "Edit...").click();

        System.out.println("Change text for comment");
        WebElement commentField = testHelper.getElement(SelectorsDataBase.SelectorsKeys.COMMENT_TEXT_FIELD);
        commentField.clear();
        commentField.sendKeys(newCommentText);

        System.out.println("Click 'Save and Return' button");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that copied comment exist");
        assertTrue("Comment is not edited",
                testHelper.verifyThatCommentIsPresent(new Comment(null, newCommentText, null, null)));
    }

    // task 4 - deleting comment
    @Test
    public void deleteCommentTest() {
        System.out.println("Select first comment");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Get first comment text");
        String commentText = testHelper.getElement(SelectorsDataBase.SelectorsKeys.FIRST_COMMENT_TEXT).getText();

        System.out.println("Delete selected comment");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.NAV_BUTTON, "Delete").click();

        System.out.println("Approve deletion operation");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.DELETE_POPUP_YES_BUTTON).click();

        System.out.println("Verify that deleted comment is not exist");
        assertTrue("Comment is not deleted",
                !testHelper.verifyThatCommentIsPresent(new Comment(null, commentText, null, null)));
    }

    // Task 7 - Checking unique field "Number" while creating new comment
    @Test
    public void verifyNumberIsUniqueTest() {
        final Integer uniqueExistNumber = 1;
        final String expectedErrorMessage = "The Number field should contain value from 0 to 999 and should be unique";

        System.out.println("Create new comment");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.NAV_BUTTON, "New...").click();

        System.out.println("Fill in comment name - quertyytrewq");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.COMMENT_TEXT_FIELD).sendKeys("quertyytrewq");

        System.out.println("Fill in comment name - " + uniqueExistNumber);
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.NUMBER_TEXT_FIELD).sendKeys(String.valueOf(uniqueExistNumber));

        System.out.println("Select Cat0 category");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.CAT_ZERO_CHECKBOX).click();

        System.out.println("Add it to current comment");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.ADD_SELECTED_CATEGORY).click();

        System.out.println("Click Save and Return");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that error message appears");
        assertTrue("Error message doesn't appear or it is wrong",
                testHelper.verifyThatErrorMessageNumberFieldAppears(expectedErrorMessage));
    }

    // task 10.1 activation check ---
    // very fast hardcoded check. Should be verified in another way -
    // getting random comment with status active and changing it
    // Deactivation check is similar
    @Test
    public void verifyCommentActivationTest() {
        System.out.println("Select first comment");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Edit selected comment - open popup");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.NAV_BUTTON, "Edit...").click();

        System.out.println("Deactivate comment");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.STATUS_FIELD).click();

        System.out.println("Click 'Save and Return' button");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that comment is changed");
        assertTrue("Comment is not changed",
                testHelper.verifyThatCommentIsPresent(new Comment(0, "Comment Text 0", false, "Cat0")));
    }

    @After
    public void after() {
        testHelper.quit();
        System.out.println("--------  Finished!  --------------------------------");
    }
}
