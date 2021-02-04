import org.junit.Assert;
import org.junit.Test;

public class EqualStringsTest {



    @Test
    public void checkWithStarShouldWorkFine(){
        Assert.assertTrue(Main.checkWithStar("a", "a"));
        Assert.assertTrue(Main.checkWithStar("a", "a*"));
        Assert.assertTrue(Main.checkWithStar("a", "*****"));
        Assert.assertTrue(Main.checkWithStar("aaaaa", "aa*"));
    }
}
