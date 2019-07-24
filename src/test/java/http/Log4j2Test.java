package http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * log4j2测试
 * 
 * @author fanpei
 * @date 2019年3月18日下午4:20:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class Log4j2Test {
	private static final Logger log = LogManager.getLogger(Log4j2Test.class);

	@Test
	public void printLog() {
		log.debug("log4j2 test");
	}
}
