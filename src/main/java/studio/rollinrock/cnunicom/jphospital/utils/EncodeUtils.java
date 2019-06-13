package studio.rollinrock.cnunicom.jphospital.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <p>
 * 类说明：编码解码工具类
 * <p>
 * 类名称: EncodeUtils.java
 *
 * @version v1.0.0
 * @author uccp
 * @date 2019/3/22 22:37
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class EncodeUtils {

    private final static String DEFAULT_ENCODING = "GBK";

    /**
     * 功能描述：编码
     *
     * @param str 编码字符串
     * @return void
     * @author uccp
     * @date 2019/3/22 10:47 PM
     */
    public static String encodeStr(String str) {
        try {
            return URLEncoder.encode(str, EncodeUtils.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * 功能描述：解码
     *
     * @param str 解码字符串
     * @return void
     * @author uccp
     * @date 2019/3/22 10:47 PM
     */
    public static String decodeStr(String str) {
        try {
            return URLDecoder.decode(str, EncodeUtils.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Decoding Exception", e);
        }
    }
}
