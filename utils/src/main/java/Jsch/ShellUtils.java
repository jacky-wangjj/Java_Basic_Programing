package Jsch;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/8/30.
 */
public class ShellUtils {
    private static final Logger log = Logger.getLogger(ShellUtils.class);

    public static class ShellOut {
        private String stdMsg = "";
        private String errMsg = "";
        private int retCode;

        public ShellOut() {
        }

        public ShellOut(int retCode, String stdMsg, String errMsg) {
            this.retCode = retCode;
            this.stdMsg = stdMsg;
            this.errMsg = errMsg;
        }

        public String getStdMsg() {
            return stdMsg;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public int getRetCode() {
            return retCode;
        }

        public void setStdMsg(String stdMsg) {
            this.stdMsg = stdMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public void setRetCode(int retCode) {
            this.retCode = retCode;
        }

        @Override
        public String toString() {
            return "ShellOut{" +
                    "stdMsg='" + stdMsg + '\'' +
                    ", errMsg='" + errMsg + '\'' +
                    ", retCode=" + retCode +
                    '}';
        }
    }

    private static JSch jsch = new JSch();
    private static Map<String, Session> sessionCache = new ConcurrentHashMap<String, Session>();
    private static Map<String, Long> sessionAge = new ConcurrentHashMap<String, Long>();
    private static ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(1);

    static {
        ses.schedule(new Runnable() {
            @Override
            public void run() {
                expireSession();
            }
        }, 10, TimeUnit.SECONDS);
    }

    private static synchronized void expireSession() {
        Iterator<Map.Entry<String, Session>> itr = sessionCache.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Session> entry = itr.next();
            String sessionKey = entry.getKey();
            Session session = entry.getValue();
            synchronized (session) {
                Long age = sessionAge.get(sessionKey);
                if (age == null) {
                    age = 1l;
                } else {
                    age = age.longValue() + 1;
                }
                if (age > 3) {
                    session.disconnect();
                    itr.remove();
                    sessionAge.remove(sessionKey);
                } else {
                    sessionAge.put(sessionKey, age);
                }
            }
        }
    }

    private synchronized static Session getSession(String host, String user, String psw, int port) throws Exception {
        String key = host + user + port;
        Session cached = sessionCache.get(key);
        if (cached != null && cached.isConnected()) {
            return cached;
        }

        try {
            Session session = jsch.getSession(user, host, port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(psw);
            session.connect();
            sessionCache.put(key, session);
            sessionAge.put(key, 0l);
            return session;
        } catch (Exception e) {
            log.error("Create ssh session connection failed, host:" + host + ", user:" + user + ", port:" + port, e);
            throw e;
        }
    }

    public static ShellOut exec(String host, String user, String psw, int port, String command) {
        long time0 = System.currentTimeMillis();
        if (log.isDebugEnabled()) {
            log.debug("---exec-start: t-" + time0 + "   command:" + command);
        }
        StringBuilder result = new StringBuilder();
        Session session = null;
        ChannelExec channel = null;
        String errOutput = "";
        String stdOutput = "";
        int code = -1;
        try {
            session = getSession(host, user, psw, port);
            if (session != null) {
                if (log.isDebugEnabled()) {
                    log.debug("---exec-getSession: hc-" + session.hashCode() + ",host-" + host);
                }
                synchronized (session) {
                    channel = (ChannelExec) session.openChannel("exec");
                    channel.setCommand(command);
                    channel.setInputStream(null);
                    channel.setPty(true);

                    InputStream stdout = channel.getInputStream();
                    InputStream stderr = channel.getErrStream();
                    channel.connect();
                    waitForChannelClosed(channel, 60000);

                    code = channel.getExitStatus();
                    if (code != 0) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stderr));
                        errOutput = readerToString(bufferedReader);
                    } else {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout));
                        stdOutput = readerToString(bufferedReader);
                    }
                }
            }
        } catch (JSchException | IOException e) {
            errOutput += e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null && !channel.isClosed()) {
                channel.disconnect();
            }
        }
        if (log.isDebugEnabled()) {
            long time1 = System.currentTimeMillis();
            log.debug("---exec-end: t-" + time1 + " delta:" + (time1 - time0));
        }
        return new ShellOut(code, stdOutput, errOutput);
    }

    public static void waitForChannelClosed(ChannelExec channel, int timeout) {
        long time = 0;
        while (!channel.isClosed()) {
            try {
                Thread.sleep(10);
                time += 10;
                if (time >= timeout) {
                    break;
                }
            } catch (InterruptedException e) {
                continue;
            }
        }
        if (!channel.isClosed()) {
            channel.disconnect();
        }
    }

    public static String readerToString(BufferedReader bufferedReader) {
        StringBuffer buffer = new StringBuffer();
        String line = " ";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void main(String args[]) {
        ShellOut exec = exec("node1.leap.com", "root", "123456", 22, "ls -l /etc/passwd");
        System.out.println(exec.toString());
    }
}
