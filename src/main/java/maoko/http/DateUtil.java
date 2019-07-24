package maoko.http;

import maoko.http.HttpAppConst;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * 日期助手
 *
 * @author maoko
 * @date 2019/7/25 0:05
 */
public class DateUtil {

    public static String format(String pattern, Date date) {
        return DateFormatUtils.format(date, pattern);
    }

    public static String format(Date date) {
        return DateFormatUtils.format(date, HttpAppConst.DATEFORMATTER);
    }
}
