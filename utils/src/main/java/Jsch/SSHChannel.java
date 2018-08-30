package Jsch;

import SiteConfig.SiteConfig;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30.
 */
public class SSHChannel {
    private static final Logger log = Logger.getLogger(SSHChannel.class);
    private String host;
    private int port;
    private String username;
    private String password;

    static SSHChannel get(final String defaultHost,
                          final String defaultPort,
                          final String defaultUsername,
                          final String defaultPassword) {
        String host = SiteConfig.get(defaultHost + ".host");
        String port = SiteConfig.get(defaultHost + ".port");
        String username = SiteConfig.get(defaultHost + ".username");
        String password = SiteConfig.get(defaultHost + ".password");


        if (host == null || host.isEmpty()) {
            host = defaultHost;
        }
        if (port == null || port.isEmpty()) {
            port = defaultPort;
            if (port == null || port.isEmpty()) {
                port = "22";
            }
        }
        if (username == null || username.isEmpty()) {
            username = defaultUsername;
            if (username == null || username.isEmpty()) {
                username = "root";
            }
        }
        if (password == null || password.isEmpty()) {
            password = defaultPassword;
        }

        if ((port == null || port.isEmpty()) && (password == null || password.isEmpty())) {
            SSHChannel sshChannel = new SSHChannel();
            sshChannel.setHost(host);
            sshChannel.setPort(Integer.valueOf(port));
            sshChannel.setUsername(username);
            sshChannel.setPassword(password);
            return sshChannel;
        }
        return null;
    }


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected void init() {
    }

    public List getUsers() throws Exception {
        ShellUtils.ShellOut exec = getExec("sudo -n cat /etc/passwd | awk -F: '{print $1}'");
        // System.out.println(exec);
        if (exec.getRetCode() != 0) {
            throw new Exception(exec.getErrMsg());
        } else {
            String[] users = exec.getStdMsg().split("\n");
            return new ArrayList(Arrays.asList(users));
        }
    }

    public ShellUtils.ShellOut getExec(String cmd) {
        long start = System.currentTimeMillis();
        ShellUtils.ShellOut out = ShellUtils.exec(
                this.getHost(),
                this.getUsername(),
                this.getPassword(),
                this.getPort(),
                cmd);
        log.info("===[CMD]===>\n" +
                "host: " + host + "\n" +
                "command: " + cmd + "\n" +
                "result: " + out + "\n" +
                "===[CMD]" + (System.currentTimeMillis() - start) + "ms===");
        return out;
    }
    public static void main(String[] args) {

    }
}
