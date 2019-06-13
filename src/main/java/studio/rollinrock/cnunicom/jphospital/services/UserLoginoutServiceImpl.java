package studio.rollinrock.cnunicom.jphospital.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.rollinrock.cnunicom.jphospital.repositories.UserLoginOutRepository;
import studio.rollinrock.cnunicom.jphospital.repositories.entities.UserLoginOutRecordEntity;

@Service
public class UserLoginoutServiceImpl implements UserLoginoutService {

    @Autowired
    private UserLoginOutRepository userLoginOutRepository;
    @Override
    public boolean login(String mobile, String smsCode) {
        userLoginOutRepository.save(UserLoginOutRecordEntity.instantiate(mobile, "login"));
        return true;
    }

    @Override
    public boolean logout(String mobile) {
        userLoginOutRepository.save(UserLoginOutRecordEntity.instantiate(mobile, "logout"));
        return true;
    }
}
