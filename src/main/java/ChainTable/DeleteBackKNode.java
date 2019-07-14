package main.java.ChainTable;

/**
 * 题目：在单链表中删除倒数第k个节点
 *
 * @author wangjj17@lenovo.com
 * @date 2019/7/14
 */
public class DeleteBackKNode {

    public Node deleteBackK(Node head, int k) {
        if (k <= 0 || head == null) {
            return head;
        }
        //是否删除的是head节点标志位
        boolean flag = false;
        Node p = head;
        for (int i = 0; i < k; i++) {
            if (null != p.next) {
                p = p.next;
            } else {
                //判断是否是删除的head节点，当p指向最后一个节点时
                if (i + 1 == k) {
                    flag = true;
                    break;
                } else {//k大于链表长度时，直接返回
                    return head;
                }
            }
        }
        //通过p查找q得到倒数第k个节点的位置
        Node q = head;
        while (null != p.next) {
            p = p.next;
            q = q.next;
        }
        //删除head节点时
        if (flag) {
            head = head.next;
        } else {
            q.next = q.next.next;
        }
        return head;
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

        DeleteBackKNode d = new DeleteBackKNode();
//        Node result = d.deleteBackK(head, 8);
        Node result = d.deleteBackK(head, 7);
        chain.chainPrint(result);
    }
}