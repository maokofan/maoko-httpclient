package maoko.http.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * http连接池配置
 *
 * @author fanpei
 */
@Component
@ConfigurationProperties(prefix = "http")
@Data
public class HttpTdPoolConf {
    //最大连接数
    private int maxTotal = 100;
    // 并发数
    private int defaultMaxPerRoute = 20;
    // 创建连接最长超时时间
    private int connectTimeout = 1000;
    // 从连接池中获取连接的最长时间
    private int connectionRequestTimeout = 500;
    // 数据传输最长时间
    private int socketTimeout = 10000;
    // 提交请求前是否测试连接可用
    // private boolean staleConnectionCheckEnabled = true;
    //空闲Client关闭时长 秒
    private int idelConectionCloseTimeout = 30;
}
