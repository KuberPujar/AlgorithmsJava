package arrays;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class AlternatingSplitOfCircularLinkedList {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = head; // Making it circular

        Node[] result = alternatingSplit(head);
        printList(result[0]); // First half
        printList(result[1]); // Second half
    }

    public static Node[] alternatingSplit(Node head) {
        if (head == null || head.next == head) {
            return new Node[]{head, null};
        }

        Node head1 = null, head2 = null;
        Node tail1 = null, tail2 = null;
        Node current = head;
        boolean flag = true;

        do {
            if (flag) {
                if (head1 == null) {
                    head1 = tail1 = new Node(current.data);
                } else {
                    tail1.next = new Node(current.data);
                    tail1 = tail1.next;
                }
            } else {
                if (head2 == null) {
                    head2 = tail2 = new Node(current.data);
                } else {
                    tail2.next = new Node(current.data);
                    tail2 = tail2.next;
                }
            }
            flag = !flag;
            current = current.next;
        } while (current != head);

        tail1.next = head1; // Making first half circular
        tail2.next = head2; // Making second half circular

        return new Node[]{head1, head2};
    }

    public static void printList(Node head) {
        if (head == null) return;
        Node temp = head;
        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }
}