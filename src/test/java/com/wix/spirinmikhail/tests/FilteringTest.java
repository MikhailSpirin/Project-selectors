package com.wix.spirinmikhail.tests;

import com.wix.spirinmikhail.helpers.SelectorsDataBase;
import com.wix.spirinmikhail.helpers.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertTrue;

/**
 * Created by mikhails on 13.01.2016
 */

public class FilteringTest {

    private TestHelper testHelper;

    @Before
    public void before() {
        testHelper = new TestHelper();
        testHelper.getURL("http://comments.azurewebsites.net/");
    }

    //task 9.1 verifying filtering by category
    @Test
    public void verifyFilteringByCategoryTest() {
        final String categoryToFilter = "Cat3";

        System.out.println("Choose category " + categoryToFilter);
        WebElement categoryDropdownElement = testHelper.getElement(SelectorsDataBase.SelectorsKeys.DROPDOWN_CATEGORY);
        Select categorySelect = new Select(categoryDropdownElement);
        categorySelect.selectByVisibleText(categoryToFilter);

        System.out.println("Click 'Apply'");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.FILTERING_BUTTON).click();

        System.out.println("Verify that all comments are from - " + categoryToFilter);
        assertTrue("Some comment doesn't contain " + categoryToFilter,
                testHelper.verifyFilterBy(SelectorsDataBase.SelectorsKeys.COMMENT_CATEGORIES_IN_LINE, categoryToFilter));
    }

    //task 9.2 verifying filtering by status
    @Test
    public void verifyFilteringByStatusTest() {
        final String statusToFilter = "V";

        System.out.println("Choose status " + statusToFilter);
        WebElement statusDropdownElement = testHelper.getElement(SelectorsDataBase.SelectorsKeys.DROPDOWN_STATUS);
        Select statusSelect = new Select(statusDropdownElement);
        statusSelect.selectByVisibleText("Active");

        System.out.println("Click 'Apply'");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.FILTERING_BUTTON).click();

        System.out.println("Verify that all comments have status - " + statusToFilter);
        assertTrue("Some comment is not " + statusToFilter,
                testHelper.verifyFilterBy(SelectorsDataBase.SelectorsKeys.COMMENT_ACTIVE_IN_LINE, statusToFilter));

    }

    //task 9.3 verifying filtering by both
    @Test
    public void verifyFilteringByCategoryAndStatusTest() {
        final String categoryToFilter = "Cat1";
        final String statusToFilter = "V";

        System.out.println("Choose category " + categoryToFilter);
        WebElement categoryDropdownElement = testHelper.getElement(SelectorsDataBase.SelectorsKeys.DROPDOWN_CATEGORY);
        Select categorySelect = new Select(categoryDropdownElement);
        categorySelect.selectByVisibleText(categoryToFilter);

        System.out.println("Click 'Apply'");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.FILTERING_BUTTON).click();

        System.out.println("Choose status " + statusToFilter);
        WebElement statusDropdownElement = testHelper.getElement(SelectorsDataBase.SelectorsKeys.DROPDOWN_STATUS);
        Select statusSelect = new Select(statusDropdownElement);
        statusSelect.selectByVisibleText("Active");

        System.out.println("Click 'Apply'");
        testHelper.getElement(SelectorsDataBase.SelectorsKeys.FILTERING_BUTTON).click();

        System.out.println("Verify that all comments have status - " + statusToFilter + " and category - " + categoryToFilter);
        assertTrue("Some comment has not expected status or category",
                (testHelper.verifyFilterBy(SelectorsDataBase.SelectorsKeys.COMMENT_ACTIVE_IN_LINE, statusToFilter) &
                        testHelper.verifyFilterBy(SelectorsDataBase.SelectorsKeys.COMMENT_CATEGORIES_IN_LINE, categoryToFilter)));
    }

    @After
    public void after() {
        testHelper.quit();
        System.out.println("--------  Finished!  --------------------------------");
    }
}
