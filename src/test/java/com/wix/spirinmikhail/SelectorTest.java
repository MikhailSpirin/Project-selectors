package com.wix.spirinmikhail;


import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertTrue;

import static com.wix.spirinmikhail.SelectorsDataBase.SelectorsKeys;

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
        testHelper.getElement(SelectorsKeys.ACTION_BUTTON, "New...").click();

        System.out.println("Fill in comment name" + expectedCommentText);
        testHelper.getElement(SelectorsKeys.COMMENT_TEXT_FIELD).sendKeys(expectedCommentText);

        System.out.println("Select Cat0 category");
        testHelper.getElement(SelectorsKeys.CAT_ZERO_CHECKBOX).click();

        System.out.println("Add it to current comment");
        testHelper.getElement(SelectorsKeys.ADD_SELECTED_CATEGORY).click();

        System.out.println("Click Save and Return");
        testHelper.getElement(SelectorsKeys.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that comment " + expectedCommentText + " is exist");
        assertTrue("Comment is not added",
                testHelper.verifyThatCommentIsPresent(new Comment(null, expectedCommentText, null, null)));
    }

    @Test
    public void duplicateCommentTest() {

        System.out.println("Select first comment");
        testHelper.getElement(SelectorsKeys.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Save text of the first comment");
        String sourceCommentText = testHelper.getElement(SelectorsKeys.FIRST_COMMENT_TEXT).getText();

        System.out.println("Duplicate selected comment");
        testHelper.getElement(SelectorsKeys.ACTION_BUTTON, "Duplicate...").click();

        System.out.println("In opened popup delete number (for automatic generation");
        testHelper.getElement(SelectorsKeys.NUMBER_TEXT_FIELD).clear();

        System.out.println("Click 'Save and Return' button");
        testHelper.getElement(SelectorsKeys.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that copied comment exist");
        assertTrue("Comment is not copied",
                testHelper.verifyThatCommentIsPresent(new Comment(null, "Copy of" + sourceCommentText, null, null)));
    }

    @Test
    public void changeCommentTest() {
        final String newCommentText = "New Name For Comment";

        System.out.println("Select first comment");
        testHelper.getElement(SelectorsKeys.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Edit selected comment - open popup");
        testHelper.getElement(SelectorsKeys.ACTION_BUTTON, "Edit...").click();

        System.out.println("Change text for comment");
        WebElement commentField = testHelper.getElement(SelectorsKeys.COMMENT_TEXT_FIELD);
        commentField.clear();
        commentField.sendKeys(newCommentText);

        System.out.println("Click 'Save and Return' button");
        testHelper.getElement(SelectorsKeys.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that copied comment exist");
        assertTrue("Comment is not edited",
                testHelper.verifyThatCommentIsPresent(new Comment(null, newCommentText, null, null)));
    }

    @Test
    public void deleteCommentTest() {
        System.out.println("Select first comment");
        testHelper.getElement(SelectorsKeys.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Get first comment text");
        String commentText = testHelper.getElement(SelectorsKeys.FIRST_COMMENT_TEXT).getText();

        System.out.println("Delete selected comment");
        testHelper.getElement(SelectorsKeys.ACTION_BUTTON, "Delete").click();

        System.out.println("Approve deletion operation");
        testHelper.getElement(SelectorsKeys.DELETE_POPUP_YES_BUTTON).click();

        System.out.println("Verify that deleted comment is not exist");
        assertTrue("Comment is not deleted",
                !testHelper.verifyThatCommentIsPresent(new Comment(null, commentText, null, null)));
    }

    @Test
    public void refreshPageTest() {
        System.out.println("Verify refresh via link on page");
        testHelper.getElement(SelectorsKeys.REFRESH_LINK).click();

        System.out.println("Verify refresh via driver");
        testHelper.getDriver().navigate().refresh();
    }

    @Test
    public void verifyHeaderColorTest() {
        final String expectedColor = "rgba(255, 255, 255, 1)";

        System.out.println("Get header color");
        String actualColor = testHelper.getElement(SelectorsKeys.MAIN_HEADER).getCssValue("color");

        System.out.println("Verify that color is " + expectedColor);
        Assert.assertEquals("Color is not as expected", expectedColor, actualColor);
    }

    // Task 7 - Checking unique field "Number" while creating new comment
    @Test
    public void verifyNumberIsUniqueTest() {
        final Integer uniqueExistNumber = 1;
        final String expectedErrorMessage = "The Number field should contain value from 0 to 999 and should be unique";

        System.out.println("Create new comment");
        testHelper.getElement(SelectorsKeys.ACTION_BUTTON, "New...").click();

        System.out.println("Fill in comment name - quertyytrewq");
        testHelper.getElement(SelectorsKeys.COMMENT_TEXT_FIELD).sendKeys("quertyytrewq");

        System.out.println("Fill in comment name - " + uniqueExistNumber);
        testHelper.getElement(SelectorsKeys.NUMBER_TEXT_FIELD).sendKeys(String.valueOf(uniqueExistNumber));

        System.out.println("Select Cat0 category");
        testHelper.getElement(SelectorsKeys.CAT_ZERO_CHECKBOX).click();

        System.out.println("Add it to current comment");
        testHelper.getElement(SelectorsKeys.ADD_SELECTED_CATEGORY).click();

        System.out.println("Click Save and Return");
        testHelper.getElement(SelectorsKeys.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that error message appears");
        assertTrue("Error message doesn't appear or it is wrong",
                testHelper.verifyThatErrorMessageNumberFieldAppears(expectedErrorMessage));
    }

    //task 8.1 - sorting on number
    @Test
    public void verifyNumberSortByNumberTest() {
        System.out.println("Click on 'sort by number'");
        WebElement link = testHelper.getElement(SelectorsKeys.LINK_FOR_SORTING, "NumberValue");
        link.click();

        System.out.println("If sorting is ascending, click again - to descending");
        link = testHelper.getElement(SelectorsKeys.LINK_FOR_SORTING, "NumberValue");
        if(link.getAttribute("href").contains("ASC")) link.click();

        System.out.println("Verify that comments are sorted by number");
        assertTrue("Comments are not sorted by number",
                testHelper.verifyThatCommentsAreSortedByNumber());
    }

    //task 8.2 - sorting on comment text
    @Test
    public void verifyNumberSortByCommentTextTest() {
        System.out.println("Click on 'sort by comment text'");
        WebElement link = testHelper.getElement(SelectorsKeys.LINK_FOR_SORTING, "Text");
        link.click();

        System.out.println("If sorting is ascending, click again - to descending");
        link = testHelper.getElement(SelectorsKeys.LINK_FOR_SORTING, "Text");
        if(link.getAttribute("href").contains("ASC")) link.click();

        System.out.println("Verify that comments are sorted by comment text");
        assertTrue("Comments are not sorted by comment text",
                testHelper.verifyThatCommentsAreSortedByCommentText());
    }

    //task 8.3 - sorting on active
    @Test
    public void verifyNumberSortByActiveTest() {
        System.out.println("Click on sorting by active");
        WebElement link = testHelper.getElement(SelectorsKeys.LINK_FOR_SORTING, "Active");
        link.click();

        System.out.println("If sorting is ascending, click again - to descending");
        link = testHelper.getElement(SelectorsKeys.LINK_FOR_SORTING, "Active");
        if(link.getAttribute("href").contains("ASC")) link.click();

        System.out.println("Verify that comments are sorted by number");
        assertTrue("Comments are not sorted by active",
                testHelper.verifyThatCommentsAreSortedByActive());
    }

    //task 9.1 verifying filtering by category
    @Test
    public void verifyFilteringByCategoryTest() {
        final String categoryToFilter = "Cat3";

        System.out.println("Choose category " + categoryToFilter);
        WebElement categoryDropdownElement = testHelper.getElement(SelectorsKeys.DROPDOWN_CATEGORY);
        Select categorySelect = new Select(categoryDropdownElement);
        categorySelect.selectByVisibleText(categoryToFilter);

        System.out.println("Click 'Apply'");
        testHelper.getElement(SelectorsKeys.FILTERING_BUTTON).click();

        System.out.println("Verify that all comments are from " + categoryToFilter);
        assertTrue("Some comment doesn't contain " + categoryToFilter,
                testHelper.verifyFilterByCategory(categoryToFilter));
    }

    //task 9.2 verifying filtering by status
    @Test
    public void verifyFilteringByStatusTest() {
        final String statusToFilter = "V";

        System.out.println("Choose status " + statusToFilter);
        WebElement statusDropdownElement = testHelper.getElement(SelectorsKeys.DROPDOWN_STATUS);
        Select statusSelect = new Select(statusDropdownElement);
        statusSelect.selectByVisibleText("Active");

        System.out.println("Click 'Apply'");
        testHelper.getElement(SelectorsKeys.FILTERING_BUTTON).click();

        System.out.println("Verify that all comments have status" + statusToFilter);
        assertTrue("Some comment is not " + statusToFilter, testHelper.verifyFilterByStatus(statusToFilter));

    }

    //task 9.3 verifying filtering by both
    @Test
    public void verifyFilteringByCategoryAndStatusTest() {
        final String categoryToFilter = "Cat1";
        final String statusToFilter = "V";

        System.out.println("Choose category " + categoryToFilter);
        WebElement categoryDropdownElement = testHelper.getElement(SelectorsKeys.DROPDOWN_CATEGORY);
        Select categorySelect = new Select(categoryDropdownElement);
        categorySelect.selectByVisibleText(categoryToFilter);

        System.out.println("Click 'Apply'");
        testHelper.getElement(SelectorsKeys.FILTERING_BUTTON).click();

        System.out.println("Choose status " + statusToFilter);
        WebElement statusDropdownElement = testHelper.getElement(SelectorsKeys.DROPDOWN_STATUS);
        Select statusSelect = new Select(statusDropdownElement);
        statusSelect.selectByVisibleText("Active");

        System.out.println("Click 'Apply'");
        testHelper.getElement(SelectorsKeys.FILTERING_BUTTON).click();

        System.out.println("Verify that all comments have status " + statusToFilter + " and category " + categoryToFilter);
        assertTrue("Some comment has not expected status or category",
                (testHelper.verifyFilterByStatus(statusToFilter) & testHelper.verifyFilterByCategory(categoryToFilter)));
    }

    // task 10.1 activation check ---
    // very fast hardcoded check. Should be verified in another way -
    // getting random comment with status active and changing it
    // Deactivation check is similar
    @Test
    public void verifyCommentActivationTest() {
        System.out.println("Select first comment");
        testHelper.getElement(SelectorsKeys.FIRST_COMMENT_CHECKBOX).click();

        System.out.println("Edit selected comment - open popup");
        testHelper.getElement(SelectorsKeys.ACTION_BUTTON, "Edit...").click();

        System.out.println("Deactivate comment");
        testHelper.getElement(SelectorsKeys.STATUS_FIELD).click();

        System.out.println("Click 'Save and Return' button");
        testHelper.getElement(SelectorsKeys.SAVE_RETURN_BUTTON).click();

        System.out.println("Verify that comment is changed");
        assertTrue("Comment is not changed",
                testHelper.verifyThatCommentIsPresent(new Comment(0, "Comment Text 0", false, "Cat0")));
    }

    //task 11 weight of divider between filter and menu
    @Test
    public void verifyDividerWeightTest() {
        final String expectedWeight = "2px";

        System.out.println("Verify that divider weight is " + expectedWeight);
        assertTrue("Divider weight is different",
                testHelper.verifyDividerWeight(expectedWeight));

    }

    //task 12 checking that headers number,  comment text, active are underlined
    @Test
    public void verifyHeadersUnderlinedTest() {
        System.out.println("Verify that headers are underlined");
        assertTrue("Headers are not underlined",
                testHelper.verifyHeaderUnderlined());
    }



    @After
    public void after() {
        testHelper.quit();
        System.out.println("--------  Finished!  ------------------------------------");
    }
}
