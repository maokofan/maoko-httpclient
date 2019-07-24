package maoko.http.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * http连接池配置
 *
 * @author fanpei
 */
@Component
@ConfigurationProperties(prefix = "http")
public class HttpTdPoolConf {

    private int maxTotal = 100;// 最大连接数
    private int defaultMaxPerRoute = 20;// 并发数
    private int connectTimeout = 1000;// 创建连接最长超时时间
    private int connectionRequestTimeout = 500;// 从连接池中获取连接的最长时间
    private int socketTimeout = 10000;// 数据传输最长时间
    private boolean staleConnectionCheckEnabled = true;// 提交请求前是否测试连接可用

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean isStaleConnectionCheckEnabled() {
        return staleConnectionCheckEnabled;
    }

    public void setStaleConnectionCheckEnabled(boolean staleConnectionCheckEnabled) {
        this.staleConnectionCheckEnabled = staleConnectionCheckEnabled;
    }

}
