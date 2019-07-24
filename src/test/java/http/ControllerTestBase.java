package http;

import maoko.http.HttpApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

/**
 * 测试基类
 * 
 * @author fanpei
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { HttpApplication.class, MockServletContext.class })
public class ControllerTestBase {

	protected MockMvc mockMvc;// 模拟mvc测试对象
	@Autowired
	protected WebApplicationContext wac;

	@Before()
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/**
	 * 发送信息
	 * 
	 * @param method
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected void sendRequest(HttpMethod method, String url, String params) throws Exception {
		MvcResult mvResult = this.mockMvc.perform(MockMvcRequestBuilders.request(method, url)//
				.contentType(MediaType.APPLICATION_JSON)//
				.content(params))//
				.andReturn();
		System.err.println(mvResult.getResponse().getContentAsString());
	}

	protected void sendRequest(HttpMethod method, String url, MultiValueMap<String, String> params) throws Exception {
		MockHttpServletRequestBuilder builders = MockMvcRequestBuilders.request(method, url)//
				.contentType(MediaType.APPLICATION_JSON);
		builders.params(params);
		ResultActions actions = this.mockMvc.perform(builders);
		MvcResult mvResult = actions.andReturn();
		System.err.println(mvResult.getResponse().getContentAsString());
	}
}
