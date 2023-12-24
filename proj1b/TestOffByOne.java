import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    // Your tests go here.
    @Test
    public void testequalChars(){
        assertFalse(offByOne.equalChars('a','a'));
        assertFalse(offByOne.equalChars('a','c'));
        assertFalse(offByOne.equalChars('a','e'));
        assertFalse(offByOne.equalChars('z','a'));
        assertFalse(offByOne.equalChars('A','b'));

        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('b','a'));
        assertTrue(offByOne.equalChars('r','q'));
        assertTrue(offByOne.equalChars('%','&'));
    }
}
