import java.util.*;

public class LC138_CopyListWithRandomPointer {
    // Problem: Copy List with Random Pointer
    // URL: https://leetcode.com/problems/copy-list-with-random-pointer/submissions/1941253539/?envType=problem-list-v2&envId=dsa-association-slope-hash
    //
    // Time Complexity: O(n) - we traverse the list twice, where n is the number of nodes in the list.
    // Space Complexity: O(n) - we use a hash map to store the mapping from original nodes to their copies, 
    // which can take up to O(n) space.
    //
    // Note: Implement solution in copyRandomList. Placeholder used below.

    static class Node {
        int val;
        Node next;
        Node random;
        Node(int val) { this.val = val; this.next = null; this.random = null; }
    }

    public Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Node temp = head;
        // Pass 1: create copy nodes
        while(temp != null) {
            Node newNode = new Node(temp.val);
            map.put(temp, newNode);
            temp = temp.next;
        }

        temp = head;
        // Pass 2: connect next and random
        while(temp != null) {
            map.get(temp).next = map.get(temp.next);
            map.get(temp).random = map.get(temp.random);
            temp = temp.next;
        }

        return map.get(head);
    }

    static void printList(Node head) {
        Node cur = head;
        List<String> parts = new ArrayList<>();
        Map<Node,Integer> idx = new HashMap<>();
        int i = 0;
        for (Node n = head; n != null; n = n.next) { idx.put(n, i++); }
        for (Node n = head; n != null; n = n.next) {
            String r = (n.random == null) ? "null" : String.valueOf(idx.get(n.random));
            parts.add("[" + n.val + "," + r + "]");
        }
        System.out.println(parts);
    }

    public static void main(String[] args) {
        // Example 1: [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node n1 = new Node(7);
        Node n2 = new Node(13);
        Node n3 = new Node(11);
        Node n4 = new Node(10);
        Node n5 = new Node(1);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5;
        n1.random = null;
        n2.random = n1;
        n3.random = n5;
        n4.random = n3;
        n5.random = n1;
        LC138_CopyListWithRandomPointer sol = new LC138_CopyListWithRandomPointer();
        System.out.print("Original: ");
        printList(n1); // expected [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node copy1 = sol.copyRandomList(n1);
        System.out.print("Copied  : ");
        printList(copy1); // expected [[7,null],[13,0],[11,4],[10,2],[1,0]]

        // Example 2: [[1,1],[2,1]]
        Node a1 = new Node(1);
        Node a2 = new Node(2);
        a1.next = a2;
        a1.random = a2;
        a2.random = a2;
        System.out.print("Original: ");
        printList(a1); // expected [[1,1],[2,1]]
        Node copy2 = sol.copyRandomList(a1);
        System.out.print("Copied  : ");
        printList(copy2);
    }
}