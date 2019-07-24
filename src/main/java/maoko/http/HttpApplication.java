package maoko.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * 程序启动入口
 *
 * @author maoko
 */

@PropertySource("classpath:application.yml")
//默认加载数据库配置，当前无数据库配置，排除在外2019.03.16 fanpei
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class,
        // gson转换排除
        JacksonAutoConfiguration.class})
public class HttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpApplication.class);
    }

}
