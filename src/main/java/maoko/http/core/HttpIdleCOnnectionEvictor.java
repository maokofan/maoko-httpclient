package maoko.http.core;

import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理关闭连接的线程
 *
 * @author fanpei
 */
@Component
public class HttpIdleCOnnectionEvictor extends Thread {

    @Autowired
    private HttpClientConnectionManager connMgr;

    private volatile boolean shutDown;

    public HttpIdleCOnnectionEvictor() {
        super();
        super.start();
    }

    @Override
    public void run() {
        try {
            while (!shutDown) {
                synchronized (this) {
                    wait(5000);
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void shutDown() {
        shutDown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
