package studio.rollinrock.cnunicom.jphospital.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public interface ActualSmsService {

    /**
     * 发送内部维护的6位数字验证码，短信验证码有效时间默认为10分钟
     * @param mobile
     * @return
     */

    @Deprecated
    boolean sendVerificationCode(String mobile);

    /**
     * 自定义验证码发送
     * @param mobile 手机号
     * @param verificationCode 自定义验证码
     * @param expiry 短信验证码有效时间，单位毫秒，最小允许时间为1分钟
     * @return 成功失败
     * @throws IllegalStateException 失败则抛出异常
     */
    @Deprecated
    boolean sendVerificationCodeByCustom(String mobile, String verificationCode, long expiry);

    default boolean checkMobile(String mobile){
        // todo 校验手机号码是否合法
        String regex = "^((13[0-2])|(14[5])|(15([5-6]))|(16[6])|(17[5-6])|(18[5-6]))\\d{8}$";
        if(Pattern.matches(regex, mobile)) {
            return true;
        }
        return  false;
    }

    boolean send(String mobile, String content);
    boolean sendByTemplate(String mobile, String templateId, Map<String, String> replacements);
}
