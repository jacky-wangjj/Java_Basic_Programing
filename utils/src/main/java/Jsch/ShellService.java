package Jsch;

import SiteConfig.SiteConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2018/8/30.
 */
public class ShellService {
    private static final Logger log = Logger.getLogger(ShellService.class);
    private List<SSHChannel> sshChannels = new ArrayList<SSHChannel>();
    public ShellService() {
        String hosts = SiteConfig.get("ssh.useradd.hosts");
        String port = SiteConfig.get("ssh.useradd.port");
        String username = SiteConfig.get("ssh.useradd.un");
        String password = SiteConfig.get("ssh.useradd.pwd");
        for (String host : hosts.split(",")) {
            SSHChannel channel = SSHChannel.get(host, port, username, password);
            if (channel != null) {
                sshChannels.add(channel);
            }
        }
    }

    private void init() {
        //Ensure there is a main group for all leapid users
        parallelRun(sshChannel -> {
            sshChannel.init();
            return true;
        });
    }
    private static class T extends Thread {
        public boolean success = false;
    }

    private boolean parallelRun(final Function<SSHChannel, Boolean> consumer) {
        List<T> threads = new ArrayList<>();
        for (SSHChannel sshChannel : sshChannels) {
            T t = new T() {
                public void run() {
                    try {
                        success = consumer.apply(sshChannel);
                    } catch (Exception e) {
                        success = false;
                    }
                }
            };
            t.start();
            threads.add(t);
        }

        for (T t : threads) {
            try {
                t.join();
                if (!t.success) {
                    return false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public List<String> getUsers() throws Exception {
        if (sshChannels.size() > 0) {
            SSHChannel channel = sshChannels.get(0);
            return channel.getUsers();
        } else {
            throw new Exception("ssh.useradd.hosts Configuration error");
        }
    }

    public static SSHChannel checkHostReachable(String host) throws Exception {
        String portStr = SiteConfig.get("ssh.useradd.hosts.port");
        String username = SiteConfig.get("ssh.useradd.hosts.un");
        String password = SiteConfig.get("ssh.useradd.hosts.pwd");

        int port;
        try {
            port = Integer.valueOf(portStr);
        } catch (Exception e) {
            log.warn("no correct ssh port config in site.properties, using default 22 as port");
            port = 22;
        }

        log.info("test jsch connection\n: host=" + host
                + "\nport=" + port
                + "\nuser=" + username);

        long start = System.currentTimeMillis();

        ShellUtils.ShellOut out = ShellUtils.exec(
                host,
                username,
                password,
                port,
                "pwd");

        log.info("===[CMD]===>\n" +
                "host: " + host + "\n" +
                "cmd: " + "pwd" + "\n" +
                "result: " + out + "\n" +
                "===[CMD]" + (System.currentTimeMillis() - start) + "ms===");

        if (out.getRetCode() == 0) {
            SSHChannel sshChannel = new SSHChannel();
            sshChannel.setHost(host);
            sshChannel.setPort(port);
            sshChannel.setUsername(username);
            sshChannel.setPassword(password);
            return sshChannel;
        } else {
            String errMSg = out.getErrMsg().toLowerCase();
            if (errMSg.contains("unknownhost") || errMSg.contains("auth fail")) {
                throw new Exception("目标主机[" + host + "]不可用");
            } else {
                throw new Exception("主机连接失败，请重试");
            }
        }
    }
    public static void main(String[] args) {
        ShellService ss = new ShellService();
        SSHChannel sshChannel = null;
        try {
            sshChannel = checkHostReachable("node1.leap.com");
            List users = sshChannel.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
