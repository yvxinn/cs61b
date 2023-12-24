import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
public class TestOffByN {
    static CharacterComparator offByOne = new OffByN(1);
    static CharacterComparator offByZero = new OffByN(0);
    static CharacterComparator offByFive = new OffByN(5);
    @Test
    public void testequalChars(){
        assertFalse(offByOne.equalChars('a','a'));
        assertFalse(offByOne.equalChars('a','c'));
        assertFalse(offByOne.equalChars('a','e'));
        assertFalse(offByOne.equalChars('z','a'));
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('b','a'));
        assertTrue(offByOne.equalChars('r','q'));

        assertTrue(offByZero.equalChars('a','a'));
        assertFalse(offByZero.equalChars('a','b'));

        assertTrue(offByFive.equalChars('a', 'f'));
        assertTrue(offByFive.equalChars('f', 'a'));
        assertFalse(offByFive.equalChars('f', 'h'));
    }
}
