import java.util.*;

// LC328_OddEvenLinkedList.java
// Problem: Odd Even Linked List
// URL: https://leetcode.com/problems/odd-even-linked-list/description/?envType=problem-list-v2&envId=dsa-association-slope-linked-list
//
// Time Complexity: O(n) - we traverse the list once, where n is the number of nodes in the list.
// Space Complexity: O(1) - we are modifying the list in place and not using any extra space that grows with input size.
// Complexity: Medium

public class LC328_OddEvenLinkedList {
    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode oddEvenList(ListNode head) {
        // corner case
        if(head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head != null ? head.next : null;
        ListNode evenHead = even;

        while(even != null &&  even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd != null ? odd.next : null;
            even = even.next;
        }

        odd.next = evenHead;
        
        return head;
    }

    // Helper to build list from array
    private static ListNode build(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    // Helper to print list
    private static void printList(ListNode head) {
        List<Integer> out = new ArrayList<>();
        while (head != null) {
            out.add(head.val);
            head = head.next;
        }
        System.out.println(out);
    }

    public static void main(String[] args) {
        // Example 1:
        ListNode ex1 = build(new int[]{1,2,3,4,5});
        ListNode res1 = oddEvenList(ex1);
        printList(res1); // expected [1,3,5,2,4]

        // Example 2:
        ListNode ex2 = build(new int[]{2,1,3,5,6,4,7});
        ListNode res2 = oddEvenList(ex2);
        printList(res2); // expected [2,3,6,7,1,5,4]
    }
}
