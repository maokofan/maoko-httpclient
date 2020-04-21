package maoko.http.core.entity;

import lombok.Data;

/**
 * URL参数实体
 */
@Data
public class UrlParam {
    private String key;
    private String value;


    public UrlParam(String key, String value) {
        this.value = value;
        this.key = key;
    }

}
