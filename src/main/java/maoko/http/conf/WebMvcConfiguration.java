package maoko.http.conf;


import com.google.gson.Gson;
import maoko.http.conf.gson.UIGsonUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * gson替换
 * 
 * @author fanpei
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	/*
	 * gson替换
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#
	 * configureMessageConverters(java.util.List)
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setWriteAcceptCharset(false);
		stringConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
		converters.add(stringConverter);
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(new SourceHttpMessageConverter<>());

		GsonHttpMessageConverter msConverter = new GsonHttpMessageConverter();
		Gson gson = UIGsonUtil.getGson();
		msConverter.setGson(gson);
		msConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		converters.add(msConverter);
		super.configureMessageConverters(converters);
	}

}
