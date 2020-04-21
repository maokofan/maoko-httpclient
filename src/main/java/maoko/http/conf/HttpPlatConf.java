package maoko.http.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * http服务配置信息
 *
 * @author fanpei
 */
@Component
@ConfigurationProperties(prefix = "http-server")
public class HttpPlatConf {

    private String serverIP = "109.64.20.99";
    private int serverPort = 6070;

    private String urlStart;

    /**
     * 获取URL起始地址
     *
     * @return
     */
    public String getUrlStart() {
        if (null == urlStart) {
            StringBuilder builder = new StringBuilder("http://");
            urlStart = builder.append(serverIP).append(":").append(Integer.toString(serverPort)).toString();
        }
        return urlStart;
    }
}
