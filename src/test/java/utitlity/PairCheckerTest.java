package utitlity;

import entity.TestClass;
import entity.TestClass2;
import org.junit.Assert;
import org.junit.Test;

public class PairCheckerTest {
    @Test
    public void testPair () {
        Pair pair = Pair.of("Raihan", 56);
        Assert.assertEquals("Pair Test (first): ", pair.first, "Raihan");
        Assert.assertEquals("Pair Test (second): ", pair.second, 56);

        pair = Pair.of(11, 56);
        Assert.assertEquals("Pair Test (first): ", pair.first, 11);
        Assert.assertEquals("Pair Test (second): ", pair.second, 56);

        pair = Pair.of('a', TestClass.class);
        Assert.assertEquals("Pair Test (first): ", pair.first, 'a');
        Assert.assertEquals("Pair Test (second): ", pair.second, TestClass.class);

        pair = Pair.of(TestClass2.class, TestClass.class);
        Assert.assertEquals("Pair Test (first): ", pair.first, TestClass2.class);
        Assert.assertEquals("Pair Test (second): ", pair.second, TestClass.class);



    }
}
