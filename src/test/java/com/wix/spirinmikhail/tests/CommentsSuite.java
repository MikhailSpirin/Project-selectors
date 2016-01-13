package com.wix.spirinmikhail.tests;

/**
 * Created by mikhails on 13.01.2016
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CommentsTest.class,
        FilteringTest.class,
        SortingTest.class,
        OtherTest.class,
})
public class CommentsSuite {

}
