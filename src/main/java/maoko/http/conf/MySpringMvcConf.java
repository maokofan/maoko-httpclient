package maoko.http.conf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author maoko
 * @date 2020/4/25 14:41
 */
//@Configuration
//@EnableWebMvc
@Deprecated
public class MySpringMvcConf extends WebMvcConfigurationSupport {
    private final static String DATEFROMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setWriteAcceptCharset(false);
        stringConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        converters.add(stringConverter);
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new SourceHttpMessageConverter<>());

        GsonHttpMessageConverter msConverter = new GsonHttpMessageConverter();
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat(DATEFROMAT).create();
        msConverter.setGson(gson);
        msConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(msConverter);
        super.configureMessageConverters(converters);
    }
}
