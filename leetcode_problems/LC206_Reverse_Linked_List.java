import java.util.*;

/*
Problem: Reverse Linked List
URL: https://leetcode.com/problems/reverse-linked-list/submissions/1938181967/?envType=problem-list-v2&envId=dsa-association-slope-linked-list

Time Complexity:
Space Complexity:
*/

public class LC206_Reverse_Linked_List {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // PASTE YOUR SOLUTION HERE
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode first = null;
        ListNode second = head;
        ListNode third = head.next;

        while(second != null && third != null) {
            second.next = first;
            first = second;
            second = third;
            third = third.next;
        }

        second.next = first;
        return second;
    }

    // Helper to build a linked list from array
    private static ListNode buildList(int[] vals) {
        if (vals == null || vals.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    // Helper to print linked list
    private static void printList(ListNode head) {
        List<Integer> vals = new ArrayList<>();
        while (head != null) {
            vals.add(head.val);
            head = head.next;
        }
        System.out.println(vals);
    }

    public static void main(String[] args) {
        // Example 1: head = [1,2,3,4,5] -> [5,4,3,2,1]
        ListNode t1 = buildList(new int[]{1,2,3,4,5});
        System.out.print("Original: ");
        printList(t1);
        ListNode r1 = reverseList(t1);
        System.out.print("Reversed: ");
        printList(r1);

        // Example 2: head = [1,2] -> [2,1]
        ListNode t2 = buildList(new int[]{1,2});
        System.out.print("Original: ");
        printList(t2);
        ListNode r2 = reverseList(t2);
        System.out.print("Reversed: ");
        printList(r2);

        // Example 3: head = [] -> []
        ListNode t3 = buildList(new int[]{});
        System.out.print("Original: ");
        printList(t3);
        ListNode r3 = reverseList(t3);
        System.out.print("Reversed: ");
        printList(r3);
    }
}