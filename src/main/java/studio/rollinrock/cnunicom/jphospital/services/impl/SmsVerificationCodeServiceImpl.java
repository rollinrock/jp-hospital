package studio.rollinrock.cnunicom.jphospital.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.rollinrock.cnunicom.jphospital.exceptions.UserWarningException;
import studio.rollinrock.cnunicom.jphospital.repositories.SmsVCRecordRepository;
import studio.rollinrock.cnunicom.jphospital.repositories.entities.SmsVCRecordEntity;
import studio.rollinrock.cnunicom.jphospital.services.ActualSmsService;
import studio.rollinrock.cnunicom.jphospital.services.SmsFilter;
import studio.rollinrock.cnunicom.jphospital.services.SmsVerificationCodeService;
import studio.rollinrock.cnunicom.jphospital.services.VCGenerateService;
import studio.rollinrock.cnunicom.jphospital.services.filters.CnunicomMobileMatchFilter;
import studio.rollinrock.cnunicom.jphospital.services.filters.PreviousSmsAliveFilter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 短信验证码服务，支持短信验证码时效、
 */
@Service
public class SmsVerificationCodeServiceImpl implements SmsVerificationCodeService {

    @Autowired
    private CnunicomMobileMatchFilter cnunicomMobileMatchFilter;
    @Autowired
    private PreviousSmsAliveFilter previousSmsAliveFilter;
    @Autowired
    private VCGenerateService vcGenerateService;
    @Autowired
    private SmsVCRecordRepository smsVCRecordRepository;

    private List<SmsFilter> filters = new ArrayList<>();

    @PostConstruct
    private void init() {
        filters.add(cnunicomMobileMatchFilter);
        filters.add(previousSmsAliveFilter);

    }


    @Autowired
    private ActualSmsService smsService;

    @Override
    public boolean sendByMobile(String mobile) {
        Objects.requireNonNull(mobile, "手机号码不允许为空");
        filters.stream().forEach(smsFilter -> {
            if (!smsFilter.allow(mobile)) {
                throw new UserWarningException("1", smsFilter.explain(mobile));
            }
        });

        String verificationCode = vcGenerateService.genRandomNumIn6(previousSmsAliveFilter.getAliveExpiry());
        String content = String.format("【中国联通】您正在登陆，短信验证码(%s),%d分钟内有效。请勿泄露",
                verificationCode,
                previousSmsAliveFilter.getAliveExpiryInMinutes());

        //todo 短信验证码发送
        boolean succeeded = smsService.send(mobile, content);
        smsVCRecordRepository.save(SmsVCRecordEntity.newIns(mobile, verificationCode));
        return succeeded;
    }
}
