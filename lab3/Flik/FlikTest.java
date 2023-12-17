import static org.junit.Assert.*;

import org.junit.Test;
public class FlikTest {
    @Test
    public void testFilk(){
        assertTrue(Flik.isSameNumber(0,0));
        assertTrue(Flik.isSameNumber(500,500));
        assertTrue(Flik.isSameNumber(128,128));
        assertFalse(Flik.isSameNumber(500,128));
    }
}
