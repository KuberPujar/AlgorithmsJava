package linkedlist;

import java.util.HashMap;
import java.util.Map;

/*
LRU cache
Design a data structure that works like a LRU Cache. Here cap denotes the capacity of the cache and Q denotes the number of queries. Query can be of two types:

SET x y: sets the value of the key x with value y.
GET x: gets the key of x if present else returns -1.

The LRUCache class has two methods get() and set() which are defined as follows.

get(key): returns the value of the key if it already exists in the cache otherwise returns -1. set(key, value): if the key is already present, update its value. If not present, add the key-value pair to the cache. If the cache reaches its capacity it should invalidate the least recently used item before inserting the new item.
In the constructor of the class the capacity of the cache should be initialized.

Example 1:

Input:

cap = 2
Q = 2
Queries = SET 1 2 GET 1
Output:
2
Explanation:

Cache Size = 2

SET 1 2 : Cache becomes {1=2}. No eviction needed.

GET 1 : Print the value corresponding to Key 1, i.e., 2.

Note: The function should return the result.
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.set(1, 2);
        System.out.println(cache.get(1)); // Output: 2
        cache.set(2, 3);
        System.out.println(cache.get(2)); // Output: 3
        cache.set(3, 4); // Evicts key 1
        System.out.println(cache.get(1)); // Output: -1
    }

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Map<Integer, Node> cache;
    private final int capacity;
    private final Node head;
    private final Node tail;

    public LRUCache(int cap) {
        this.capacity = cap;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            addToFront(node);
            return node.value;
        }
        return -1;
    }

    public void set(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            remove(node);
            addToFront(node);
        } else {
            if (cache.size() == capacity) {
                Node toRemove = tail.prev;
                remove(toRemove);
                cache.remove(toRemove.key);
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToFront(newNode);
        }
    }

    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
