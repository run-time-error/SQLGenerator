package suits;

import ddl.TableCreatorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import utitlity.PairCheckerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TableCreatorTest.class,
        PairCheckerTest.class
})
public class TestSuit {

}
