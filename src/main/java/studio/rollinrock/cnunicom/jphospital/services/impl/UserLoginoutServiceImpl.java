package studio.rollinrock.cnunicom.jphospital.services.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.rollinrock.cnunicom.jphospital.repositories.UserLoginOutRepository;
import studio.rollinrock.cnunicom.jphospital.repositories.entities.UserLoginOutRecordEntity;
import studio.rollinrock.cnunicom.jphospital.services.SmsVerificationCodeService;
import studio.rollinrock.cnunicom.jphospital.services.UserLoginoutService;

@Slf4j
@Service
public class UserLoginoutServiceImpl implements UserLoginoutService {

    @Autowired
    private UserLoginOutRepository userLoginOutRepository;

    @Autowired
    private SmsVerificationCodeService smsVerificationCodeService;

    @Override
    public boolean login(String mobile, String smsCode) {
        if (smsVerificationCodeService.validate(mobile, smsCode)) {
            userLoginOutRepository.save(UserLoginOutRecordEntity.newInsAsLogin(mobile));
        }
        return true;
    }

    @Override
    public boolean logout(String mobile) {
        userLoginOutRepository.save(UserLoginOutRecordEntity.newInsAsLogout(mobile));
        return true;
    }
}
