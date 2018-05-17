package spacecolonies;

import java.util.Arrays;
import queue.EmptyQueueException;
import student.TestCase;

/**
 * The ColonyReader parses the input data from two text files. It generates the
 * planets and queue of applicants based on one file of comma separated values
 * about applicants and the other about each planet. Then it gives SpaceWindow
 * this queue in order to tie everything together.
 * 
 * @author Zhiyuan Li
 * @version 2018.04.13
 *
 */
public class ArrayQueueTest extends TestCase {
    private ArrayQueue<String> queue;
    
    /**
     * set up 
     */
    public void setUp() {
        queue = new ArrayQueue<>();
        
    }
    /**
     * test get lenth
     */
    public void testGetLength() {
        assertEquals(11, queue.getLength());
        for (int i = 0; i < 10; i++) {
            queue.enqueue("test");
        }
        queue.enqueue("test");
        assertEquals(21, queue.getLength());
    }
    
    /**
     * test get size
     */
    public void testGetSize() {
        assertEquals(0, queue.getSize());
        queue.enqueue("first");
        assertEquals(1, queue.getSize());
        queue.dequeue();
        assertEquals(0, queue.getSize());
    }
    
    /**
     * test clear
     */
    public void testClear() {
        assertEquals(0, queue.getSize());
        queue.enqueue("first");
        assertEquals(1, queue.getSize());
        queue.clear();
        assertEquals(0, queue.getSize());
    }
    
    /**
     * test dequeue
     */
    public void testDequeue() {
        Exception exception = null;
        try {
            queue.dequeue();
        }
        catch (EmptyQueueException e) {
            exception = e;
        }
        assertTrue(exception instanceof EmptyQueueException);
        queue.enqueue("first");
        assertEquals(1, queue.getSize());
        String temp = queue.dequeue();
        assertEquals("first", temp);
        assertEquals(0, queue.getSize());
    }
    
    /**
     * test enqueue
     */
    public void testEnqueue() {
        queue.enqueue("first");
        assertEquals(1, queue.getSize());
        String temp = queue.dequeue();
        assertEquals("first", temp);
    }
    
    /**
     * test get front 
     */
    public void testGetFront() {
        Exception exception = null;
        try {
            queue.getFront();
        }
        catch (EmptyQueueException e) {
            exception = e;
        }
        assertTrue(exception instanceof EmptyQueueException);
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        queue.enqueue("last");
        assertEquals("first", queue.getFront());
        queue.dequeue();
        assertEquals("second", queue.getFront());
    }
    
    /**
     * test capacity 
     */
    public void testEnsureCapacity() {
        Exception exception = null;
        try {
            for (int i = 0; i < 500; i++) {
                queue.enqueue("test");
            }
        }
        catch (IllegalStateException e) {
            exception = e;
        }
        assertTrue(exception instanceof IllegalStateException);
        queue.clear();
        assertEquals(11, queue.getLength());
        assertEquals(0, queue.getSize());
        for (int i = 0; i < 11; i++) {
            queue.enqueue("test");
        }
        assertEquals(21, queue.getLength());
        assertEquals(11, queue.getSize());
    }
    
    /**
     * test empty
     */
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        for (int i = 0; i < 11; i++) {
            queue.enqueue("test");
        }
        assertFalse(queue.isEmpty());
    }
    
    /**
     * test to array
     */
    public void testToArray() {
        Exception exception = null;
        try {
            queue.toArray();
        }
        catch (EmptyQueueException e) {
            exception = e;
        }
        assertTrue(exception instanceof EmptyQueueException);
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        queue.enqueue("last");
        String[] temp = { "first", "second", "third", "last" };
        assertEquals(Arrays.toString(temp), Arrays.toString(queue.toArray()));
        assertEquals(queue.getLength(), 11);
    }
    
    /**
     * test to string
     */
    public void testToString() {
        assertEquals("[]", queue.toString());
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        queue.enqueue("last");
        assertEquals("[first, second, third, last]", queue.toString());
        queue.dequeue();
        assertEquals("[second, third, last]", queue.toString());
        queue.enqueue("first");
        assertEquals("[second, third, last, first]", queue.toString());
        queue.dequeue();
        assertEquals("[third, last, first]", queue.toString());
    }
    
    /**
     * test equals
     */
    public void testEquals() {
        ArrayQueue<String> nullQueue = null;
        assertFalse(queue.equals(nullQueue));
        assertTrue(queue.equals(queue));
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        queue.enqueue("last");
        ArrayQueue<String> testQ = new ArrayQueue<String>();
        testQ.enqueue("first");
        testQ.enqueue("second");
        testQ.enqueue("third");
        testQ.enqueue("last");
        assertTrue(queue.equals(testQ));
        testQ.dequeue();
        testQ.dequeue();
        assertFalse(queue.equals(testQ));
//        testQ.enqueue("first");
        testQ.enqueue("ala");
        testQ.enqueue("wow");
//        testQ.enqueue("last");
        assertFalse(queue.equals(testQ));
        String testStr = "";
        assertFalse(queue.equals(testStr));
    }
}
