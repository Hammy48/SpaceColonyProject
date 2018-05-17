package spacecolonies;

import java.util.Arrays;
import queue.EmptyQueueException;
import queue.QueueInterface;

/**
 * This data structure implements QueueInterface with a circular array
 * implementation. It provides default queue behavior, such as enqueue, dequeue,
 * getFront, and isEmpty.
 * 
 * @author Zhiyuan Li
 * @version 2018.04.13
 * @param <T> generic type
 *
 */
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Zhiyuan Li (lzy9667)
public class ArrayQueue<T> implements QueueInterface<T> {
    // Fields
    private T[] queue;
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * max capacity of array
     */
    public static final int MAX_CAPACITY = 100;
    private int enqueueIndex;
    private int dequeueIndex;
    private int size;


    // Constrcutor

    /**
     * Initialize all variables
     * 
     */
    public ArrayQueue() {
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[])new Object[DEFAULT_CAPACITY + 1];
        queue = tempQueue;
        dequeueIndex = 0;
        enqueueIndex = queue.length - 1;
        size = 0;
    }


    /**
     * @return the current length of the queue
     */
    public int getLength() {
        return queue.length;
    }


    /**
     * @return the current size of the queue
     */
    public int getSize() {
        return size;
    }


    /**
     * 
     * @return true if the length reachs the max capacity
     */
    private boolean isFull() {
        return dequeueIndex == (enqueueIndex + 2) % queue.length;
    }


    /**
     * helper method to increase the target index
     * 
     * @param index
     *            to be increased
     * @return new index
     */
    private int incrementIndex(int index) {
        return (index + 1) % queue.length;
    }


    /**
     * Clear and reset the array
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        T[] tempQueue = (T[])new Object[DEFAULT_CAPACITY + 1];
        queue = tempQueue;
        dequeueIndex = 0;
        enqueueIndex = queue.length - 1;
        size = 0;
    }


    /**
     * remove the first entry in the array
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        else {
            T front = getFront();
            queue[dequeueIndex] = null;
            dequeueIndex = incrementIndex(dequeueIndex);
            size--;
            return front;
        }
    }


    /**
     * Adding to the back Double the size of the array if it is full place the
     * new
     * entry after the last occupied location in the array
     */
    @Override
    public void enqueue(T entry) {
        ensureCapacity();
        enqueueIndex = incrementIndex(enqueueIndex);
        queue[enqueueIndex] = entry;
        size++;
    }


    /**
     * retrieving the front entry
     */
    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        else {
            return queue[dequeueIndex];
        }
    }


    /**
     * This optional helper method can be used to upgrade the length of the
     * queue
     * when the queue is full. The new length is twice as large as the old size.
     * Note that size is different from length. So a queue of length 11 will be
     * full
     * when the size is 10, and after upgrading the length in ensureCapacity(),
     * the
     * new length will be 21 with the capacity to hold 20 elements.
     */
    private void ensureCapacity() {
        if (isFull()) {
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize - 1;
            if (newSize > MAX_CAPACITY) {
                throw new IllegalStateException();
            }
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[])new Object[newSize];
            queue = tempQueue;
            for (int i = 0; i < oldSize - 1; i++) {
                queue[i] = oldQueue[dequeueIndex];
                dequeueIndex = (dequeueIndex + 1) % oldSize;
            }
            dequeueIndex = 0;
            enqueueIndex = oldSize - 2;
        }
    }


    /**
     * @return true is the array is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 
     * @return an array that have its entries start at index 0, and as large as
     *         its
     *         size
     */
    @SuppressWarnings("unchecked")
    public Object[] toArray() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        else {
            int index = dequeueIndex;
            Object[] returnArray = (T[])new Object[size];
            for (int i = 0; i < returnArray.length; i++) {
                returnArray[i] = queue[index];
                index = incrementIndex(index);
            }
            return returnArray;
        }
    }


    /**
     * @return the string representation of the entries in the queue
     */
    public String toString() {
// StringBuilder builder = new StringBuilder("[");
// for (int i = 0; i < this.size; i++) {
// builder.append(queue[i].toString());
// if (i != this.size - 1) {
// builder.append(", ");
// }
// }
// builder.append("]");
// return builder.toString();
        if (isEmpty()) {
            return "[]";
        }
        String str = Arrays.toString(toArray());
        return str;
    }


    /**
     * @return true if the two queue contains same objects
     * @param obj compareing object
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (this == obj) {
            return true;
        }
        if (this.getClass() == obj.getClass()) {
            
            @SuppressWarnings("unchecked")
            ArrayQueue<T> tempQueue = (ArrayQueue<T>)obj;
            if (this.size == tempQueue.size) {
                for (int i = 0; i < this.size; i++) {
                    T myElement = this.queue[(this.dequeueIndex + i)
                        % this.queue.length];
                    T otherElement = tempQueue.queue[(tempQueue.dequeueIndex
                        + i) % tempQueue.queue.length];
                    if (!myElement.equals(otherElement)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

}
