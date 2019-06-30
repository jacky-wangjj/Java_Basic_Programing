package main.java.BinaryTree;

/**
 * @author wangjj17@lenovo.com
 * @date 2019/6/30
 */
public interface Tree {
    /**
     * 查找节点
     *
     * @param data
     * @return
     */
    public Node find(int data);

    /**
     * 插入新节点
     *
     * @param data
     * @return
     */
    public boolean insert(int data);

    /**
     * 删除节点
     *
     * @param data
     * @return
     */
    public boolean delete(int data);

    /**
     * 中序遍历
     *
     * @param current
     */
    public void infixOrder(Node current);

    /**
     * 前序遍历
     *
     * @param current
     */
    public void preOrder(Node current);

    /**
     * 后序遍历
     *
     * @param current
     */
    public void postOrder(Node current);

    /**
     * 查找最大值
     *
     * @return
     */
    public Node findMax();

    /**
     * 查找最小值
     *
     * @return
     */
    public Node findMin();

}