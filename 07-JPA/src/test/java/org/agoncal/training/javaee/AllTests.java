package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookTest.class,
        CDTest.class,
        ChapterTest.class,
        ItemTest.class,
        TrackTest.class
})
public class AllTests {
}
