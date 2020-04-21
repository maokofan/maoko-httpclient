package maoko.http.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * http服务配置信息
 *
 * @author fanpei
 */
@Component
@ConfigurationProperties(prefix = "http-server")
@Data
public class HttpPlatConf {

    private String serverIP;
    private int serverPort;

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
