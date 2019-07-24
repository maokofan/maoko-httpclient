package maoko.http.conf.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * 自定义序列化策略--前端
 * 
 * @author fanpei
 */
public class UIStrategyDerializedExclusion implements ExclusionStrategy {

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		UIExpose expose = f.getAnnotation(UIExpose.class);
		if (expose != null && !expose.deserialize())
			return true;
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

}
