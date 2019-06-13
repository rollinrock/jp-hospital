package studio.rollinrock.cnunicom.jphospital.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.rollinrock.cnunicom.jphospital.services.ActualSmsService;
import studio.rollinrock.cnunicom.jphospital.services.SmsFilter;
import studio.rollinrock.cnunicom.jphospital.services.SmsVerificationCodeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 短信验证码服务，支持短信验证码时效、
 */
@Service
public class SmsVerificationCodeServiceImpl implements SmsVerificationCodeService {

    private long expiry = 10 * 60 * 1000; //10分钟

    private List<SmsFilter> filters = Arrays.asList();


    @Autowired
    private ActualSmsService smsService;

    @Override
    public boolean sendByMobile(String mobile) {
        Objects.requireNonNull(mobile, "手机号码不允许为空");
        filters.stream().forEach(smsFilter -> {
            if (!smsFilter.allow(mobile)) {
                throw new IllegalStateException(smsFilter.explain(mobile));
            }
        });

        String content = "短信验证码";

        //todo 短信验证码发送
        return smsService.send(mobile, content);
    }
}
