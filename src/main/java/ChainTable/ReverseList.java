package main.java.ChainTable;

import main.java.ChainTable.ListNode;

public class ReverseList {
    public static ListNode list;

    public ReverseList(ListNode list) {
        this.list = list;
    }

    /**
     * 反转单向链表
     * @return
     */
    public Node reverseList() {
        Node tmp = list.head;
        Node pre = null;
        Node next = null;
        while (tmp != null) {
            next = tmp.next;
            tmp.next = pre;
            pre = tmp;
            tmp = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode chain = new ListNode(new Node(9));
        chain.addNode(new Node(8));
        chain.addNode(new Node(7));
        chain.addNode(new Node(1));
        chain.addNode(new Node(6));
        chain.addNode(new Node(2));
        chain.addNode(new Node(5));
        chain.addNode(new Node(3));
        chain.chainPrint(chain.head);
        ReverseList r = new ReverseList(chain);
        chain.head = r.reverseList();
        chain.chainPrint(chain.head);
    }
}