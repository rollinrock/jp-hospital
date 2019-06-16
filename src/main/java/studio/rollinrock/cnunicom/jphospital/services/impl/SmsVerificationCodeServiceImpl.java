package studio.rollinrock.cnunicom.jphospital.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.Instant;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Slf4j
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

//    @Transactional
    @Override
    public boolean sendByMobile(String mobile) {
        Objects.requireNonNull(mobile, "手机号码不允许为空");
        filters.stream().forEach(smsFilter -> {
            if (!smsFilter.allow(mobile)) throw new UserWarningException("1", smsFilter.explain(mobile));
        });
        String verificationCode = vcGenerateService.genRandomNumIn6(previousSmsAliveFilter.getAliveExpiry());
        String content = String.format("【中国联通】您正在登陆，短信验证码(%s),%d分钟内有效。仅限单次使用，请勿泄露!",
                verificationCode,
                previousSmsAliveFilter.getAliveExpiryInMinutes());

        if (smsService.sendVerificationCode(mobile, content)) {
            smsVCRecordRepository.save(SmsVCRecordEntity.newIns(mobile, verificationCode, previousSmsAliveFilter.getAliveExpiry()));
            return true;
        }

        return false;
    }

    @Override
    public boolean validate(String mobile, String verificationCode) {
        if (!cnunicomMobileMatchFilter.allow(mobile)) {
            throw new UserWarningException("1", cnunicomMobileMatchFilter.explain(mobile));
        }

        SmsVCRecordEntity vcRecordEntity = smsVCRecordRepository.findFirstByMobileOrderByCreateTimeDesc(Objects.requireNonNull(mobile));
        // 从未发送
        if (null == vcRecordEntity)
            throw new UserWarningException("1", "无效短信验证码，请检查");
        else if (vcRecordEntity.isUsedOrNot())
            throw new UserWarningException("1", "当前短信验证码已使用，请重新获取");
        else if (new Instant(vcRecordEntity.getCreateTime()).plus(vcRecordEntity.getAliveExpiryInMills()).isBeforeNow())
            throw new UserWarningException("1", "当前短信验证码已失效，请重新获取");
        else if (!Objects.requireNonNull(verificationCode).equalsIgnoreCase(vcRecordEntity.getVerificationCode()))
            throw new UserWarningException("1", "短信短信验证码输入错误，请检查");

        try {
            //更新短信验证码记录为已使用
            vcRecordEntity.setUsedOrNot(true);
            smsVCRecordRepository.save(vcRecordEntity);
        } catch (Exception e) {
            log.error("状态不一致警告：手机号[{}]验证码[{}]已验证使用，但保存数据库失败；存在短信验证码重复使用风险", mobile, verificationCode);
        }

        return true;
    }


}
