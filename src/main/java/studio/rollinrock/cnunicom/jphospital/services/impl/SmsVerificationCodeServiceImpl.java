package studio.rollinrock.cnunicom.jphospital.services.impl;

import org.springframework.stereotype.Service;
import studio.rollinrock.cnunicom.jphospital.services.SmsVerificationCodeService;
import studio.rollinrock.cnunicom.jphospital.utils.SmsSendUtils;

@Service
public class SmsVerificationCodeServiceImpl implements SmsVerificationCodeService {
    @Override
    public boolean sendByMobile(String mobile) {
        SmsSendUtils smsSendUtils=new SmsSendUtils();
        return smsSendUtils.Send(mobile);
    }
}
