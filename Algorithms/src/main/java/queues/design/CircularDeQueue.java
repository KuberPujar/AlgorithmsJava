package queues.design;
/*
Design your implementation of the circular double-ended queue (deque).

Implement the MyCircularDeque class:

MyCircularDeque(int k) Initializes the deque with a maximum size of k.
boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
boolean isEmpty() Returns true if the deque is empty, or false otherwise.
boolean isFull() Returns true if the deque is full, or false otherwise.


Example 1:

Input
["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
Output
[null, true, true, true, false, 2, true, true, true, 4]

Explanation
MyCircularDeque myCircularDeque = new MyCircularDeque(3);
myCircularDeque.insertLast(1);  // return True
myCircularDeque.insertLast(2);  // return True
myCircularDeque.insertFront(3); // return True
myCircularDeque.insertFront(4); // return False, the queue is full.
myCircularDeque.getRear();      // return 2
myCircularDeque.isFull();       // return True
myCircularDeque.deleteLast();   // return True
myCircularDeque.insertFront(4); // return True
myCircularDeque.getFront();     // return 4


Constraints:

1 <= k <= 1000
0 <= value <= 1000
At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.
 */
public class CircularDeQueue {
    private int[] deque;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularDeQueue(int k) {
        this.capacity = k;
        this.deque = new int[k];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }

        if (size == 0) {
            deque[front] = value;
        } else {
            front = (front - 1 + capacity) % capacity;
            deque[front] = value;
        }
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }

        if (size == 0) {
            deque[rear] = value;
        } else {
            rear = (rear + 1) % capacity;
            deque[rear] = value;
        }
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }

        if (size == 1) {
            // Reset pointers when deque becomes empty
            front = 0;
            rear = 0;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        if (size == 1) {
            // Reset pointers when deque becomes empty
            front = 0;
            rear = 0;
        } else {
            rear = (rear - 1 + capacity) % capacity;
        }
        size--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return deque[front];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return deque[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        // Test case from the example
        CircularDeQueue myCircularDeque = new CircularDeQueue(3);

        System.out.println("insertLast(1): " + myCircularDeque.insertLast(1));   // true
        System.out.println("insertLast(2): " + myCircularDeque.insertLast(2));   // true
        System.out.println("insertFront(3): " + myCircularDeque.insertFront(3)); // true
        System.out.println("insertFront(4): " + myCircularDeque.insertFront(4)); // false
        System.out.println("getRear(): " + myCircularDeque.getRear());           // 2
        System.out.println("isFull(): " + myCircularDeque.isFull());             // true
        System.out.println("deleteLast(): " + myCircularDeque.deleteLast());     // true
        System.out.println("insertFront(4): " + myCircularDeque.insertFront(4)); // true
        System.out.println("getFront(): " + myCircularDeque.getFront());         // 4

        // Additional comprehensive tests
        System.out.println("\nAdditional tests:");
        System.out.println("Current state - Front: " + myCircularDeque.getFront() +
                ", Rear: " + myCircularDeque.getRear()); // Front: 4, Rear: 1

        System.out.println("deleteFront(): " + myCircularDeque.deleteFront());   // true
        System.out.println("getFront(): " + myCircularDeque.getFront());         // 3
        System.out.println("getRear(): " + myCircularDeque.getRear());           // 1

        System.out.println("deleteLast(): " + myCircularDeque.deleteLast());     // true
        System.out.println("getFront(): " + myCircularDeque.getFront());         // 3
        System.out.println("getRear(): " + myCircularDeque.getRear());           // 3

        System.out.println("deleteFront(): " + myCircularDeque.deleteFront());   // true
        System.out.println("isEmpty(): " + myCircularDeque.isEmpty());           // true
        System.out.println("getFront(): " + myCircularDeque.getFront());         // -1
        System.out.println("getRear(): " + myCircularDeque.getRear());           // -1

        // Test edge cases
        System.out.println("\nEdge case tests:");
        System.out.println("deleteFront() on empty: " + myCircularDeque.deleteFront()); // false
        System.out.println("deleteLast() on empty: " + myCircularDeque.deleteLast());   // false

        // Test alternating insertions
        System.out.println("insertFront(10): " + myCircularDeque.insertFront(10)); // true
        System.out.println("insertLast(20): " + myCircularDeque.insertLast(20));   // true
        System.out.println("insertFront(5): " + myCircularDeque.insertFront(5));   // true
        System.out.println("isFull(): " + myCircularDeque.isFull());               // true
        System.out.println("Final state - Front: " + myCircularDeque.getFront() +
                ", Rear: " + myCircularDeque.getRear()); // Front: 5, Rear: 20
    }
}
