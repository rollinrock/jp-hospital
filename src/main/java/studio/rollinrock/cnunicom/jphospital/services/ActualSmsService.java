package studio.rollinrock.cnunicom.jphospital.services;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public interface ActualSmsService {

    default boolean checkMobile(String mobile){
        Objects.requireNonNull(mobile);
        String regex = "^((13[0-2])|(14[5])|(15([5-6]))|(16[6])|(17[5-6])|(18[5-6]))\\d{8}$";
        return Pattern.matches(regex, mobile);
    }

    boolean sendVerificationCode(String mobile, String content);


}
