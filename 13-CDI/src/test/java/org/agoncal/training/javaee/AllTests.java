package org.agoncal.training.javaee;

import org.agoncal.training.javaee.service.IsbnGeneratorTest;
import org.agoncal.training.javaee.service.ItemEJBTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ItemEJBTest.class,
        IsbnGeneratorTest.class
})
public class AllTests {
}
