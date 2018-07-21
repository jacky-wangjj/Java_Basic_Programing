package main.java.ChainTable;

import main.java.ChainTable.ListNode;

public class InsertionSortList {
    public static ListNode list;
    public InsertionSortList(ListNode list) {
        this.list = list;
    }
    /**
     * 单链表实现插入排序
     */
    public void insertSortNode() {
        if (list.length(list.head) < 2) {
            System.out.println("not need sort");
            return;
        }
        Node order = new Node(-1);//辅助链表，有序链表head，虚拟节点
        order.next = list.head;//head为第一个有序节点
        Node orderLast = list.head;//有序链表的最后一个结点

        Node cur = list.head.next;//从第二个几点开始遍历排序
        while (cur != null) {
            if (cur.data < orderLast.data) {
                Node nextNode = cur.next;//保存下一个需要排序的节点
                //寻找插入的位置
                Node curOrder = order.next;//取有序链表的第一个节点
                Node preNode = order;//记录curOrder的前序节点
                while (cur.data > curOrder.data && curOrder != orderLast) {
                    preNode = curOrder;//遍历有序链表查找插入位置
                    curOrder = curOrder.next;
                }
                //进行插入，插入到curOrder节点之前
                preNode.next = cur;
                cur.next = curOrder;
                //设置下一个需要排序的节点
                cur = nextNode;
            } else {
                orderLast = cur;
                cur = cur.next;
            }
        }
        orderLast.next = null;//设置辅助链表的最后一个节点指向null
        list.head = order.next;
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
        InsertionSortList s = new InsertionSortList(chain);
        s.insertSortNode();
        chain.chainPrint(s.list.head);
    }
}