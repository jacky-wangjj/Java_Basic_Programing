package main.java.ChainTable;

import main.java.ChainTable.Node;

public class ListNode {
    public static Node head;

    public ListNode(Node head) {
        this.head = head;
    }

    /**
     * 计算链表的长度，返回值为0，说明head为null
     * @param head
     * @return
     */
    public int length(Node head) {
        int len = 0;
        Node tmp = head;
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }
        return len;
    }

    /**
     * 向链表head末尾添加结点node
     * @param node
     */
    public void addNode(Node node) {
        Node tmp = head;
        while(tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = node;
    }

    /**
     * 在链表head指定位置index后添加结点node
     * @param index
     * @param node
     */
    public void insertNodeByIndex(int index, Node node) {
        if (index<0 || index>length(head)) {
            System.out.println("index error");
            return;
        }
        if (index == 0) {//插入到head前
            node.next = head;
            head = node;
            return;
        }
        int len = 1;
        Node tmp = head;
        while (tmp != null) {
            if (index == len) {
                node.next = tmp.next;
                tmp.next = node;
                return;
            }
            tmp = tmp.next;
            len++;
        }
    }

    /**
     * 删除指定位置index的结点
     * @param index
     */
    public void delNodeByIndex(int index) {
        if (index<1 || index>length(head)) {
            System.out.println("index error");
            return;
        }
        if (index == 1) {
            head = head.next;
            return;
        }
        int len = 1;
        Node tmp = head;
        while (tmp != null) {
            len++;
            if (index == len) {
                tmp.next = tmp.next.next;
                return;
            }
            tmp = tmp.next;
        }
    }

    /**
     * 打印链表结点
     * @param head
     */
    public void chainPrint(Node head) {
        Node tmp = head;
        System.out.println("Chain Node:");
        while (tmp != null){
            System.out.printf(" N:%d", tmp.data);
            tmp = tmp.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Node head = new Node(9);
        ListNode chain = new ListNode(head);
        chain.addNode(new Node(8));
        chain.addNode(new Node(7));
        chain.addNode(new Node(1));
        chain.addNode(new Node(6));
        chain.addNode(new Node(2));
        chain.addNode(new Node(5));
        chain.addNode(new Node(3));
        chain.chainPrint(chain.head);
        chain.insertNodeByIndex(0, new Node(4));
        chain.chainPrint(chain.head);
        chain.delNodeByIndex(1);
        chain.chainPrint(chain.head);
    }
}