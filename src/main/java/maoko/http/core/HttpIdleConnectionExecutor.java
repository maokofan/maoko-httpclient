package maoko.http.core;

import maoko.http.conf.HttpTdPoolConf;
import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 处理关闭连接的线程
 *
 * @author fanpei
 */
@Component
public class HttpIdleConnectionExecutor extends Thread {

    private final HttpClientConnectionManager connMgr;
    private final HttpTdPoolConf conf;

    private volatile boolean shutDown;

    public HttpIdleConnectionExecutor(HttpClientConnectionManager connMgr, HttpTdPoolConf conf) {
        super();
        this.connMgr = connMgr;
        this.conf = conf;
        super.start();
    }

    @Override
    public void run() {
        try {
            while (!shutDown) {
                synchronized (this) {
                    wait(5000);
                    connMgr.closeExpiredConnections();
                    // that have been idle longer than 30 sec
                    connMgr.closeIdleConnections(conf.getIdelConectionCloseTimeout(), TimeUnit.SECONDS);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 关闭
     */
    public void shutDown() {
        shutDown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
