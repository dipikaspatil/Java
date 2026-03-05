import java.util.*;

// LC83_RemoveDuplicatesFromSortedList.java
// Problem: Remove Duplicates from Sorted List
// URL: https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/?envType=problem-list-v2&envId=dsa-association-slope-linked-list
// Time Complexity: O(n) - we traverse the list once, where n is the number of nodes in the list.
// Space Complexity: O(1) - we are modifying the list in place and not using any extra space that grows with input size.


public class LC83_RemoveDuplicatesFromSortedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode n = head;

        while(n != null) {
            if(n.next != null && n.val == n.next.val) {
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }

        return head;
    }

    // helper to build list from array
    public static ListNode buildList(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    // helper to print list
    public static void printList(ListNode head) {
        List<Integer> vals = new ArrayList<>();
        while (head != null) {
            vals.add(head.val);
            head = head.next;
        }
        System.out.println(vals);
    }

    public static void main(String[] args) {
        LC83_RemoveDuplicatesFromSortedList solver = new LC83_RemoveDuplicatesFromSortedList();

        // Example 1: Input: [1,1,2] -> Output: [1,2]
        ListNode ex1 = buildList(new int[]{1,1,2});
        ListNode res1 = solver.deleteDuplicates(ex1);
        printList(res1);

        // Example 2: Input: [1,1,2,3,3] -> Output: [1,2,3]
        ListNode ex2 = buildList(new int[]{1,1,2,3,3});
        ListNode res2 = solver.deleteDuplicates(ex2);
        printList(res2);
    }
}