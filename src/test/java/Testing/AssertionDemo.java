package Testing;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertionDemo {
    @Test
    public void test1() {
        Assert.assertEquals(12, 12);
    }
    @Test
    public void test2() {
         Assert.assertNotEquals(12, 13, "Count mismatched");
    }
    @Test
    public void test3() {
         Assert.assertTrue(true);
    }
    @Test
    public void test4() {
        Assert.assertFalse(false);
    }
    @Test
    public void test5() {
        Assert.assertFalse(false);
    }
}
