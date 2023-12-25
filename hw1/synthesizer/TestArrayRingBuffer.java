package synthesizer;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }
    @Test
    public void testArray(){
        ArrayRingBuffer<Integer> L=new ArrayRingBuffer<>(3);
        L.enqueue(0);
        L.enqueue(1);
        L.enqueue(2);
        assertEquals((long)0,(long)L.dequeue());
        assertEquals((long)1,(long)L.peek());
    }
    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
