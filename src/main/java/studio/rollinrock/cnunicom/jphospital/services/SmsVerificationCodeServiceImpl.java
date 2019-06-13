package studio.rollinrock.cnunicom.jphospital.services;

import org.springframework.stereotype.Service;

@Service
public class SmsVerificationCodeServiceImpl implements SmsVerificationCodeService {
    @Override
    public boolean sendByMobile(String mobile) {
        //todo 短信验证码发送
        return true;
    }
}
