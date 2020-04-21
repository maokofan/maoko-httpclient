package maoko.http;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.PropertySource;



/**
 * @author maoko
 */
@PropertySource("classpath:application.yml")
@SpringBootApplication
public class HttpApplication implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("http client start.....");
    }
}
