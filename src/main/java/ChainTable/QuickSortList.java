package main.java.ChainTable;

import main.java.ChainTable.ListNode;
import main.java.SortAlgorithm.Quick;

public class QuickSortList {
    public static ListNode list;
    public QuickSortList(ListNode list) {
        this.list = list;
    }

    /**
     * 单链表实现快速排序
     */
    public void quickSortNode() {
        Node end = null;
        quickSortNode(list.head, end);
    }
    public void quickSortNode(Node begin, Node end) {
        if (begin != end) {
            Node part = partition(begin, end);
            quickSortNode(begin, part);
            quickSortNode(part.next, end);
        }
    }
    //两个指针p1和p2均往next方向移动，移动的过程中保持p1之前的data都小于待排定的data，
    //p1和p2之间的data都大于待排定的data，那么当p2走到末尾时交换p1和待排节点的data便完成一次切分。
    public Node partition(Node begin, Node end) {
        Node p1 = begin, p2 = begin.next;//p1存放待排节点，从p1下一个节点即p2开始遍历，查找小于p1的节点
        while (p2 != end) {
            if (p2.data < begin.data) {
                p1 = p1.next;//p1和p1之前的节点都小于待排节点begin.data
                int tmp = p1.data;//将p1.next和p2交换，小于待排节点增加一个节点
                p1.data = p2.data;
                p2.data = tmp;
            }
            p2 = p2.next;
        }
        if (p1 != begin) {//p1位最终位置，交换p1和begin
            int tmp = p1.data;
            p1.data = begin.data;
            begin.data = tmp;
        }
        return p1;
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
        QuickSortList s = new QuickSortList(chain);
        s.quickSortNode();
        chain.chainPrint(s.list.head);
    }
}