package main.java.LogFile;

/**
 * Created by Administrator on 2018/9/11.
 */
public class AychWriter extends Thread {
    private String content;
    public AychWriter(String content){
        this.content=content;
    }
    @Override
    public void run(){
        System.out.println("开始执行run()");
        LogWriter logger = null;
        String fileName = "d:/logger.log";
        long startTime=System.currentTimeMillis();
        try {
            logger = LogWriter.getLogWriter(fileName);
            logger.log(this.content);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        long endTime=System.currentTimeMillis();
        System.out.println("总消耗时间："+(endTime-startTime));
    }

    public static void main(String[] args) {
        String content = "this is a test string in AychWriter main function";
        new AychWriter(content).start();
    }
}
