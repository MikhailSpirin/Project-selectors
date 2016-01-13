package com.wix.spirinmikhail.tests;

import com.wix.spirinmikhail.helpers.SelectorsDataBase;
import com.wix.spirinmikhail.helpers.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;
import static com.wix.spirinmikhail.helpers.SelectorsDataBase.TblSel;

/**
 * Created by mikhails on 13.01.2016
 */
public class SortingTest {

    private TestHelper testHelper;

    @Before
    public void before() {
        testHelper = new TestHelper();
        testHelper.getURL("http://comments.azurewebsites.net/");
    }

    //task 8.1 - sorting on number
    @Test
    public void verifyNumberSortByNumberTest() {
        System.out.println("Click on 'sort by number'");
        WebElement link = testHelper.getElement(SelectorsDataBase.TblSel.LINKS_FOR_SORTING, "NumberValue");
        link.click();

        System.out.println("If sorting is ascending, click again - to descending");
        link = testHelper.getElement(TblSel.LINKS_FOR_SORTING, "NumberValue");
        if(link.getAttribute("href").contains("ASC")) link.click();

        System.out.println("Verify that comments are sorted by number");
        assertTrue("Comments are not sorted by number",
                testHelper.verifyThatCommentsAreSortedByNumber());
    }

    //task 8.2 - sorting on comment text
    @Test
    public void verifyNumberSortByCommentTextTest() {
        System.out.println("Click on 'sort by comment text'");
        WebElement link = testHelper.getElement(TblSel.LINKS_FOR_SORTING, "Text");
        link.click();

        System.out.println("If sorting is ascending, click again - to descending");
        link = testHelper.getElement(TblSel.LINKS_FOR_SORTING, "Text");
        if(link.getAttribute("href").contains("ASC")) link.click();

        System.out.println("Verify that comments are sorted by comment text");
        assertTrue("Comments are not sorted by comment text",
                testHelper.verifyThatCommentsAreSortedByCommentText());
    }

    //task 8.3 - sorting on active
    @Test
    public void verifyNumberSortByActiveTest() {
        System.out.println("Click on sorting by active");
        WebElement link = testHelper.getElement(TblSel.LINKS_FOR_SORTING, "Active");
        link.click();

        System.out.println("If sorting is ascending, click again - to descending");
        link = testHelper.getElement(TblSel.LINKS_FOR_SORTING, "Active");
        if(link.getAttribute("href").contains("ASC")) link.click();

        System.out.println("Verify that comments are sorted by number");
        assertTrue("Comments are not sorted by active",
                testHelper.verifyThatCommentsAreSortedByActive());
    }

    @After
    public void after() {
        testHelper.quit();
        System.out.println("--------  Finished!  --------------------------------");
    }
}
