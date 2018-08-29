package main.java.ChainTable;

public class Chain {
    /**
     * 链表结点Node
     */
    private static class Node {
        //存放数据
        public int data;
        //存放结点
        public Node next;
        //构造方法，构造时填充data
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;

    /**
     *
     * @param head
     */
    public void setHead(Node head) {
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
     * 单链表实现选择排序
     */
    public void selectSortNode() {
        if (length(head) < 2) {
            System.out.println("not need sort");
            return;
        }
        Node tmp = head;
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

    /**
     * 单链表实现插入排序
     */
    public void insertSortNode() {
        if (length(head) < 2) {
            System.out.println("not need sort");
            return;
        }
        Node order = new Node(-1);//辅助链表，有序链表head，虚拟节点
        order.next = head;//head为第一个有序节点
        Node orderLast = head;//有序链表的最后一个结点

        Node cur = head.next;//从第二个几点开始遍历排序
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
        head = order.next;
    }

    /**
     * 单链表实现快速排序
     */
    public void quickSortNode() {
        Node end = null;
        quickSortNode(head, end);
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
        Chain chain;
        chain = new Chain();
        chain.setHead(head);
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
        //chain.selectSortNode();
        //chain.insertSortNode();
        chain.quickSortNode();
        chain.chainPrint(chain.head);
    }
}