package com.wenhao.algorithm.link;

public class ListNode {

    public int data;

    public ListNode next;

    public ListNode(int data) {
        this.data = data;
        this.next = null;
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
        ListNode reverse = reverse(head);
        current = reverse;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }
}
