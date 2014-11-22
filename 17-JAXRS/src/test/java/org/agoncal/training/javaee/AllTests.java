package org.agoncal.training.javaee;

import org.agoncal.training.javaee.domain.BookTest;
import org.agoncal.training.javaee.domain.CDTest;
import org.agoncal.training.javaee.service.IsbnGeneratorTest;
import org.agoncal.training.javaee.service.ItemEJBTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ItemEJBTest.class,
        IsbnGeneratorTest.class,
        BookTest.class,
        CDTest.class
})
public class AllTests {
}
