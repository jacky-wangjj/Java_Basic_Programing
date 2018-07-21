package main.java.ChainTable;

import main.java.ChainTable.ListNode;

public class SelectionSortList {
    public static ListNode list;
    public SelectionSortList(ListNode list) {
        this.list = list;
    }
    /**
     * 单链表实现选择排序
     */
    public void selectSortNode() {
        if (list.length(list.head) < 2) {
            System.out.println("not need sort");
            return;
        }
        Node tmp = list.head;
        while (tmp != null) {
            Node stmp = tmp.next;
            while (stmp != null) {
                if (tmp.data > stmp.data) {
                    int t = tmp.data;
                    tmp.data = stmp.data;
                    stmp.data = t;
                }
                stmp = stmp.next;
            }
            tmp = tmp.next;
        }
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
        SelectionSortList s = new SelectionSortList(chain);
        s.selectSortNode();
        chain.chainPrint(s.list.head);
    }
}