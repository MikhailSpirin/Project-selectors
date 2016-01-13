package com.wix.spirinmikhail.tests;

import com.wix.spirinmikhail.helpers.TestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static com.wix.spirinmikhail.helpers.SelectorsDataBase.MainPg;

/**
 * Created by mikhails on 13.01.2016
 */
public class OtherTest {

    private TestHelper testHelper;

    @Before
    public void before() {
        testHelper = new TestHelper();
        testHelper.getURL("http://comments.azurewebsites.net/");
    }

    //task 5 - refresh page via link on page and via browser
    @Test
    public void refreshPageTest() {
        System.out.println("Verify refresh via link on page");
        testHelper.getElement(MainPg.REFRESH_LINK).click();

        System.out.println("Verify refresh via driver");
        testHelper.getDriver().navigate().refresh();
    }

    // task 6 - checking main header's color
    @Test
    public void verifyHeaderColorTest() {
        final String expectedColor = "rgba(255, 255, 255, 1)";

        System.out.println("Get header color");
        String actualColor = testHelper.getElement(MainPg.MAIN_HEADER).getCssValue("color");

        System.out.println("Verify that color is " + expectedColor);
        Assert.assertEquals("Color is not as expected", expectedColor, actualColor);
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
        System.out.println("--------  Finished!  --------------------------------");
    }
}
