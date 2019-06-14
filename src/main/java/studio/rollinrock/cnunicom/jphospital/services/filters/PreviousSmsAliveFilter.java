package studio.rollinrock.cnunicom.jphospital.services.filters;

import lombok.Getter;
import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import studio.rollinrock.cnunicom.jphospital.repositories.SmsVCRecordRepository;
import studio.rollinrock.cnunicom.jphospital.services.SmsFilter;

import java.util.Date;

@Component
public class PreviousSmsAliveFilter extends SmsFilterAdapter implements SmsFilter {
    @Getter
    @Value("${sms.vc.alive-expiry}")
    private long aliveExpiry = 10 * 60 * 1000; // ten minutes

    @Value("${sms.vc.resend-expiry}")
    private long resendExpiry = 60 * 1000; // one minute

    @Autowired
    private SmsVCRecordRepository smsVCRecordRepository;

    @Override
    public boolean allow(String mobile) {
        Date prevSendTime = smsVCRecordRepository.selectLatestVCCreateTime(mobile);
        if (null == prevSendTime) return true;
        return new Instant(prevSendTime).plus(resendExpiry).isBeforeNow();
    }

    @Override
    public String explain(String mobile) {
        return "短信验证码发送过于频繁，请稍后再试";
    }

    public long getAliveExpiryInMinutes() {
        return aliveExpiry / 1000 / 60;
    }
}
