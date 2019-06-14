package studio.rollinrock.cnunicom.jphospital.services.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import studio.rollinrock.cnunicom.jphospital.JpHospitalApplicationTests;
import studio.rollinrock.cnunicom.jphospital.services.ActualSmsService;

import static org.junit.Assert.*;

public class ActualSmsServiceImplTest extends JpHospitalApplicationTests {

    @Autowired
    private ActualSmsService actualSmsService;

    @Ignore
    @Test
    public void sendVerificationCode() {
        actualSmsService.sendVerificationCode("15657978981");
    }
}