package com.wix.spirinmikhail.tests;

import com.wix.spirinmikhail.helpers.Comment;
import com.wix.spirinmikhail.helpers.TestHelper;
import org.junit.*;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;
import static com.wix.spirinmikhail.helpers.SelectorsDataBase.MainPg;
import static com.wix.spirinmikhail.helpers.SelectorsDataBase.EditPg;
import static com.wix.spirinmikhail.helpers.SelectorsDataBase.TblSel;

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
        testHelper.getElement(MainPg.NAV_BUTTON, "New...").click();

        System.out.println("Fill in comment name" + expectedCommentText);
        testHelper.getElement(EditPg.COMMENT_TEXT_FIELD).sendKeys(expectedCommentText);

        System.out.println("Select Cat0 category");
        testHelper.getElement(EditPg.CAT_ZERO_CHECKBOX).click();

        System.out.println("Add it to current comment");
        testHelper.getElement(EditPg.ADD_SELECTED_CATEGORY_BUTTON).click();

        System.out.println("Click Save and Return");
        testHelper.getElement(EditPg.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that comment " + expectedCommentText + " is exist");
        assertTrue("Comment is not added",
                testHelper.verifyThatCommentIsPresent(new Comment(null, expectedCommentText, null, null)));
    }

    // task 2 - duplicate comment
    @Test
    public void duplicateCommentTest() {

        System.out.println("Select first comment");
        testHelper.getElement(TblSel.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Save text of the first comment");
        String sourceCommentText = testHelper.getElement(TblSel.FIRST_COMMENT_TEXT).getText();

        System.out.println("Duplicate selected comment");
        testHelper.getElement(MainPg.NAV_BUTTON, "Duplicate...").click();

        System.out.println("In opened popup delete number (for automatic generation");
        testHelper.getElement(EditPg.NUMBER_TEXT_FIELD).clear();

        System.out.println("Click 'Save and Return' button");
        testHelper.getElement(EditPg.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that copied comment exist");
        assertTrue("Comment is not copied",
                testHelper.verifyThatCommentIsPresent(new Comment(null, "Copy of" + sourceCommentText, null, null)));
    }

    // task3 - changing comment
    @Test
    public void changeCommentTest() {
        final String newCommentText = "New Name For Comment";

        System.out.println("Select first comment");
        testHelper.getElement(TblSel.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Edit selected comment - open popup");
        testHelper.getElement(MainPg.NAV_BUTTON, "Edit...").click();

        System.out.println("Change text for comment");
        WebElement commentField = testHelper.getElement(EditPg.COMMENT_TEXT_FIELD);
        commentField.clear();
        commentField.sendKeys(newCommentText);

        System.out.println("Click 'Save and Return' button");
        testHelper.getElement(EditPg.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that copied comment exist");
        assertTrue("Comment is not edited",
                testHelper.verifyThatCommentIsPresent(new Comment(null, newCommentText, null, null)));
    }

    // task 4 - deleting comment
    @Test
    public void deleteCommentTest() {
        System.out.println("Select first comment");
        testHelper.getElement(TblSel.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Get first comment text");
        String commentText = testHelper.getElement(TblSel.FIRST_COMMENT_TEXT).getText();

        System.out.println("Delete selected comment");
        testHelper.getElement(MainPg.NAV_BUTTON, "Delete").click();

        System.out.println("Approve deletion operation");
        testHelper.getElement(EditPg.DELETE_POPUP_YES_BUTTON).click();

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
        testHelper.getElement(MainPg.NAV_BUTTON, "New...").click();

        System.out.println("Fill in comment name - quertyytrewq");
        testHelper.getElement(EditPg.COMMENT_TEXT_FIELD).sendKeys("quertyytrewq");

        System.out.println("Fill in comment name - " + uniqueExistNumber);
        testHelper.getElement(EditPg.NUMBER_TEXT_FIELD).sendKeys(String.valueOf(uniqueExistNumber));

        System.out.println("Select Cat0 category");
        testHelper.getElement(EditPg.CAT_ZERO_CHECKBOX).click();

        System.out.println("Add it to current comment");
        testHelper.getElement(EditPg.ADD_SELECTED_CATEGORY_BUTTON).click();

        System.out.println("Click Save and Return");
        testHelper.getElement(EditPg.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that error message appears");
        assertTrue("Error message doesn't appear or it is wrong",
                testHelper.verifyThatErrorMessageNumberFieldAppears(expectedErrorMessage));
    }

    // task 10.1 activation check
    @Test
    public void verifyCommentActivationTest() {
        System.out.println("Select first comment that is deactivated");
        Comment testComment = testHelper.getFirstCommentMatchTemplate(new Comment(null, null, false, null));
        testComment.checkbox().click();

        System.out.println("Edit selected comment - open popup");
        testHelper.getElement(MainPg.NAV_BUTTON, "Edit...").click();

        System.out.println("Activate comment");
        testHelper.getElement(EditPg.STATUS_FIELD).click();

        System.out.println("Click 'Save and Return' button");
        testHelper.getElement(EditPg.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that comment is changed");
        assertTrue("Comment is not changed",
                testHelper.verifyThatCommentIsPresent(new Comment(0, "Comment Text 0", true, "Cat0")));
    }

    // task 10.2 deactivation check
    @Test
    public void verifyCommentDeactivationTest() {
        System.out.println("Select first comment that is activated");
        Comment testComment = testHelper.getFirstCommentMatchTemplate(new Comment(null, null, true, null));
        testComment.checkbox().click();

        System.out.println("Edit selected comment - open popup");
        testHelper.getElement(MainPg.NAV_BUTTON, "Edit...").click();

        System.out.println("Deactivate comment");
        testHelper.getElement(EditPg.STATUS_FIELD).click();

        System.out.println("Click 'Save and Return' button");
        testHelper.getElement(EditPg.SAVE_RETURN_BUTTON).click();

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
