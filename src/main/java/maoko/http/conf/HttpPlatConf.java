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
    private String serverURL;
}
