package studio.rollinrock.cnunicom.jphospital.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.util.Assert;
import studio.rollinrock.cnunicom.jphospital.JpHospitalApplicationTests;
import studio.rollinrock.cnunicom.jphospital.exceptions.UserWarningException;
import studio.rollinrock.cnunicom.jphospital.services.SmsVerificationCodeService;

import static org.junit.Assert.*;

@Slf4j
public class SmsVerificationCodeServiceImplTest extends JpHospitalApplicationTests {

    @Autowired
    private SmsVerificationCodeService smsVerificationCodeService;

    @Test
    public void sendByMobile() {
        Assert.isTrue(smsVerificationCodeService.sendByMobile("15657978981"), "短信验证码服务功能异常");
    }


    @Test(expected = UserWarningException.class)
    public void sendByUnvalidMobile() {
        try {
            smsVerificationCodeService.sendByMobile("13685760392");

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}