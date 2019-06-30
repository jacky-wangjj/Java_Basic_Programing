package main.java.Interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 题目：实现最高频率出栈，比如依次放入423245。出栈按4253324出栈
 * FreqStack 有两个函数：
 * push(int x)，将整数 x 推入栈中。
 * pop()，它移除并返回栈中出现最频繁的元素。
 * 如果最频繁的元素不只一个，则移除并返回最接近栈顶的元素。
 *
 * @author wangjj17@lenovo.com
 * @date 2019/6/29
 */
public class FreqStack {
    private int maxFreq;//存储出现元素最大的次数
    private Map<Integer, Integer> numFreq;//保留list中各个元素出现的次数<元素, 次数>
    private Map<Integer, Stack<Integer>> group;//group[i]存储出现次数为i的所有元素，存储在stack中<次数, 存储元素的栈>

    public FreqStack() {
        numFreq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;//初始化出现元素最大的次数
    }

    public void push(int x) {
        if (null == numFreq.get(x)) {
            numFreq.put(x, 0);//初始化x元素的次数为0
        }
        int t = numFreq.get(x) + 1;
        numFreq.put(x, t);//更新x出现的次数
        if (t > maxFreq) {
            maxFreq = t;//更新出现元素最大的次数
        }
        Stack s = group.get(numFreq.get(x));
        if (null == s) {
            Stack newStack = new Stack();//创建新栈
            group.put(numFreq.get(x), newStack);//初始化对应次数的存储栈
        }
        group.get(numFreq.get(x)).push(x);//将x放入出现次数为numFreq[x]的栈中
    }

    public int pop() {
        int x = group.get(maxFreq).pop();//获取出现次数最多的栈的栈顶元素
        numFreq.put(x, numFreq.get(x) - 1);//更新出栈元素x的次数
        if (0 == group.get(maxFreq).size()) {//如果出现次数为maxFreq的元素已经没有，则减小maxFreq
            --maxFreq;
        }
        return x;
    }

    /**
     * 结果：依次放入423245。出栈按4253324出栈
     *
     * @param args
     */
    public static void main(String[] args) {
        FreqStack f = new FreqStack();
        f.push(4);
        f.push(2);
        f.push(3);
        f.push(2);
        f.push(4);
        f.push(5);
        System.out.println(f.pop());
        System.out.println(f.pop());
        System.out.println(f.pop());
        System.out.println(f.pop());
        System.out.println(f.pop());
        System.out.println(f.pop());
    }
}