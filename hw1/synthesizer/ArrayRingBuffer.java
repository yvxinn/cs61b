package synthesizer;
import synthesizer.AbstractBoundedQueue;
import java.util.Iterator;
import java.util.Optional;

import org.junit.Test;
import static org.junit.Assert.*;
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;
    public ArrayRingBuffer(int capacity) {
        this.capacity=capacity;
        this.first=0;
        this.last=0;
        this.fillCount=0;
        rb= (T[]) new Object[this.capacity];
    }
    @Override
    public void enqueue(T x) {
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }else{
            rb[last]=x;
            fillCount++;
            last=(last+1)%capacity;
        }
    }
    @Override
    public T dequeue() {
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }else{
            T reVal=rb[first];
            this.fillCount--;
            first=(first+1)%capacity;
            return reVal;
        }
    }
    @Override
    public T peek() {
        if(isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }
    private class ArrayRingBufferIterator implements Iterator<T>{
        private int index;
        private int count;
        public ArrayRingBufferIterator(){
            count=0;
            index=first;
        }
        @Override
        public boolean hasNext() {
            return count<fillCount;
        }
        @Override
        public T next() {
            T retVal=rb[index];
            index=(index+1)%capacity;
            count++;
            return retVal;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
