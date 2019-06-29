package main.java.Interview;

import java.util.Arrays;

/**
 * 题目：
 * 二叉树返回第k小的数
 *
 * @author wangjj17@lenovo.com
 * @date 2019/6/29
 */
public class GetMinKFromBinaryTree {
    /**
     * 树节点
     */
    private static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private int[] result;
    int i = 0;

    public GetMinKFromBinaryTree(int[] r) {
        this.result = r;
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public void getTreeNode(TreeNode node) {
        if (null != node) {
            getTreeNode(node.left);
            result[i++] = node.value;
            getTreeNode(node.right);
        }
    }

    public int getMin(TreeNode root, int k) {
        getTreeNode(root);
        return result[k - 1];
    }

    /**
     * array: [1, 2, 3, 5, 6, 7, 8]
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2, n1, n3);
        TreeNode n6 = new TreeNode(6);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7, n6, n8);
        TreeNode n5 = new TreeNode(5, n2, n7);
        TreeNode root = n5;
        int[] array = new int[7];
        GetMinKFromBinaryTree g = new GetMinKFromBinaryTree(array);
        int k = 7;
        int n = g.getMin(root, k);
        System.out.println("array: " + Arrays.toString(array));
        System.out.println("第" + k + "小的数是：" + n);
    }
}