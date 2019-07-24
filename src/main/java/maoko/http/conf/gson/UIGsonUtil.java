package maoko.http.conf.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import maoko.common.JSONUtil;

/**
 * JSON助手
 * 
 * @author fanpei
 *
 */
/**
 * @author fanpei
 */
public class UIGsonUtil extends JSONUtil {
	static {
		gson = getGson();
		prParser = new JsonParser();
	}

	public static Gson getGson() {
		return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")//
				.addDeserializationExclusionStrategy(new UIStrategyDerializedExclusion())//
				.addSerializationExclusionStrategy(new UIStrategySerializedExclusion())//
				.create();
	}

}
