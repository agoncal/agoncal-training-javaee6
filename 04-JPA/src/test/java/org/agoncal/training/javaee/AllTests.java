package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.BookTest;
import org.agoncal.training.javaee.model.ChapterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookTest.class,
        ChapterTest.class
})
public class AllTests {
}
