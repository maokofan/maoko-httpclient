package maoko.http.core.entity;

public class UrlParam {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public UrlParam(String key, String value) {
        this.value = value;
        this.key = key;
    }

}
